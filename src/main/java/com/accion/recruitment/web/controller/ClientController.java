package com.accion.recruitment.web.controller;

import com.accion.recruitment.common.constants.ClientConstants;
import com.accion.recruitment.common.constants.ClientRestURIConstants;
import com.accion.recruitment.common.constants.UserConstants;
import com.accion.recruitment.common.constants.UserRestURIConstants;
import com.accion.recruitment.jpa.entities.ClientDetails;
import com.accion.recruitment.jpa.entities.User;
import com.accion.recruitment.service.ClientService;
import com.accion.recruitment.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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

@Controller
public class ClientController {

    @Autowired
    private ClientService clientService;



    @ApiOperation(value = "Get the Client Details based on ID  ", httpMethod="GET"
            , notes = "Return the matched Client")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Client Found "),
            @ApiResponse(code = 404, message = "Client not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(value = ClientRestURIConstants.GET_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> getClientById(@PathVariable("id") final Integer clientDetailsId) {

        ClientDetails clientDetailsObject;
        try{
            clientDetailsObject=this.clientService.findClientDetailsByPropertyName(ClientConstants.CLIENT_ID,clientDetailsId);
            if(clientDetailsObject!=null){
                ClientDetails clientDetails=new ClientDetails(clientDetailsObject.getId(),clientDetailsObject.getClientName());
                return new ResponseEntity<Object>(clientDetails, HttpStatus.OK);
            }
        }catch (SQLException sql){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }



    @ApiOperation(value = "Get the Client Details based on ClientName  ", httpMethod="GET"
            , notes = "Return the matched Client")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Client Found "),
            @ApiResponse(code = 404, message = "Client not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(value = ClientRestURIConstants.GET_CLIENT_NAME, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> isClientNameExist(@PathVariable("clientName") final String clientName) {

        ClientDetails clientDetailsObject;
        try{
            clientDetailsObject=this.clientService.findClientDetailsByPropertyName(ClientConstants.CLIENT_NAME,clientName);
            if(clientDetailsObject!=null){
                ClientDetails clientDetails=new ClientDetails(clientDetailsObject.getId(),clientDetailsObject.getClientName());
                return new ResponseEntity<Object>(clientDetails, HttpStatus.OK);
            }
        }catch (SQLException sql){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ApiOperation(value = "Get the Client Details based on FederalId  ", httpMethod="GET"
            , notes = "Return the matched Client")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Client Found "),
            @ApiResponse(code = 404, message = "Client not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(value = ClientRestURIConstants.GET_FEDERAL_ID, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> isFederalIdExist(@PathVariable("federalId") final String federalId) {

        ClientDetails clientDetailsObject;
        try{
            clientDetailsObject=this.clientService.findClientDetailsByPropertyName(ClientConstants.FEDERAL_ID,federalId);
            if(clientDetailsObject!=null){
                ClientDetails clientDetails=new ClientDetails(clientDetailsObject.getId(),clientDetailsObject.getClientName());
                return new ResponseEntity<Object>(clientDetails, HttpStatus.OK);
            }
        }catch (SQLException sql){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /*@ApiOperation(value = "Get the Client Details based on property Name and Value  ", httpMethod="GET"
            , notes = "Return the matched Client")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Client Found "),
            @ApiResponse(code = 404, message = "Client not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(value = ClientRestURIConstants.GET_BY_PROPERTY, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> propertyName(@PathVariable("propertyName") final String propertyName,
                                               @PathVariable("propertyValue") final String propertyValue) {

        ClientDetails clientDetailsObject;
        try{
            clientDetailsObject=this.clientService.findClientDetailsByPropertyName(propertyName,propertyValue);
            if(clientDetailsObject!=null){
                ClientDetails clientDetails=new ClientDetails(clientDetailsObject.getId(),clientDetailsObject.getClientName());
                return new ResponseEntity<Object>(clientDetails, HttpStatus.OK);
            }
        }catch (SQLException sql){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }*/

}
