package com.accion.recruitment.web.controller;

import com.accion.recruitment.common.constants.HookedOnConstants;
import com.accion.recruitment.common.constants.LoginRestURIConstants;
import com.accion.recruitment.common.constants.UserConstants;
import com.accion.recruitment.common.constants.UserRestURIConstants;
import com.accion.recruitment.common.enums.UserHttpStatusEnums;
import com.accion.recruitment.common.enums.UserEnums;
import com.accion.recruitment.common.helper.PasswordGeneratorHelper;
import com.accion.recruitment.jpa.entities.TechnicalScreenerSkills;
import com.accion.recruitment.jpa.entities.User;
import com.accion.recruitment.beans.UserSkills;
import com.accion.recruitment.service.UserEmailNotificationService;
import com.accion.recruitment.service.UserService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
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

    private PasswordGeneratorHelper passwordGeneratorHelper=new PasswordGeneratorHelper();

    private final SimpleDateFormat sdf = new SimpleDateFormat(UserConstants.DATE_FORMAT);


    @ApiOperation(value = "Create the new User ",  code = 201, httpMethod="POST")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "User Created Successfully"),
                    @ApiResponse(code = 200, message = "Successful Respond Send")
            , @ApiResponse(code = 500, message = "Internal Server Error")})

    @RequestMapping(value = UserRestURIConstants.CREATE_USER,produces = MediaType.APPLICATION_JSON_VALUE ,method = RequestMethod.POST)
    public  ResponseEntity<String> createUser(@RequestBody UserSkills userSkills,
                                                final Principal principal) {

        try{
            final Date currentDate = new Date();
            User user;
            HashMap<String,Object>   technicalScreenerSkills;
            List<TechnicalScreenerSkills> technicalScreenerSkillsList;
            HashMap<String,String> userDetailsExistMap;

            if(userSkills.getUser()!=null){
                user=userSkills.getUser();
            }else{
                return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.USER_NOT_SAVED.ResponseMsg()), HttpStatus.OK);
            }

            userDetailsExistMap=this.checkUserDetailsExist(user);

            if(userDetailsExistMap.containsKey(HookedOnConstants.EXIST)){
                return new ResponseEntity<String>(new Gson().toJson(userDetailsExistMap.get(HookedOnConstants.EXIST)), HttpStatus.OK);
            }

            if (user != null && userSkills.getUserImage() != null && !userSkills.getUserImage().isEmpty()) {
                try {
                    byte[] bytes = userSkills.getUserImage().getBytes();
                    user.setUserImage(bytes);
                } catch (IOException e) {
                    return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.USER_IMAGE_EXCEPTION.ResponseMsg()), HttpStatus.OK);
                }catch (Exception e){
                    return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.USER_NOT_SAVED.ResponseMsg()), HttpStatus.OK);
                }
            }
            if (user != null && userSkills.getUserProfile() != null && !userSkills.getUserProfile().isEmpty()) {
                try {
                    byte[] bytes = userSkills.getUserProfile().getBytes();
                    user.setUserProfile(bytes);
                } catch (IOException e) {
                    return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.USER_PROFILE_EXCEPTION.ResponseMsg()), HttpStatus.OK);
                }catch (Exception e){
                    return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.USER_NOT_SAVED.ResponseMsg()), HttpStatus.OK);
                }
            }

            String password=this.passwordGeneratorHelper.generatePassword();
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
                    if(userSkills.getTechnicalScreenerSkills()!=null){
                        technicalScreenerSkills=userSkills.getTechnicalScreenerSkills();
                        technicalScreenerSkillsList=this.getTechnicalSkillsObject(technicalScreenerSkills);
                        user.getTechnicalScreenerDetailsDSkillsSet().addAll(technicalScreenerSkillsList);
                        if(this.userService.saveUserGroups(user)){
                            user.setPassword(password);
                            if(this.userEmailNotificationService.sendUserCredentials(user)){
                                return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.USER_SAVED_EMAIL_SEND.ResponseMsg()), HttpStatus.CREATED);
                            }else{
                                return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.USER_SAVED_EMAIL_NOT_SEND.ResponseMsg()), HttpStatus.CREATED);
                            }
                        }else{
                            return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.USER_NOT_SAVED.ResponseMsg()), HttpStatus.OK);
                        }
                    }else{
                        return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.USER_SKILLS_EXCEPTION.ResponseMsg()), HttpStatus.OK);
                    }

                }catch (ArrayIndexOutOfBoundsException e){
                    return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.USER_SKILLS_EXCEPTION.ResponseMsg()), HttpStatus.OK);
                }catch (SQLException e){
                    return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.DATABASE_EXCEPTION.ResponseMsg()), HttpStatus.OK);
                }catch (Exception e){
                    return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.USER_NOT_SAVED.ResponseMsg()), HttpStatus.OK);
                }
            }else{
                try{
                    if(this.userService.saveUserGroups(user)){
                        user.setPassword(password);
                        if(this.userEmailNotificationService.sendUserCredentials(user)){
                            return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.USER_SAVED_EMAIL_SEND.ResponseMsg()), HttpStatus.CREATED);
                        }else{
                            return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.USER_SAVED_EMAIL_NOT_SEND.ResponseMsg()), HttpStatus.CREATED);
                        }
                    }else{
                        return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.USER_NOT_SAVED.ResponseMsg()), HttpStatus.OK);
                    }
                }catch (SQLException e){
                    return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.DATABASE_EXCEPTION.ResponseMsg()), HttpStatus.OK);
                }catch (Exception e){
                    return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.USER_NOT_SAVED.ResponseMsg()), HttpStatus.OK);
                }
            }

        }catch (Exception e){
            return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.USER_NOT_SAVED.ResponseMsg()), HttpStatus.OK);
        }
    }




    @ApiOperation(value = "Get All the Users  ", httpMethod="GET")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Users Found "),
            @ApiResponse(code = 500, message = "Internal Server Error")})

    @RequestMapping(value = UserRestURIConstants.GET_ALL_USER, produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.GET)
    @ResponseBody
    @JsonIgnore
    public ResponseEntity<LinkedHashSet<User>> getAllUsers(){

        try{
            List<User> userList=this.userService.findAllUser();
            LinkedHashSet<User> users=new LinkedHashSet<User>();
            for(User userObject:userList){
                 User user= new User(userObject.getId(),userObject.getFirstName(),userObject.getLastName(),userObject.getUserName(),userObject.getEmailId(),userObject.getEnabled(),
                        userObject.getContactNumber(),userObject.getRole(),userObject.getAlternateContact(),userObject.getAddressOne(),userObject.getAddressTwo(),
                        userObject.getZipCode(),userObject.getCity(),userObject.getState(),userObject.getCountry(),userObject.getExpectedPayRange(),userObject.getUserImage(),
                        userObject.getUserProfile(),userObject.getErrorMessage(),userObject.getTechnicalScreenerDetailsDSkillsSet());
                users.add(user);
            }
            return new ResponseEntity<LinkedHashSet<User>>(users, HttpStatus.OK);
        }catch (SQLException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @ApiOperation(value = "Update/Edit the  User ",  code = 200, httpMethod="PUT")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Updated User Successfully")
            , @ApiResponse(code = 500, message = "Internal Server Error")})

    @RequestMapping(value = UserRestURIConstants.UPDATE_USER, method = RequestMethod.PUT)
    @JsonIgnore
    public  ResponseEntity<String> updateUser(@RequestBody UserSkills userSkills,
                                              final Principal principal) {

        try{
            final Date currentDate = new Date();
            User user;
            User oldUser;
            HashMap<String,Object>   technicalScreenerSkills;
            List<TechnicalScreenerSkills> technicalScreenerSkillsList;

            if(userSkills.getUser()!=null){
                user=userSkills.getUser();
            }else{
                return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.USER_NOT_UPDATED.ResponseMsg()), HttpStatus.OK);
            }

            try{
                oldUser=this.userService.findUserById(user.getId());
                if(oldUser==null)
                    return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.USER_NOT_UPDATED.ResponseMsg()), HttpStatus.OK);
            }catch (Exception e){
                return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.USER_NOT_UPDATED.ResponseMsg()), HttpStatus.OK);
            }

            if(user != null && user.getUserName() != null && (!user.getUserName().isEmpty()) && (!oldUser.getUserName().equals(user.getUserName()))){
                try{
                    User userObject=this.userService.findUserByPropertyName(UserConstants.USER_NAME,user.getUserName());
                    if(userObject != null)
                        return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.USER_NAME_EXIST.ResponseMsg()), HttpStatus.OK);
                }catch (SQLException e){
                    return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.DATABASE_EXCEPTION.ResponseMsg()), HttpStatus.OK);
                }catch (Exception e){
                    return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.USER_NOT_UPDATED.ResponseMsg()), HttpStatus.OK);
                }
            }
            if(user != null && user.getEmailId() != null && (!user.getEmailId().isEmpty())  && (!oldUser.getEmailId().equals(user.getEmailId()))){
                try{
                    User userObject=this.userService.findUserByPropertyName(UserConstants.EMAIL_ID,user.getEmailId());
                    if(userObject != null)
                        return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.EMAIlID_EXIST.ResponseMsg()), HttpStatus.OK);
                }catch (SQLException e){
                    return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.DATABASE_EXCEPTION.ResponseMsg()), HttpStatus.OK);
                }catch (Exception e){
                    return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.USER_NOT_UPDATED.ResponseMsg()), HttpStatus.OK);
                }
            }
            if(user != null && user.getContactNumber() != null && (!user.getContactNumber().isEmpty()) &&(!oldUser.getContactNumber().equals(user.getContactNumber()))){
                try{
                    User userObject=this.userService.findUserByPropertyName(UserConstants.CONTACT_NUMBER,user.getContactNumber());
                    if(userObject != null)
                        return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.CONTACT_NUMBER_EXIST.ResponseMsg()), HttpStatus.OK);
                }catch (SQLException e){
                    return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.DATABASE_EXCEPTION.ResponseMsg()), HttpStatus.OK);
                }catch (Exception e){
                    return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.USER_NOT_UPDATED.ResponseMsg()), HttpStatus.OK);
                }
            }
            if (user != null && userSkills.getUserImage() != null && !userSkills.getUserImage().isEmpty()) {
                try {
                    byte[] bytes = userSkills.getUserImage().getBytes();
                    user.setUserImage(bytes);
                } catch (IOException e) {
                    return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.USER_IMAGE_EXCEPTION.ResponseMsg()), HttpStatus.OK);
                }catch (Exception e){
                    return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.USER_NOT_UPDATED.ResponseMsg()), HttpStatus.OK);
                }
            }else{
                user.setUserImage(oldUser.getUserImage());
            }
            if (user != null && userSkills.getUserProfile() != null && !userSkills.getUserProfile().isEmpty()) {
                try {
                    byte[] bytes = userSkills.getUserProfile().getBytes();
                    user.setUserProfile(bytes);
                } catch (IOException e) {
                    return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.USER_PROFILE_EXCEPTION.ResponseMsg()), HttpStatus.OK);
                }catch (Exception e){
                    return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.USER_NOT_UPDATED.ResponseMsg()), HttpStatus.OK);
                }
            }else{
                user.setUserProfile(oldUser.getUserProfile());
            }

            try{
                user.setId(oldUser.getId());
                user.setPassword(oldUser.getPassword());
                user.setCreatedBy(oldUser.getCreatedBy());
                user.setCreatedDate(oldUser.getCreatedDate());
                user.setUpdatedBy(principal.getName());
            }catch (Exception e){

            }
            user.setUpdatedDate(new Date(sdf.format(currentDate)));

            if(user.getRole().equals(oldUser.getRole())){
                if(userSkills.getTechnicalScreenerSkills()!=null){
                    technicalScreenerSkills=userSkills.getTechnicalScreenerSkills();
                    technicalScreenerSkillsList=this.getTechnicalSkillsObject(technicalScreenerSkills);
                    user.getTechnicalScreenerDetailsDSkillsSet().addAll(technicalScreenerSkillsList);
                    if(this.userService.saveUser(user)){
                        return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.USER_UPDATED.ResponseMsg()), HttpStatus.OK);
                    }else {
                        return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.USER_NOT_UPDATED.ResponseMsg()), HttpStatus.OK);
                    }
                }else{
                    if(this.userService.saveUser(user)){
                        return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.USER_UPDATED.ResponseMsg()), HttpStatus.OK);
                    }else {
                        return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.USER_NOT_UPDATED.ResponseMsg()), HttpStatus.OK);
                    }
                }

            }else{
                String deleteUserGroupQuery="delete  FROM default.user_group where userSet_id="+oldUser.getId();
                if(this.userService.deleteRecordByQuery(deleteUserGroupQuery)){
                    if(user.getRole().equalsIgnoreCase(UserEnums.TechnicalScreener.toString())){
                        try{
                            if(userSkills.getTechnicalScreenerSkills()!=null){
                                technicalScreenerSkills=userSkills.getTechnicalScreenerSkills();
                                technicalScreenerSkillsList=this.getTechnicalSkillsObject(technicalScreenerSkills);
                                user.getTechnicalScreenerDetailsDSkillsSet().addAll(technicalScreenerSkillsList);
                                if(this.userService.saveUserGroups(user)){
                                    return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.USER_UPDATED.ResponseMsg()), HttpStatus.OK);
                                }else {
                                    return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.USER_NOT_UPDATED.ResponseMsg()), HttpStatus.OK);
                                }
                            }else{
                                return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.USER_SKILLS_EXCEPTION.ResponseMsg()), HttpStatus.OK);
                            }
                        }catch (ArrayIndexOutOfBoundsException e){
                            return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.USER_SKILLS_EXCEPTION.ResponseMsg()), HttpStatus.OK);
                        }catch (SQLException e){
                            return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.DATABASE_EXCEPTION.ResponseMsg()), HttpStatus.OK);
                        }catch (Exception e){
                            return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.USER_NOT_UPDATED.ResponseMsg()), HttpStatus.OK);
                        }
                    }else{
                        try{
                            if(this.userService.saveUserGroups(user)){
                                return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.USER_UPDATED.ResponseMsg()), HttpStatus.OK);
                            }else{
                                return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.USER_NOT_UPDATED.ResponseMsg()), HttpStatus.OK);
                            }
                        }catch (SQLException e){
                            return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.DATABASE_EXCEPTION.ResponseMsg()), HttpStatus.OK);
                        }catch (Exception e){
                            return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.USER_NOT_UPDATED.ResponseMsg()), HttpStatus.OK);
                        }
                    }
                }
            }
        }catch (Exception e){
            return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.USER_NOT_UPDATED.ResponseMsg()), HttpStatus.OK);
        }
        return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.USER_NOT_UPDATED.ResponseMsg()), HttpStatus.OK);
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
                String password=this.passwordGeneratorHelper.generatePassword();
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
    @ApiResponses(value = {@ApiResponse(code = 200, message = "User Found "),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(value = UserRestURIConstants.GET_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> getUserById(@PathVariable("id") final int userId) {
        User userObject;
        try{
            userObject=this.userService.findUserById(userId);
            if(userObject != null){
                User user= new User(userObject.getId(),userObject.getFirstName(),userObject.getLastName(),userObject.getUserName(),userObject.getEmailId(),userObject.getEnabled(),
                        userObject.getContactNumber(),userObject.getRole(),userObject.getAlternateContact(),userObject.getAddressOne(),userObject.getAddressTwo(),
                        userObject.getZipCode(),userObject.getCity(),userObject.getState(),userObject.getCountry(),userObject.getExpectedPayRange(),userObject.getUserImage(),
                        userObject.getUserProfile(),userObject.getErrorMessage(),userObject.getTechnicalScreenerDetailsDSkillsSet());

                  return new ResponseEntity<Object>(user, HttpStatus.OK);
            }
        }catch (SQLException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

    }


    @ApiOperation(value = "Get the User based on UserName  ", httpMethod="GET"
            , notes = "Return the matched User")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "User Found "),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(value = UserRestURIConstants.GET_USER_NAME, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> isUserNameExist(@PathVariable("userName") final String userName) {
        User userObject;
        try{
            userObject=this.userService.findUserByPropertyName(UserConstants.USER_NAME,userName);
            if(userObject != null){
                User user= new User(userObject.getId(),userObject.getFirstName(),userObject.getLastName(),userObject.getUserName(),userObject.getEmailId(),userObject.getEnabled(),
                        userObject.getContactNumber(),userObject.getRole(),userObject.getAlternateContact(),userObject.getAddressOne(),userObject.getAddressTwo(),
                        userObject.getZipCode(),userObject.getCity(),userObject.getState(),userObject.getCountry(),userObject.getExpectedPayRange(),userObject.getUserImage(),
                        userObject.getUserProfile(),userObject.getErrorMessage(),userObject.getTechnicalScreenerDetailsDSkillsSet());
                return new ResponseEntity<Object>(user, HttpStatus.OK);
            }
        }catch (SQLException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

    }


    @ApiOperation(value = "Get the User based on Email Id  ",  httpMethod="GET",
                  notes = "Return the matched User")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "User Found "),
                           @ApiResponse(code = 404, message = "User not found"),
                           @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(value = UserRestURIConstants.GET_EMAIL_ID, produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> isEmailIdExist(@PathVariable("emailId") final String emailId) {
        User userObject;
        try{
            userObject=this.userService.findUserByPropertyName(UserConstants.EMAIL_ID, emailId);
            if(userObject != null){
                User user= new User(userObject.getId(),userObject.getFirstName(),userObject.getLastName(),userObject.getUserName(),userObject.getEmailId(),userObject.getEnabled(),
                        userObject.getContactNumber(),userObject.getRole(),userObject.getAlternateContact(),userObject.getAddressOne(),userObject.getAddressTwo(),
                        userObject.getZipCode(),userObject.getCity(),userObject.getState(),userObject.getCountry(),userObject.getExpectedPayRange(),userObject.getUserImage(),
                        userObject.getUserProfile(),userObject.getErrorMessage(),userObject.getTechnicalScreenerDetailsDSkillsSet());
                return new ResponseEntity<Object>(user, HttpStatus.OK);
            }
        }catch (SQLException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return  new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ApiOperation(value = "Get the User based on Contact Number  ", httpMethod="GET",
            notes = "Return the matched User")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "User Found "),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(value = UserRestURIConstants.GET_CONTACT_NUMBER, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> isContactNumberExist(@PathVariable("contactNumber") final String contactNumber){
        User userObject;
        try{
            userObject=this.userService.findUserByPropertyName(UserConstants.CONTACT_NUMBER, contactNumber);
            if(userObject != null){
                User user= new User(userObject.getId(),userObject.getFirstName(),userObject.getLastName(),userObject.getUserName(),userObject.getEmailId(),userObject.getEnabled(),
                        userObject.getContactNumber(),userObject.getRole(),userObject.getAlternateContact(),userObject.getAddressOne(),userObject.getAddressTwo(),
                        userObject.getZipCode(),userObject.getCity(),userObject.getState(),userObject.getCountry(),userObject.getExpectedPayRange(),userObject.getUserImage(),
                        userObject.getUserProfile(),userObject.getErrorMessage(),userObject.getTechnicalScreenerDetailsDSkillsSet());
                return new ResponseEntity<Object>(user, HttpStatus.OK);
            }
        }catch (SQLException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return  new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ApiOperation(value = "Get the Users based on Role  ", httpMethod="GET")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Users Found "),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(value = UserRestURIConstants.GET_ROLE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<LinkedHashSet<User>> findAllUserByRole(@PathVariable("role") final String role){

        try{
            List<User> userList=this.userService.findUserByRole(role);
            LinkedHashSet<User> users=new LinkedHashSet<User>();
            for(User userObject:userList){
                User user= new User(userObject.getId(),userObject.getFirstName(),userObject.getLastName(),userObject.getUserName(),userObject.getEmailId(),userObject.getEnabled(),
                        userObject.getContactNumber(),userObject.getRole(),userObject.getAlternateContact(),userObject.getAddressOne(),userObject.getAddressTwo(),
                        userObject.getZipCode(),userObject.getCity(),userObject.getState(),userObject.getCountry(),userObject.getExpectedPayRange(),userObject.getUserImage(),
                        userObject.getUserProfile(),userObject.getErrorMessage(),userObject.getTechnicalScreenerDetailsDSkillsSet());
                users.add(user);
            }
            return new ResponseEntity<LinkedHashSet<User>>(users, HttpStatus.OK);
        }catch (SQLException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @ApiOperation(value = "Change the Password   ", httpMethod="POST")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Password Changed Successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error")})

    @RequestMapping(value = UserRestURIConstants.CHANGE_PASSWORD, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> changePassword(@PathVariable("currentPassword") final String currentPassword,
                                                 @PathVariable("newPassword") final String newPassword,
                                                 Principal principal) {
        User userObject;
        try{
            userObject=this.userService.findUserByPropertyName(UserConstants.USER_NAME, principal.getName());
            if(userObject!=null){
                if(userObject.getPassword().equals(encoder.encodePassword(currentPassword, null))){
                    userObject.setPassword(encoder.encodePassword(newPassword, null));
                    this.userService.saveUser(userObject);
                    return new ResponseEntity<Object>(UserHttpStatusEnums.PASSWORD_CHANGED, HttpStatus.OK);
                }else{
                    return new ResponseEntity<Object>(UserHttpStatusEnums.PASSWORD_NOT_MATCHED, HttpStatus.OK);
                }
            }
        }catch (SQLException e){
            return new ResponseEntity<Object>(UserHttpStatusEnums.DATABASE_EXCEPTION.ResponseMsg(), HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            return new ResponseEntity<Object>(UserHttpStatusEnums.LOGIN_ERROR.ResponseMsg(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Object>(UserHttpStatusEnums.PASSWORD_NOT_MATCHED, HttpStatus.OK);
    }


    public  HashMap<String,String> checkUserDetailsExist(User user){
        HashMap userDetailsMap=new HashMap<String,String>();

        if(user != null && user.getUserName() != null && (!user.getUserName().isEmpty())){
            try{
                User userObject=this.userService.findUserByPropertyName(UserConstants.USER_NAME,user.getUserName());
                if(userObject != null){
                    userDetailsMap.put(HookedOnConstants.EXIST, UserHttpStatusEnums.USER_NAME_EXIST.ResponseMsg());
                    return userDetailsMap;
                }

            }catch (SQLException e){
                userDetailsMap.put(HookedOnConstants.EXIST, UserHttpStatusEnums.DATABASE_EXCEPTION.ResponseMsg());
                return userDetailsMap;
            }catch (Exception e){
                userDetailsMap.put(HookedOnConstants.EXIST, UserHttpStatusEnums.USER_NOT_SAVED.ResponseMsg());
                return userDetailsMap;
            }
        }
        if(user != null && user.getEmailId() != null && (!user.getEmailId().isEmpty())){
            try{
                User userObject=this.userService.findUserByPropertyName(UserConstants.EMAIL_ID,user.getEmailId());
                if(userObject != null){
                    userDetailsMap.put(HookedOnConstants.EXIST, UserHttpStatusEnums.EMAIlID_EXIST.ResponseMsg());
                    return userDetailsMap;
                }

            }catch (SQLException e){
                userDetailsMap.put(HookedOnConstants.EXIST, UserHttpStatusEnums.DATABASE_EXCEPTION.ResponseMsg());
                return userDetailsMap;
            }catch (Exception e){
                userDetailsMap.put(HookedOnConstants.EXIST, UserHttpStatusEnums.USER_NOT_SAVED.ResponseMsg());
                return userDetailsMap;
            }
        }
        if(user != null && user.getContactNumber() != null && (!user.getContactNumber().isEmpty())){
            try{
                User userObject=this.userService.findUserByPropertyName(UserConstants.CONTACT_NUMBER,user.getContactNumber());
                if(userObject != null){
                    userDetailsMap.put(HookedOnConstants.EXIST, UserHttpStatusEnums.CONTACT_NUMBER_EXIST.ResponseMsg());
                    return userDetailsMap;
                }

            }catch (SQLException e){
                userDetailsMap.put(HookedOnConstants.EXIST, UserHttpStatusEnums.DATABASE_EXCEPTION.ResponseMsg());
                return userDetailsMap;
            }catch (Exception e){
                userDetailsMap.put(HookedOnConstants.EXIST, UserHttpStatusEnums.USER_NOT_SAVED.ResponseMsg());
                return userDetailsMap;
            }
        }
        userDetailsMap.put(HookedOnConstants.NOT_EXIST, "");
        return userDetailsMap;
    }


    public final List<TechnicalScreenerSkills> getTechnicalSkillsObject(HashMap<String,Object> technicalScreenerSkillsMap) throws ArrayIndexOutOfBoundsException{

        List<HashMap<String, Object>> primarySkillsArrayList;
        List<HashMap<String, Object>>  secondarySkillsArrayList;
        List<TechnicalScreenerSkills> technicalScreenerSkillsList=new ArrayList<TechnicalScreenerSkills>();

        if(technicalScreenerSkillsMap.containsKey("primarySkills")){
            primarySkillsArrayList= (List<HashMap<String, Object>>) technicalScreenerSkillsMap.get("primarySkills");
            for(int i=0;i<primarySkillsArrayList.size();i++){
                HashMap hashMap = primarySkillsArrayList.get(i);
                if(hashMap.containsKey("skills") && hashMap.containsKey("years") && hashMap.containsKey("months")){
                    TechnicalScreenerSkills technicalScreenerSkills=new TechnicalScreenerSkills();
                    technicalScreenerSkills.setSkills(hashMap.get("skills").toString());
                    technicalScreenerSkills.setYears(Long.valueOf(hashMap.get("years").toString()));
                    technicalScreenerSkills.setMonths(Long.valueOf(hashMap.get("months").toString()));
                    technicalScreenerSkills.setSkillType(UserEnums.PrimarySkill.toString());
                    technicalScreenerSkillsList.add(technicalScreenerSkills);
                }
            }
        }
        if(technicalScreenerSkillsMap.containsKey("secondarySkills")){
            secondarySkillsArrayList= (List<HashMap<String, Object>>) technicalScreenerSkillsMap.get("secondarySkills");
            for(int i=0;i<secondarySkillsArrayList.size();i++){
                HashMap hashMap = secondarySkillsArrayList.get(i);
                if(hashMap.containsKey("skills") && hashMap.containsKey("years") && hashMap.containsKey("months")){
                    TechnicalScreenerSkills technicalScreenerSkills=new TechnicalScreenerSkills();
                    technicalScreenerSkills.setSkills(hashMap.get("skills").toString());
                    technicalScreenerSkills.setYears(Long.valueOf(hashMap.get("years").toString()));
                    technicalScreenerSkills.setMonths(Long.valueOf(hashMap.get("months").toString()));
                    technicalScreenerSkills.setSkillType(UserEnums.SecondarySkill.toString());
                    technicalScreenerSkillsList.add(technicalScreenerSkills);
                }
            }
        }

        return technicalScreenerSkillsList;
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
