package com.accion.recruitment.web.controller;

import com.accion.recruitment.common.constants.UserConstants;
import com.accion.recruitment.common.constants.UserRestURIConstants;
import com.accion.recruitment.common.enums.HttpStatusEnums;
import com.accion.recruitment.common.enums.UserEnums;
import com.accion.recruitment.jpa.entities.TechnicalScreenerSkills;
import com.accion.recruitment.jpa.entities.User;
import com.accion.recruitment.service.EmailNotificationService;
import com.accion.recruitment.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
@Api(value = "UserController", description = "All the API's  related to user ")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private EmailNotificationService emailNotificationService;

    private final Md5PasswordEncoder encoder = new Md5PasswordEncoder();

    private final Random random = new SecureRandom();

    private final Date currentDate = new Date();

    private final SimpleDateFormat sdf = new SimpleDateFormat(UserConstants.DATE_FORMAT);


    @ApiOperation(value = "Create the new User ",  code = 201, httpMethod="POST")

    @ApiResponses(value = {@ApiResponse(code = 201, message = "User Created Successfully")
            , @ApiResponse(code = 302, message = "User Found")
            , @ApiResponse(code = 500, message = "Internal Server Error")})

    @RequestMapping(value = UserRestURIConstants.CREATE_USER, produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)

    public  ResponseEntity<Void> createUser(@RequestBody User user
                              /*final @RequestBody(required = false) TechnicalScreenerSkills technicalScreenerSkills,
                              final @RequestParam(required = false, value = "userImage") MultipartFile userImage,
                              final @RequestParam(required = false, value = "userProfile") MultipartFile userProfile,
                              final Principal principal*/) {

        List<TechnicalScreenerSkills> technicalScreenerSkillsList=new ArrayList<TechnicalScreenerSkills>();

       /* if(user != null && user.getUserName() != null && user.getUserName().isEmpty()){
            try{
                User userObject=this.userService.findUserByPropertyName(UserConstants.USER_NAME,user.getUserName());
                if(userObject != null)
                    return new ResponseEntity<String>(HttpStatusEnums.USER_NAME_EXIST.ResponseMsg(), HttpStatus.FOUND);
            }catch (SQLException e){
                    return new ResponseEntity<String>(HttpStatusEnums.DATABASE_EXCEPTION.ResponseMsg(), HttpStatus.INTERNAL_SERVER_ERROR);
            }catch (Exception e){
                    return new ResponseEntity<String>(HttpStatusEnums.RECORD_NOT_SAVED.ResponseMsg(), HttpStatus.INTERNAL_SERVER_ERROR);

            }
        }
        if(user != null && user.getEmailId() != null && user.getEmailId().isEmpty()){
            try{
                User userObject=this.userService.findUserByPropertyName(UserConstants.EMAIL_ID,user.getEmailId());
                if(userObject != null)
                    return new ResponseEntity<String>(HttpStatusEnums.EMAIlID_EXIST.ResponseMsg(), HttpStatus.FOUND);
            }catch (SQLException e){
                return new ResponseEntity<String>(HttpStatusEnums.DATABASE_EXCEPTION.ResponseMsg(), HttpStatus.INTERNAL_SERVER_ERROR);
            }catch (Exception e){
                return new ResponseEntity<String>(HttpStatusEnums.RECORD_NOT_SAVED.ResponseMsg(), HttpStatus.INTERNAL_SERVER_ERROR);

            }
        }
        if(user != null && user.getContactNumber() != null){
            try{
                User userObject=this.userService.findUserByPropertyName(UserConstants.CONTACT_NUMBER,user.getContactNumber());
                if(userObject != null)
                    return new ResponseEntity<String>(HttpStatusEnums.CONTACT_NUMBER_EXIST.ResponseMsg(), HttpStatus.FOUND);
            }catch (SQLException e){
                return new ResponseEntity<String>(HttpStatusEnums.DATABASE_EXCEPTION.ResponseMsg(), HttpStatus.INTERNAL_SERVER_ERROR);
            }catch (Exception e){
                return new ResponseEntity<String>(HttpStatusEnums.RECORD_NOT_SAVED.ResponseMsg(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        if (user != null && userImage != null && !userImage.isEmpty()) {
            try {
                byte[] bytes = userImage.getBytes();
                user.setUserImage(bytes);
            } catch (IOException e) {
                return new ResponseEntity<String>(HttpStatusEnums.USER_IMAGE_EXCEPTION.ResponseMsg(), HttpStatus.INTERNAL_SERVER_ERROR);
            }catch (Exception e){
                return new ResponseEntity<String>(HttpStatusEnums.RECORD_NOT_SAVED.ResponseMsg(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        if (user != null && userProfile != null && !userProfile.isEmpty()) {
            try {
                byte[] bytes = userProfile.getBytes();
                user.setUserProfile(bytes);
            } catch (IOException e) {
                return new ResponseEntity<String>(HttpStatusEnums.USER_PROFILE_EXCEPTION.ResponseMsg(), HttpStatus.INTERNAL_SERVER_ERROR);
            }catch (Exception e){
                return new ResponseEntity<String>(HttpStatusEnums.RECORD_NOT_SAVED.ResponseMsg(), HttpStatus.INTERNAL_SERVER_ERROR);
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
                this.emailNotificationService.sendUserCredentials(user);
            }catch (ArrayIndexOutOfBoundsException e){
                return new ResponseEntity<String>(HttpStatusEnums.USER_SKILLS_EXCEPTION.ResponseMsg(), HttpStatus.INTERNAL_SERVER_ERROR);
            }catch (SQLException e){
                return new ResponseEntity<String>(HttpStatusEnums.DATABASE_EXCEPTION.ResponseMsg(), HttpStatus.INTERNAL_SERVER_ERROR);
            }catch (Exception e){
                return new ResponseEntity<String>(HttpStatusEnums.RECORD_NOT_SAVED.ResponseMsg(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else{
            try{
                this.userService.saveUser(user);
                this.emailNotificationService.sendUserCredentials(user);
            }catch (SQLException e){
            return new ResponseEntity<String>(HttpStatusEnums.DATABASE_EXCEPTION.ResponseMsg(), HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            return new ResponseEntity<String>(HttpStatusEnums.RECORD_NOT_SAVED.ResponseMsg(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        }

        return new ResponseEntity<String>(HttpStatusEnums.RECORD_NOT_SAVED.ResponseMsg(), HttpStatus.CREATED);*/
        return new ResponseEntity<Void>( HttpStatus.CREATED);

    }




    @RequestMapping(value = UserRestURIConstants.GET_ALL_USER, produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.TEXT_PLAIN_VALUE,method = RequestMethod.GET)
    @ResponseBody
    public List<User> getAllUsers() {
        List<User> userList=this.userService.findAllUser();
        return  userList;
    }


   /* @ApiOperation(value = "Get the User based on ID  ", httpMethod="GET"
            , notes = "Return the matched User")
    @ApiResponses(value = {@ApiResponse(code = 302, message = "User Found "),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(value = UserRestURIConstants.CHANGE_STATUS, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> changeStatus(@PathVariable("id") final int userId) {
        User user;
        JSONObject jsonObject=new JSONObject();
        try{
            user=this.userService.findUserById(userId);
            if(user != null){
                jsonObject.put("user", user.toString());
                return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.FOUND);
            }
        }catch (SQLException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);

    }*/





    @ApiOperation(value = "Get the User based on ID  ", httpMethod="GET"
            , notes = "Return the matched User")
    @ApiResponses(value = {@ApiResponse(code = 302, message = "User Found "),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(value = UserRestURIConstants.GET_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> getUserById(@PathVariable("id") final int userId) {
        User user;
        JSONObject jsonObject=new JSONObject();
        try{
            user=this.userService.findUserById(userId);
            if(user != null){
                jsonObject.put("user", user.toString());
                return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.FOUND);
            }
        }catch (SQLException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);

    }


    @ApiOperation(value = "Get the User based on UserName  ", httpMethod="GET"
            , notes = "Return the matched User")
    @ApiResponses(value = {@ApiResponse(code = 302, message = "User Found "),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(value = UserRestURIConstants.GET_USER_NAME, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> isUserNameExist(@PathVariable("userName") final String userName) {
        User user;
        JSONObject jsonObject=new JSONObject();
        try{
            user=this.userService.findUserByPropertyName(UserConstants.USER_NAME,userName);
            if(user != null){
                jsonObject.put("user", user.toString());
                return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.FOUND);
            }
        }catch (SQLException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);

    }


    @ApiOperation(value = "Get the User based on Email Id  ",  httpMethod="GET",
                  notes = "Return the matched User")
    @ApiResponses(value = {@ApiResponse(code = 302, message = "User Found "),
                           @ApiResponse(code = 404, message = "User not found"),
                           @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(value = UserRestURIConstants.GET_EMAIL_ID, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> isEmailIdExist(@PathVariable("emailId") final String emailId) {
        User user;
        JSONObject jsonObject=new JSONObject();
        try{
            user=this.userService.findUserByPropertyName(UserConstants.EMAIL_ID, emailId);
            if(user != null){
                jsonObject.put("user", user.toString());
                return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.FOUND);
            }
        }catch (SQLException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return  new ResponseEntity(HttpStatus.NOT_FOUND);
    }


    @ApiOperation(value = "Get the User based on Contact Number  ", httpMethod="GET",
            notes = "Return the matched User")
    @ApiResponses(value = {@ApiResponse(code = 302, message = "User Found "),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(value = UserRestURIConstants.GET_CONTACT_NUMBER, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> isContactNumberExist(@PathVariable("contactNumber") final Long contactNumber){
        User user;
        JSONObject jsonObject=new JSONObject();
        try{
            user=this.userService.findUserByPropertyName(UserConstants.CONTACT_NUMBER, contactNumber);
            if(user != null){
                jsonObject.put("user", user.toString());
                return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.FOUND);
            }
        }catch (SQLException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return  new ResponseEntity(HttpStatus.NOT_FOUND);
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
