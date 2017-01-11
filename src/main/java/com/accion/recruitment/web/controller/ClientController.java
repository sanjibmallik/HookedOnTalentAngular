package com.accion.recruitment.web.controller;

import com.accion.recruitment.common.constants.ClientConstants;
import com.accion.recruitment.common.constants.ClientRestURIConstants;
import com.accion.recruitment.common.constants.UserConstants;
import com.accion.recruitment.common.constants.UserRestURIConstants;
import com.accion.recruitment.common.enums.ClientHttpStatusEnums;
import com.accion.recruitment.common.enums.UserHttpStatusEnums;
import com.accion.recruitment.jpa.entities.*;
import com.accion.recruitment.service.ClientService;
import com.accion.recruitment.service.UserService;
import com.google.gson.Gson;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.sql.SQLException;
import java.util.Date;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 09/01/17 00:11 AM#$
 */

@Controller
public class ClientController {

    @Autowired
    private ClientService clientService;

    @ApiOperation(value = "Create the new Client ",  code = 201, httpMethod="POST")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Client Created Successfully"),
            @ApiResponse(code = 200, message = "Successful Respond Send")
            , @ApiResponse(code = 500, message = "Internal Server Error")})

    @RequestMapping(value = ClientRestURIConstants.CREATE_CLIENT,produces = MediaType.APPLICATION_JSON_VALUE ,method = RequestMethod.POST)
    public  ResponseEntity<String> createUser(@RequestBody ClientDetailsContact clientDetailsContact,
                                              final Principal principal) {

        try{
            final Date currentDate = new Date();
            ClientDetails clientDetails=new ClientDetails();
            ClientContacts clientContacts=new ClientContacts();

            if(clientDetailsContact.getClientDetails()!=null && clientDetailsContact.getClientContacts()!=null){
                clientDetails=clientDetailsContact.getClientDetails();
                clientContacts=clientDetailsContact.getClientContacts();
            }else{

            }

            if(clientDetails != null && clientDetails.getClientName() != null && (!clientDetails.getClientName().isEmpty())){
                try{
                    ClientDetails clientObject=this.clientService.findClientDetailsByPropertyName(ClientConstants.CLIENT_NAME, clientDetails.getClientName());
                    if(clientObject != null)
                        return new ResponseEntity<String>(new Gson().toJson(ClientHttpStatusEnums.CLIENT_NAME_EXIST.ResponseMsg()), HttpStatus.OK);
                }catch (SQLException e){
                    return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.DATABASE_EXCEPTION.ResponseMsg()), HttpStatus.OK);
                }catch (Exception e){
                    return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.USER_NOT_SAVED.ResponseMsg()), HttpStatus.OK);
                }
            }




        }catch (Exception e){

        }

        return null;

    }


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
