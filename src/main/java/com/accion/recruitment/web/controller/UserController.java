package com.accion.recruitment.web.controller;

import com.accion.recruitment.common.constants.UserControllerConstants;
import com.accion.recruitment.common.constants.UserRestURIConstants;
import com.accion.recruitment.common.enums.UserEnums;
import com.accion.recruitment.jpa.entities.TechnicalScreenerSkills;
import com.accion.recruitment.jpa.entities.User;
import com.accion.recruitment.service.UserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.security.SecureRandom;
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

    final Random random = new SecureRandom();

    private final Date currentDate = new Date();

    private final SimpleDateFormat sdf = new SimpleDateFormat(UserControllerConstants.DATE_FORMAT);


    @RequestMapping(value = UserRestURIConstants.CREATE_USER, produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
    public @ResponseBody String createUser(final @RequestBody User user,
                              final @RequestBody TechnicalScreenerSkills technicalScreenerSkills,
                              final @RequestParam(required = false, value = "userImage") MultipartFile userImage,
                              final @RequestParam(required = false, value = "userProfile") MultipartFile userProfile,
                              final Principal principal) {

        List<TechnicalScreenerSkills> technicalScreenerSkillsList=new ArrayList<TechnicalScreenerSkills>();

        if (user != null && userImage != null && !userImage.isEmpty()) {
            try {
                byte[] bytes = userImage.getBytes();
                user.setUserImage(bytes);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (user != null && userProfile != null && !userProfile.isEmpty()) {
            try {
                byte[] bytes = userProfile.getBytes();
                user.setUserProfile(bytes);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        user.setPassword(this.encoder.encodePassword(this.generatePassword(), null));
        user.setCreatedBy(principal.getName());
        user.setCreatedDate(new Date(sdf.format(currentDate)));
        user.setUpdatedBy(principal.getName());
        user.setUpdatedDate(new Date(sdf.format(currentDate)));



        if(user.getRole().equalsIgnoreCase(UserEnums.TechnicalScreener.toString())){
            technicalScreenerSkillsList=this.getTechnicalSkillsObjectList(technicalScreenerSkills);
            user.getTechnicalScreenerDetailsDSkillsSet().addAll(technicalScreenerSkillsList);
            this.userService.saveUser(user);
        }else{
            this.userService.saveUser(user);
        }


        return null;
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
            user=this.userService.findUserByPropertyName("userName",userName);
            if(user==null)
               return String.valueOf(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return String.valueOf(HttpStatus.NOT_FOUND);
        }
        try {
            jsonObject.put("user", user.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  jsonObject.toString();
    }

    @RequestMapping(value = UserRestURIConstants.GET_EMAIL_ID, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public String emailIdExist(@PathVariable("emailId") final String emailId) {

        User user;
        JSONObject jsonObject=new JSONObject();

        try{
            user=this.userService.findUserByPropertyName("emailId", emailId);
            if(user==null)
                return String.valueOf(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return String.valueOf(HttpStatus.NOT_FOUND);
        }
        try {
            jsonObject.put("user", user.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  jsonObject.toString();
    }

    @RequestMapping(value = UserRestURIConstants.GET_CONTACT_NUMBER, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public String contactNumberExist(@PathVariable("contactNumber") final Long contactNumber){

        User user;
        JSONObject jsonObject=new JSONObject();

        try{
            user=this.userService.findUserByPropertyName("contactNumber", contactNumber);
            if(user==null)
                return String.valueOf(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return String.valueOf(HttpStatus.NOT_FOUND);
        }

        try {
            jsonObject.put("user", user.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  jsonObject.toString();
    }

    public final String generatePassword(){
        String password="";
        try{
            for (int i=0; i<UserControllerConstants.PASSWORD_LENGTH; i++)
            {
                int index = (int)(this.random.nextDouble()*UserControllerConstants.LETTERS.length());
                password += UserControllerConstants.LETTERS.substring(index, index+1);
            }
        }catch (Exception e){
            password=UserControllerConstants.DEFAULT_PASSWORD;
        }
        return password;

    }

    public final List<TechnicalScreenerSkills> getTechnicalSkillsObjectList(TechnicalScreenerSkills technicalScreenerSkills){

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
