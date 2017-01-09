package com.accion.recruitment.web.controller;

import com.accion.recruitment.common.constants.UserConstants;
import com.accion.recruitment.common.constants.UserRestURIConstants;
import com.accion.recruitment.common.enums.UserHttpStatusEnums;
import com.accion.recruitment.common.enums.UserEnums;
import com.accion.recruitment.jpa.entities.TechnicalScreenerSkills;
import com.accion.recruitment.jpa.entities.User;
import com.accion.recruitment.jpa.entities.UserSkills;
import com.accion.recruitment.service.UserEmailNotificationService;
import com.accion.recruitment.service.UserService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;
import io.swagger.annotations.*;
import org.json.JSONException;
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
    private UserEmailNotificationService userEmailNotificationService;

    private final Md5PasswordEncoder encoder = new Md5PasswordEncoder();

    private final Random random = new SecureRandom();

    private final SimpleDateFormat sdf = new SimpleDateFormat(UserConstants.DATE_FORMAT);


    @ApiOperation(value = "Create the new User ",  code = 201, httpMethod="POST")

    @ApiResponses(value = {@ApiResponse(code = 201, message = "User Created Successfully"),
                    @ApiResponse(code = 200, message = "Successful Respond Send")
            , @ApiResponse(code = 500, message = "Internal Server Error")})

    @RequestMapping(value = UserRestURIConstants.CREATE_USER, method = RequestMethod.POST)
