package com.accion.recruitment.web.controller;


import com.accion.recruitment.common.constants.LoginRestURIConstants;
import com.accion.recruitment.common.enums.HttpStatusEnums;
import com.accion.recruitment.jpa.entities.User;
import com.accion.recruitment.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 12/23/16 00:11 AM#$
 */
@Controller
public class LoginController{

    @Autowired
    private LoginService loginService;

    final Md5PasswordEncoder encoder = new Md5PasswordEncoder();

    @RequestMapping(value = LoginRestURIConstants.LOGIN, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public String userLoginAuthentication(@PathVariable("userName") final String userNameOREmailId,
                                        @PathVariable("password") final String password) {

        User userObject=new User();
        final String encodedPassword=encoder.encodePassword(password, null);
        try{
            userObject=this.loginService.getLoginUserByUserNameOREmailIdAndPassword(userNameOREmailId, encodedPassword);
            return userObject.toString();
        }catch (SQLException e){
            return String.valueOf(HttpStatusEnums.DATABASE_EXCEPTION.ResponseMsg());
        }catch (Exception e){
            return String.valueOf(HttpStatusEnums.LOGIN_ERROR.ResponseMsg());
        }


    }


}
