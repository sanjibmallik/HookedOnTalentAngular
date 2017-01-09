package com.accion.recruitment.web.controller;

import com.accion.recruitment.common.constants.UserConstants;
import com.accion.recruitment.common.constants.UserRestURIConstants;
import com.accion.recruitment.jpa.entities.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 09/01/17 00:11 AM#$
 */

public class ClientController {

    /*@ApiOperation(value = "Get the Client Details based on ClientName  ", httpMethod="GET"
            , notes = "Return the matched Client")
    @ApiResponses(value = {@ApiResponse(code = 302, message = "Client Found "),
            @ApiResponse(code = 404, message = "Client not found"),
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

    }*/
}