@JsonIgnore
    public  ResponseEntity<String> createUser(@RequestBody UserSkills userSkills,
                              final @RequestParam(required = false, value = "userImage") MultipartFile userImage,
                              final @RequestParam(required = false, value = "userProfile") MultipartFile userProfile,
                              final Principal principal) {

        final Date currentDate = new Date();

        User user=new User();
        TechnicalScreenerSkills  technicalScreenerSkills=new TechnicalScreenerSkills();
        if(userSkills.getUser()!=null){
            user=userSkills.getUser();
        }
        if(userSkills.getTechnicalScreenerSkills()!=null){
            technicalScreenerSkills=userSkills.getTechnicalScreenerSkills();
        }

        List<TechnicalScreenerSkills> technicalScreenerSkillsList=new ArrayList<TechnicalScreenerSkills>();

        if(user != null && user.getUserName() != null && (!user.getUserName().isEmpty())){
            try{
                User userObject=this.userService.findUserByPropertyName(UserConstants.USER_NAME,user.getUserName());
                if(userObject != null)
                    return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.USER_NAME_EXIST.ResponseMsg()), HttpStatus.OK);
            }catch (SQLException e){
                    return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.DATABASE_EXCEPTION.ResponseMsg()), HttpStatus.OK);
            }catch (Exception e){
                    return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.RECORD_NOT_SAVED.ResponseMsg()), HttpStatus.OK);
            }
        }
        if(user != null && user.getEmailId() != null && (!user.getEmailId().isEmpty())){
            try{
                User userObject=this.userService.findUserByPropertyName(UserConstants.EMAIL_ID,user.getEmailId());
                if(userObject != null)
                    return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.EMAIlID_EXIST.ResponseMsg()), HttpStatus.OK);
            }catch (SQLException e){
                    return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.DATABASE_EXCEPTION.ResponseMsg()), HttpStatus.OK);
            }catch (Exception e){
                    return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.RECORD_NOT_SAVED.ResponseMsg()), HttpStatus.OK);
            }
        }
        if(user != null && user.getContactNumber() != null){
            try{
                User userObject=this.userService.findUserByPropertyName(UserConstants.CONTACT_NUMBER,user.getContactNumber());
                if(userObject != null)
                    return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.CONTACT_NUMBER_EXIST.ResponseMsg()), HttpStatus.OK);
            }catch (SQLException e){
                    return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.DATABASE_EXCEPTION.ResponseMsg()), HttpStatus.OK);
            }catch (Exception e){
                    return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.RECORD_NOT_SAVED.ResponseMsg()), HttpStatus.OK);
            }
        }
        if (user != null && userImage != null && !userImage.isEmpty()) {
            try {
                byte[] bytes = userImage.getBytes();
                user.setUserImage(bytes);
            } catch (IOException e) {
                return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.USER_IMAGE_EXCEPTION.ResponseMsg()), HttpStatus.OK);
            }catch (Exception e){
                return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.RECORD_NOT_SAVED.ResponseMsg()), HttpStatus.OK);
            }
        }
        if (user != null && userProfile != null && !userProfile.isEmpty()) {
            try {
                byte[] bytes = userProfile.getBytes();
                user.setUserProfile(bytes);
            } catch (IOException e) {
                return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.USER_PROFILE_EXCEPTION.ResponseMsg()), HttpStatus.OK);
            }catch (Exception e){
                return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.RECORD_NOT_SAVED.ResponseMsg()), HttpStatus.OK);
            }
        }

        String password=this.generatePassword();
        user.setPassword(this.encoder.encodePassword(password, null));
        try{
            user.setCreatedBy(principal.getName());
            user.setUpdatedBy(principal.getName());
        }catch (Exception e){

        }

        user.setCreatedDate(new Date(sdf.format(currentDate)));
        user.setUpdatedDate(new Date(sdf.format(currentDate)));



        if(user.getRole().equalsIgnoreCase(UserEnums.TechnicalScreener.toString())){
            try{
                technicalScreenerSkillsList=this.getTechnicalSkillsObjectList(technicalScreenerSkills);
                user.getTechnicalScreenerDetailsDSkillsSet().addAll(technicalScreenerSkillsList);
                if(this.userService.saveUserGroups(user)){
                    user.setPassword(password);
                    if(this.userEmailNotificationService.sendUserCredentials(user)){
                        return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.RECORD_SAVED_EMAIL_SEND.ResponseMsg()), HttpStatus.CREATED);
                    }else{
                        return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.RECORD_SAVED_EMAIL_NOT_SEND.ResponseMsg()), HttpStatus.CREATED);
                    }
                }else{
                    return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.RECORD_NOT_SAVED.ResponseMsg()), HttpStatus.OK);
                }
            }catch (ArrayIndexOutOfBoundsException e){
                return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.USER_SKILLS_EXCEPTION.ResponseMsg()), HttpStatus.OK);
            }catch (SQLException e){
                return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.DATABASE_EXCEPTION.ResponseMsg()), HttpStatus.OK);
            }catch (Exception e){
                return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.RECORD_NOT_SAVED.ResponseMsg()), HttpStatus.OK);
            }
        }else{
            try{
                if(this.userService.saveUserGroups(user)){
                    user.setPassword(password);
                    if(this.userEmailNotificationService.sendUserCredentials(user)){
                        return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.RECORD_SAVED_EMAIL_SEND.ResponseMsg()), HttpStatus.CREATED);
                    }else{
                        return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.RECORD_SAVED_EMAIL_NOT_SEND.ResponseMsg()), HttpStatus.CREATED);
                    }
                }else{
                    return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.RECORD_NOT_SAVED.ResponseMsg()), HttpStatus.OK);
                }
            }catch (SQLException e){
                return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.DATABASE_EXCEPTION.ResponseMsg()), HttpStatus.OK);
            }catch (Exception e){
                return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.RECORD_NOT_SAVED.ResponseMsg()), HttpStatus.OK);
            }
        }
    }



    @ApiOperation(value = "Get All the Users  ", httpMethod="GET")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Users Found "),
            @ApiResponse(code = 500, message = "Internal Server Error")})

    @RequestMapping(value = UserRestURIConstants.GET_ALL_USER, produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.GET)
    @ResponseBody
    @JsonIgnore
    public ResponseEntity<List<User>> getAllUsers() throws JSONException {

        try{
            List<User> userList=this.userService.findAllUser();

           /* List<User> userList1=new ArrayList<User>();
            JSONObject responseDetailsJson = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            User user=new User();
            for(User userObject:userList){

                *//* new User(userObject.getId(),userObject.getUserName(),userObject.getEmailId(),userObject.getFirstName(),userObject.getLastName(),userObject.getContactNumber(),
                        userObject.getRole(),userObject.getEnabled(),userObject.getErrorMessage());
                *//*user= new User(userObject.getId(),userObject.getFirstName(),userObject.getLastName(),userObject.getUserName(),userObject.getEmailId(),userObject.getEnabled(),
                        userObject.getContactNumber(),userObject.getRole(),userObject.getAlternateContact(),userObject.getAddressOne(),userObject.getAddressTwo(),
                        userObject.getZipCode(),userObject.getCity(),userObject.getState(),userObject.getCountry(),userObject.getExpectedPayRange(),userObject.getUserImage(),
                        userObject.getUserProfile(),userObject.getErrorMessage(),userObject.getGroupsSet(),userObject.getTechnicalScreenerDetailsDSkillsSet());
                userList1.add(user);
                jsonArray.put(user.toString());
            }

            responseDetailsJson.put("users", jsonArray);*/
            return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
        }catch (SQLException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @ApiOperation(value = "Enable OR Disable the User  ", httpMethod="PUT")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "User Status Changed "),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(value = UserRestURIConstants.CHANGE_STATUS, produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<String> changeStatus(@PathVariable("id") final int userId,
                                               @PathVariable("status") String status) {

        User user;
        try{
            user=this.userService.findUserById(userId);
            if(user != null){
                if(status.equalsIgnoreCase(UserConstants.TRUE)){
                    user.setEnabled(Boolean.FALSE);
                    if(this.userService.saveUser(user)){
                        if(this.userEmailNotificationService.sendUserDisableStatus(user)){
                            return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.USER_DISABLED_EMAIL_SEND.ResponseMsg()), HttpStatus.OK);
                        }else {
                            return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.USER_DISABLED_EMAIL_NOT_SEND.ResponseMsg()), HttpStatus.OK);
                        }
                    }else{
                        return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.USER_STATUS_NOT_CHANGED.ResponseMsg()), HttpStatus.OK);
                    }
                }else if(status.equalsIgnoreCase(UserConstants.FALSE)){
                    user.setEnabled(Boolean.TRUE);
                    if(this.userService.saveUser(user)){
                        if(this.userEmailNotificationService.sendUserEnableStatus(user)){
                            return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.USER_ENABLED_EMAIL_SEND.ResponseMsg()), HttpStatus.OK);
                        }else {
                            return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.USER_ENABLED_EMAIL_NOT_SEND.ResponseMsg()), HttpStatus.OK);
                        }
                    }else{
                        return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.USER_STATUS_NOT_CHANGED.ResponseMsg()), HttpStatus.OK);
                    }
                }

            }
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (SQLException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @ApiOperation(value = "Reset the User password ", httpMethod="PUT")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Password Changed "),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(value = UserRestURIConstants.RESET_PASSWORD, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<String> resetPassword(@PathVariable("id") final int userId) {

        User user;
        try{
            user=this.userService.findUserById(userId);
            if(user != null){
                String password=this.generatePassword();
                user.setPassword(this.encoder.encodePassword(password, null));
                if(this.userService.saveUser(user)){
                    user.setPassword(password);
                    if(this.userEmailNotificationService.sendUserResetPassword(user)){
                        return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.PASSWORD_CHANGED_EMAIL_SEND.ResponseMsg()), HttpStatus.OK);
                    }else{
                        return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.PASSWORD_CHANGED_EMAIL_NOT_SEND.ResponseMsg()), HttpStatus.OK);
                    }
                }else{
                    return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.PASSWORD_NOT_CHANGED.ResponseMsg()), HttpStatus.OK);
                }
            }
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (SQLException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    @ApiOperation(value = "Get the User based on ID  ", httpMethod="GET"
            , notes = "Return the matched User")
    @ApiResponses(value = {@ApiResponse(code = 302, message = "User Found "),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(value = UserRestURIConstants.GET_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> getUserById(@PathVariable("id") final int userId) {
        User userObject;
        try{
            userObject=this.userService.findUserById(userId);
            if(userObject != null){
                User user=new User(userObject.getId(),userObject.getUserName(),userObject.getEmailId(),userObject.getFirstName(),userObject.getLastName(),userObject.getContactNumber(),
                        userObject.getRole(),userObject.getEnabled(),userObject.getErrorMessage());
                return new ResponseEntity<Object>(user, HttpStatus.OK);
            }
        }catch (SQLException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);

    }


    @ApiOperation(value = "Get the User based on UserName  ", httpMethod="GET"
            , notes = "Return the matched User")
    @ApiResponses(value = {@ApiResponse(code = 302, message = "User Found "),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(value = UserRestURIConstants.GET_USER_NAME, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> isUserNameExist(@PathVariable("userName") final String userName) {
        User userObject;
        try{
            userObject=this.userService.findUserByPropertyName(UserConstants.USER_NAME,userName);
            if(userObject != null){
                User user=new User(userObject.getId(),userObject.getUserName(),userObject.getEmailId(),userObject.getFirstName(),userObject.getLastName(),userObject.getContactNumber(),
                        userObject.getRole(),userObject.getEnabled(),userObject.getErrorMessage());
                return new ResponseEntity<Object>(user, HttpStatus.OK);
            }
        }catch (SQLException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);

    }


    @ApiOperation(value = "Get the User based on Email Id  ",  httpMethod="GET",
                  notes = "Return the matched User")
    @ApiResponses(value = {@ApiResponse(code = 302, message = "User Found "),
                           @ApiResponse(code = 404, message = "User not found"),
                           @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(value = UserRestURIConstants.GET_EMAIL_ID, produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> isEmailIdExist(@PathVariable("emailId") final String emailId) {
        User userObject;
        try{
            userObject=this.userService.findUserByPropertyName(UserConstants.EMAIL_ID, emailId);
            if(userObject != null){
                User user=new User(userObject.getId(),userObject.getUserName(),userObject.getEmailId(),userObject.getFirstName(),userObject.getLastName(),userObject.getContactNumber(),
                        userObject.getRole(),userObject.getEnabled(),userObject.getErrorMessage());
                return new ResponseEntity<Object>(user, HttpStatus.OK);
            }
        }catch (SQLException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return  new ResponseEntity(HttpStatus.OK);
    }


    @ApiOperation(value = "Get the User based on Contact Number  ", httpMethod="GET",
            notes = "Return the matched User")
    @ApiResponses(value = {@ApiResponse(code = 302, message = "User Found "),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(value = UserRestURIConstants.GET_CONTACT_NUMBER, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> isContactNumberExist(@PathVariable("contactNumber") final String contactNumber){
        User userObject;
        try{
            userObject=this.userService.findUserByPropertyName(UserConstants.CONTACT_NUMBER, contactNumber);
            if(userObject != null){
                User user=new User(userObject.getId(),userObject.getUserName(),userObject.getEmailId(),userObject.getFirstName(),userObject.getLastName(),userObject.getContactNumber(),
                        userObject.getRole(),userObject.getEnabled(),userObject.getErrorMessage());
                return new ResponseEntity<Object>(user, HttpStatus.OK);
            }
        }catch (SQLException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return  new ResponseEntity(HttpStatus.OK);
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
