package com.accion.recruitment.web.controller;

import com.accion.recruitment.common.constants.UserConstants;
import com.accion.recruitment.common.constants.UserRestURIConstants;
import com.accion.recruitment.common.enums.HttpStatusEnums;
import com.accion.recruitment.common.enums.UserEnums;
import com.accion.recruitment.jpa.entities.TechnicalScreenerSkills;
import com.accion.recruitment.jpa.entities.User;
import com.accion.recruitment.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 12/26/16 00:11 AM#$
 */
@Controller
public class UserController {


    @Autowired
    private UserService userService;

    private final Md5PasswordEncoder encoder = new Md5PasswordEncoder();

    private final Random random = new SecureRandom();

    private final Date currentDate = new Date();

    private final SimpleDateFormat sdf = new SimpleDateFormat(UserConstants.DATE_FORMAT);


    @RequestMapping(value = UserRestURIConstants.CREATE_USER, produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
    public @ResponseBody String createUser(final @RequestBody User user,
                              final @RequestBody TechnicalScreenerSkills technicalScreenerSkills,
                              final @RequestParam(required = false, value = "userImage") MultipartFile userImage,
                              final @RequestParam(required = false, value = "userProfile") MultipartFile userProfile,
                              final Principal principal) {

        List<TechnicalScreenerSkills> technicalScreenerSkillsList=new ArrayList<TechnicalScreenerSkills>();

        if(user != null && user.getUserName() != null && user.getUserName().isEmpty()){
            try{
                User userObject=this.userService.findUserByPropertyName(UserConstants.USER_NAME,user.getUserName());
                if(userObject != null)
                    return String.valueOf(HttpStatusEnums.USER_NAME_EXIST.ResponseMsg());
            }catch (SQLException e){
                return String.valueOf(HttpStatusEnums.DATABASE_EXCEPTION.ResponseMsg());
            }catch (Exception e){
                return String.valueOf(HttpStatusEnums.RECORD_NOT_SAVED.ResponseMsg());
            }
        }
        if(user != null && user.getEmailId() != null && user.getEmailId().isEmpty()){
            try{
                User userObject=this.userService.findUserByPropertyName(UserConstants.EMAIL_ID,user.getEmailId());
                if(userObject != null)
                    return String.valueOf(HttpStatusEnums.EMAIlID_EXIST.ResponseMsg());
            }catch (SQLException e){
                return String.valueOf(HttpStatusEnums.DATABASE_EXCEPTION.ResponseMsg());
            }catch (Exception e){
                return String.valueOf(HttpStatusEnums.RECORD_NOT_SAVED.ResponseMsg());
            }
        }
        if(user != null && user.getContactNumber() != null){
            try{
                User userObject=this.userService.findUserByPropertyName(UserConstants.CONTACT_NUMBER,user.getContactNumber());
                if(userObject != null)
                    return String.valueOf(HttpStatusEnums.CONTACT_NUMBER_EXIST.ResponseMsg());
            }catch (SQLException e){
                return String.valueOf(HttpStatusEnums.DATABASE_EXCEPTION.ResponseMsg());
            }catch (Exception e){
                return String.valueOf(HttpStatusEnums.RECORD_NOT_SAVED.ResponseMsg());
            }
        }
        if (user != null && userImage != null && !userImage.isEmpty()) {
            try {
                byte[] bytes = userImage.getBytes();
                user.setUserImage(bytes);
            } catch (IOException e) {
                return String.valueOf(HttpStatusEnums.USER_IMAGE_EXCEPTION.ResponseMsg());
            }catch (Exception e){
                return String.valueOf(HttpStatusEnums.RECORD_NOT_SAVED.ResponseMsg());
            }
        }
        if (user != null && userProfile != null && !userProfile.isEmpty()) {
            try {
                byte[] bytes = userProfile.getBytes();
                user.setUserProfile(bytes);
            } catch (IOException e) {
                return String.valueOf(HttpStatusEnums.USER_PROFILE_EXCEPTION.ResponseMsg());
            }catch (Exception e){
                return String.valueOf(HttpStatusEnums.RECORD_NOT_SAVED.ResponseMsg());
            }
        }

        user.setPassword(this.encoder.encodePassword(this.generatePassword(), null));
        user.setCreatedBy(principal.getName());
        user.setCreatedDate(new Date(sdf.format(currentDate)));
        user.setUpdatedBy(principal.getName());
        user.setUpdatedDate(new Date(sdf.format(currentDate)));



        if(user.getRole().equalsIgnoreCase(UserEnums.TechnicalScreener.toString())){
            try{
                technicalScreenerSkillsList=this.getTechnicalSkillsObjectList(technicalScreenerSkills);
                user.getTechnicalScreenerDetailsDSkillsSet().addAll(technicalScreenerSkillsList);
                this.userService.saveUser(user);
            }catch (ArrayIndexOutOfBoundsException e){
                return String.valueOf(HttpStatusEnums.USER_SKILLS_EXCEPTION.ResponseMsg());
            }catch (SQLException e){
                return String.valueOf(HttpStatusEnums.DATABASE_EXCEPTION.ResponseMsg());
            }catch (Exception e){
                return String.valueOf(HttpStatusEnums.RECORD_NOT_SAVED.ResponseMsg());
            }
        }else{
            try{
                this.userService.saveUser(user);
            }catch (SQLException e){
                return String.valueOf(HttpStatusEnums.DATABASE_EXCEPTION.ResponseMsg());
            }catch (Exception e){
                return String.valueOf(HttpStatusEnums.RECORD_NOT_SAVED.ResponseMsg());
            }
        }

        return String.valueOf(HttpStatus.CREATED);
    }




    @RequestMapping(value = UserRestURIConstants.GET_ALL_USER, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public List<User> getAllUsers() {
        List<User> userList=this.userService.findAllUser();
        return  userList;
    }


    @RequestMapping(value = UserRestURIConstants.GET_USER_NAME, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public String userNameExist(@PathVariable("userName") final String userName)  {
        User user;
        JSONObject jsonObject=new JSONObject();
        try{
            user=this.userService.findUserByPropertyName(UserConstants.USER_NAME,userName);
            if(user != null){
                jsonObject.put("user", user.toString());
                return jsonObject.toString();
            }
        }catch (SQLException e){
            return String.valueOf(HttpStatus.FOUND);
        }catch (Exception e){
            return String.valueOf(HttpStatus.FOUND);
        }
        return  jsonObject.toString();
    }

    @RequestMapping(value = UserRestURIConstants.GET_EMAIL_ID, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public String emailIdExist(@PathVariable("emailId") final String emailId) {
        User user;
        JSONObject jsonObject=new JSONObject();
        try{
            user=this.userService.findUserByPropertyName(UserConstants.EMAIL_ID, emailId);
            if(user != null){
                jsonObject.put("user", user.toString());
                return jsonObject.toString();
            }
        }catch (SQLException e){
            return String.valueOf(HttpStatus.FOUND);
        }catch (Exception e){
            return String.valueOf(HttpStatus.FOUND);
        }
        return  jsonObject.toString();
    }

    @RequestMapping(value = UserRestURIConstants.GET_CONTACT_NUMBER, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public String contactNumberExist(@PathVariable("contactNumber") final Long contactNumber){
        User user;
        JSONObject jsonObject=new JSONObject();
        try{
            user=this.userService.findUserByPropertyName(UserConstants.CONTACT_NUMBER, contactNumber);
            if(user != null){
                jsonObject.put("user", user.toString());
                return jsonObject.toString();
            }
        }catch (SQLException e){
            return String.valueOf(HttpStatus.FOUND);
        }catch (Exception e){
            return String.valueOf(HttpStatus.FOUND);
        }
        return  jsonObject.toString();
    }

    public final String generatePassword(){
        String password="";
        try{
            for (int i=0; i< UserConstants.PASSWORD_LENGTH; i++)
            {
                int index = (int)(this.random.nextDouble()* UserConstants.LETTERS.length());
                password += UserConstants.LETTERS.substring(index, index+1);
            }
        }catch (Exception e){
            password= UserConstants.DEFAULT_PASSWORD;
        }
        return password;

    }

    public final List<TechnicalScreenerSkills> getTechnicalSkillsObjectList(TechnicalScreenerSkills technicalScreenerSkills) throws ArrayIndexOutOfBoundsException{

        String primarySkillsArray[],secondarySkillsArray[],primarySkillsYearsArray[],secondarySkillsYearsArray[],primarySkillsMonthsArray[],secondarySkillsMonthsArray[];
        int length,count=0;
        Long years,months;
        String primarySkills,secondarySkills;

        TechnicalScreenerSkills technicalScreenerSkillsArray[];
        List<TechnicalScreenerSkills> technicalScreenerSkillsList=new ArrayList<TechnicalScreenerSkills>();

        primarySkillsArray=technicalScreenerSkills.getPrimarySkills().split(",");
        secondarySkillsArray=technicalScreenerSkills.getSecondarySkills().split(",");

        primarySkillsYearsArray=technicalScreenerSkills.getPrimarySkillsYears().split(",");
        secondarySkillsYearsArray=technicalScreenerSkills.getSecondarySkillsYears().split(",");

        primarySkillsMonthsArray=technicalScreenerSkills.getPrimarySkillsMonths().split(",");
        secondarySkillsMonthsArray=technicalScreenerSkills.getSecondarySkillsMonths().split(",");

        length=(primarySkillsArray.length+secondarySkillsArray.length*primarySkillsYearsArray.length+secondarySkillsYearsArray.length+primarySkillsMonthsArray.length+secondarySkillsMonthsArray.length);

        technicalScreenerSkillsArray=new TechnicalScreenerSkills[length];

        for(int i=0;i<primarySkillsArray.length;i++){
            primarySkills=primarySkillsArray[i];
            years= Long.valueOf(primarySkillsYearsArray[i]);
            months= Long.valueOf(primarySkillsMonthsArray[i]);
            technicalScreenerSkillsArray[i]=new TechnicalScreenerSkills();
            technicalScreenerSkillsArray[i].setSkills(primarySkills);
            technicalScreenerSkillsArray[i].setYears(years);
            technicalScreenerSkillsArray[i].setMonths(months);
            technicalScreenerSkillsArray[i].setSkillType(UserEnums.PrimarySkill.toString());
            count=count+1;
        }

        for(int i=0;i<secondarySkillsArray.length;i++){
            secondarySkills=secondarySkillsArray[i];
            years= Long.valueOf(secondarySkillsYearsArray[i]);
            months= Long.valueOf(secondarySkillsMonthsArray[i]);
            technicalScreenerSkillsArray[count+i]=new TechnicalScreenerSkills();
            technicalScreenerSkillsArray[count+i].setSkills(secondarySkills);
            technicalScreenerSkillsArray[count+i].setYears(years);
            technicalScreenerSkillsArray[count+i].setMonths(months);
            technicalScreenerSkillsArray[count+i].setSkillType(UserEnums.SecondarySkill.toString());
            count=count+1;
        }

        for (int i = 0; i < technicalScreenerSkillsArray.length; i++){
            technicalScreenerSkillsList.add(technicalScreenerSkillsArray[i]);
        }

        return  technicalScreenerSkillsList;
    }
}
