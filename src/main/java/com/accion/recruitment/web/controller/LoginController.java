package com.accion.recruitment.web.controller;


import com.accion.recruitment.common.constants.LoginRestURIConstants;
import com.accion.recruitment.common.constants.UserConstants;
import com.accion.recruitment.common.enums.UserHttpStatusEnums;
import com.accion.recruitment.common.helper.PasswordGeneratorHelper;
import com.accion.recruitment.jpa.entities.User;
import com.accion.recruitment.service.LoginService;
import com.accion.recruitment.service.UserEmailNotificationService;
import com.accion.recruitment.service.UserService;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.sql.SQLException;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 12/23/16 00:11 AM#$
 */
@Controller
@Api(value = "LoginController", description = "Login API's ")
public class LoginController{

    @Autowired
    private LoginService loginService;


    @Autowired
    private UserService userService;

    @Autowired
    private UserEmailNotificationService userEmailNotificationService;

    final Md5PasswordEncoder encoder = new Md5PasswordEncoder();

    final PasswordGeneratorHelper passwordGeneratorHelper=new PasswordGeneratorHelper();

    @ApiOperation(value = "Login  based on UserName OR EmailId and Password   ", httpMethod="POST"
            , notes = "Return the Login User")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Login User Found "),
            @ApiResponse(code = 500, message = "Internal Server Error")})

    @RequestMapping(value = LoginRestURIConstants.LOGIN, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> userLoginAuthentication(@PathVariable("userName") final String userNameOREmailId,
                                        @PathVariable("password") final String password) {

        User userObject;
        final String encodedPassword=encoder.encodePassword(password, null);
        try{
            userObject=this.loginService.getLoginUserByUserNameOREmailIdAndPassword(userNameOREmailId, encodedPassword);
            User user=new User(userObject.getId(),userObject.getUserName(),userObject.getEmailId(),userObject.getFirstName(),userObject.getLastName(),userObject.getContactNumber(),
            userObject.getRole(),userObject.getEnabled(),userObject.getErrorMessage());
            return new ResponseEntity<Object>(user, HttpStatus.OK);
        }catch (SQLException e){
            return new ResponseEntity<Object>(UserHttpStatusEnums.DATABASE_EXCEPTION.ResponseMsg(), HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            return new ResponseEntity<Object>(UserHttpStatusEnums.LOGIN_ERROR.ResponseMsg(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @ApiOperation(value = "Change the Password   ", httpMethod="POST")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Password Changed Successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error")})

    @RequestMapping(value = LoginRestURIConstants.CHANGE_PASSWORD, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
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

    @ApiOperation(value = "forgot the Password   ", httpMethod="POST")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Password Changed Successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error")})

    @RequestMapping(value = LoginRestURIConstants.FORGOT_PASSWORD, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> forgotPassword(@PathVariable("emailId") final String emailId,
                                                 Principal principal) {
        User userObject;
        try{
            userObject=this.userService.findUserByPropertyName(UserConstants.EMAIL_ID, emailId);
            if(userObject!=null){
                String password=this.passwordGeneratorHelper.generatePassword();
                userObject.setPassword(this.encoder.encodePassword(password, null));
                if(this.userService.saveUser(userObject)){
                    userObject.setPassword(password);
                    if(this.userEmailNotificationService.sendUserResetPassword(userObject)){
                        return new ResponseEntity<Object>(new Gson().toJson(UserHttpStatusEnums.PASSWORD_CHANGED_EMAIL_SEND.ResponseMsg()), HttpStatus.OK);
                    }else{
                        return new ResponseEntity<Object>(new Gson().toJson(UserHttpStatusEnums.PASSWORD_CHANGED_EMAIL_NOT_SEND.ResponseMsg()), HttpStatus.OK);
                    }
                }else{
                    return new ResponseEntity<Object>(new Gson().toJson(UserHttpStatusEnums.PASSWORD_NOT_CHANGED.ResponseMsg()), HttpStatus.OK);
                }
            }else{
                return new ResponseEntity<Object>(UserHttpStatusEnums.EMAIL_ID_NOT_FOUND, HttpStatus.OK);
            }
        }catch (SQLException e){
            return new ResponseEntity<Object>(UserHttpStatusEnums.DATABASE_EXCEPTION.ResponseMsg(), HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            return new ResponseEntity<Object>(UserHttpStatusEnums.LOGIN_ERROR.ResponseMsg(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @ApiOperation(value = "Logout User ", httpMethod="GET"
            , notes = "Return the Login User")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Logout Successfully "),
            @ApiResponse(code = 500, message = "Internal Server Error")})

    @RequestMapping(value = LoginRestURIConstants.LOGOUT, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> userLogout() {

        return null;
    }

}
