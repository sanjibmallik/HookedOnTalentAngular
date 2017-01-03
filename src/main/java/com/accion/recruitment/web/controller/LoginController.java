package com.accion.recruitment.web.controller;


import com.accion.recruitment.common.constants.LoginRestURIConstants;
import com.accion.recruitment.common.constants.UserRestURIConstants;
import com.accion.recruitment.common.enums.HttpStatusEnums;
import com.accion.recruitment.jpa.entities.User;
import com.accion.recruitment.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.json.JSONObject;
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

import java.sql.SQLException;
import java.util.List;

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

    final Md5PasswordEncoder encoder = new Md5PasswordEncoder();


    @ApiOperation(value = "Login  based on UserName OR EmailId and Password   ", httpMethod="GET"
            , notes = "Return the Login User")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Login User Found "),
            @ApiResponse(code = 500, message = "Internal Server Error")})

    @RequestMapping(value = LoginRestURIConstants.LOGIN, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> userLoginAuthentication(@PathVariable("userName") final String userNameOREmailId,
                                        @PathVariable("password") final String password) {

        User userObject;
        JSONObject jsonObject=new JSONObject();
        final String encodedPassword=encoder.encodePassword(password, null);
        try{
            userObject=this.loginService.getLoginUserByUserNameOREmailIdAndPassword(userNameOREmailId, encodedPassword);
            jsonObject.put("user", userObject.toString());
            return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);
        }catch (SQLException e){
            return new ResponseEntity<String>(HttpStatusEnums.DATABASE_EXCEPTION.ResponseMsg(), HttpStatus.INTERNAL_SERVER_ERROR);

        }catch (Exception e){
            return new ResponseEntity<String>(HttpStatusEnums.LOGIN_ERROR.ResponseMsg(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
