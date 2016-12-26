package com.accion.recruitment.web.controller;

import com.accion.recruitment.jpa.entities.TechnicalScreener;
import com.accion.recruitment.jpa.entities.User;
import com.accion.recruitment.service.LoginService;
import com.accion.recruitment.web.controller.LoginController;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping(value = "hot/userLoginAuthentication/{userName}/{password}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public User userLoginAuthentication(@PathVariable("userName") final String userName,
                                        @PathVariable("password") final String password) {

        final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        final String encodedPassword=encoder.encodePassword(password, null);
        final User userObject=this.loginService.getLoginUserByUserNameOREmailIdAndPassword(userName, encodedPassword);
        return userObject;
    }


}
