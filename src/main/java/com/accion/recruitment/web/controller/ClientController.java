package com.accion.recruitment.web.controller;

import com.accion.recruitment.beans.ClientDetailsContact;
import com.accion.recruitment.common.constants.*;
import com.accion.recruitment.common.enums.ClientHttpStatusEnums;
import com.accion.recruitment.common.enums.UserHttpStatusEnums;
import com.accion.recruitment.common.helper.PasswordGeneratorHelper;
import com.accion.recruitment.jpa.entities.*;
import com.accion.recruitment.service.ClientService;
import com.accion.recruitment.service.UserEmailNotificationService;
import com.accion.recruitment.service.UserService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 09/01/17 00:11 AM#$
 */

@Controller
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserEmailNotificationService userEmailNotificationService;

    private PasswordGeneratorHelper passwordGeneratorHelper=new PasswordGeneratorHelper();

    private final Md5PasswordEncoder encoder = new Md5PasswordEncoder();

    private final SimpleDateFormat sdf = new SimpleDateFormat(UserConstants.DATE_FORMAT);

    @ApiOperation(value = "Create the new Client ",  code = 201, httpMethod="POST")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Client Created Successfully"),
            @ApiResponse(code = 200, message = "Successful Respond Send")
            , @ApiResponse(code = 500, message = "Internal Server Error")})

    @RequestMapping(value = ClientRestURIConstants.CREATE_CLIENT,produces = MediaType.APPLICATION_JSON_VALUE ,method = RequestMethod.POST)
    public  ResponseEntity<String> createClient(@RequestBody ClientDetailsContact clientDetailsContact,
                                              final Principal principal) {

        try{
            final Date currentDate = new Date();
            ClientDetails clientDetails;
            ClientContacts clientContacts;
            User user;

            if(clientDetailsContact.getClientDetails()!=null && clientDetailsContact.getClientContacts()!=null && clientDetailsContact.getUser()!=null){
                clientDetails=clientDetailsContact.getClientDetails();
                clientContacts=clientDetailsContact.getClientContacts();
                user=clientDetailsContact.getUser();
            }else{
                return new ResponseEntity<String>(new Gson().toJson(ClientHttpStatusEnums.CLIENT_NOT_SAVED.ResponseMsg()), HttpStatus.OK);
            }

            if(user != null && user.getUserName() != null && (!user.getUserName().isEmpty())){
                try{
                    User userObject=this.userService.findUserByPropertyName(UserConstants.USER_NAME,user.getUserName());
                    if(userObject != null)
                        return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.USER_NAME_EXIST.ResponseMsg()), HttpStatus.OK);
                }catch (SQLException e){
                    return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.USER_NAME_EXIST.ResponseMsg()), HttpStatus.OK);
                }catch (Exception e){
                    return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.USER_NAME_EXIST.ResponseMsg()), HttpStatus.OK);
                }
            }
            if(user != null && user.getEmailId() != null && (!user.getEmailId().isEmpty())){
                try{
                    User userObject=this.userService.findUserByPropertyName(UserConstants.EMAIL_ID,user.getEmailId());
                    if(userObject != null)
                        return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.EMAIlID_EXIST.ResponseMsg()), HttpStatus.OK);

                }catch (SQLException e){
                    return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.EMAIlID_EXIST.ResponseMsg()), HttpStatus.OK);
                }catch (Exception e){
                    return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.EMAIlID_EXIST.ResponseMsg()), HttpStatus.OK);
                }
            }
            if(user != null && user.getContactNumber() != null && (!user.getContactNumber().isEmpty())){
                try{
                    User userObject=this.userService.findUserByPropertyName(UserConstants.CONTACT_NUMBER,user.getContactNumber());
                    if(userObject != null)
                        return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.CONTACT_NUMBER_EXIST.ResponseMsg()), HttpStatus.OK);

                }catch (SQLException e){
                    return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.CONTACT_NUMBER_EXIST.ResponseMsg()), HttpStatus.OK);
                }catch (Exception e){
                    return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.CONTACT_NUMBER_EXIST.ResponseMsg()), HttpStatus.OK);
                }
            }

            if(clientDetails != null && clientDetails.getClientName() != null && (!clientDetails.getClientName().isEmpty())){
                try{
                    ClientDetails clientObject=this.clientService.findClientDetailsByPropertyName(ClientConstants.CLIENT_NAME, clientDetails.getClientName());
                    if(clientObject != null)
                        return new ResponseEntity<String>(new Gson().toJson(ClientHttpStatusEnums.CLIENT_NAME_EXIST.ResponseMsg()), HttpStatus.OK);

                }catch (SQLException e){
                    return new ResponseEntity<String>(new Gson().toJson(ClientHttpStatusEnums.CLIENT_NAME_EXIST.ResponseMsg()), HttpStatus.OK);
                }catch (Exception e){
                    return new ResponseEntity<String>(new Gson().toJson(ClientHttpStatusEnums.CLIENT_NAME_EXIST.ResponseMsg()), HttpStatus.OK);
                }
            }
            if(clientDetails != null && clientDetails.getContactNumber() != null && (!clientDetails.getContactNumber().isEmpty())){
                try{
                    ClientDetails clientObject=this.clientService.findClientDetailsByPropertyName(ClientConstants.CONTACT_NUMBER, clientDetails.getContactNumber());
                    if(clientObject != null)
                        return new ResponseEntity<String>(new Gson().toJson(ClientHttpStatusEnums.CONTACT_NUMBER_EXIST.ResponseMsg()), HttpStatus.OK);

                }catch (SQLException e){
                    return new ResponseEntity<String>(new Gson().toJson(ClientHttpStatusEnums.CONTACT_NUMBER_EXIST.ResponseMsg()), HttpStatus.OK);
                }catch (Exception e){
                    return new ResponseEntity<String>(new Gson().toJson(ClientHttpStatusEnums.CONTACT_NUMBER_EXIST.ResponseMsg()), HttpStatus.OK);
                }
            }
            if(clientDetails != null && clientDetails.getFederalId() != null && (!clientDetails.getFederalId().isEmpty())){
                try{
                    ClientDetails clientObject=this.clientService.findClientDetailsByPropertyName(ClientConstants.FEDERAL_ID, clientDetails.getFederalId());
                    if(clientObject != null)
                        return new ResponseEntity<String>(new Gson().toJson(ClientHttpStatusEnums.FEDERAL_ID_EXIST.ResponseMsg()), HttpStatus.OK);

                }catch (SQLException e){
                    return new ResponseEntity<String>(new Gson().toJson(ClientHttpStatusEnums.FEDERAL_ID_EXIST.ResponseMsg()), HttpStatus.OK);
                }catch (Exception e){
                    return new ResponseEntity<String>(new Gson().toJson(ClientHttpStatusEnums.FEDERAL_ID_EXIST.ResponseMsg()), HttpStatus.OK);
                }
            }


            if((clientDetails.getEngagementModel()==null) || (clientDetails.getEngagementModel()=="")){
                clientDetails.setEngagementModel(clientDetails.getEngagementModelOther());
                EngagementModel engagementModel=new EngagementModel();
                engagementModel.setEngagementModel(clientDetails.getEngagementModelOther());
                try{
                    this.clientService.saveEngagementModel(engagementModel);
                }catch (Exception e){
                    return new ResponseEntity<String>(new Gson().toJson(ClientHttpStatusEnums.CLIENT_NOT_SAVED.ResponseMsg()), HttpStatus.OK);
                }
            }
            if((clientDetails.getIndustry()==null) || (clientDetails.getIndustry()=="")){
                clientDetails.setIndustry(clientDetails.getIndustryOther());
                Industry industry=new Industry();
                industry.setIndustry(clientDetails.getIndustryOther());
                try{
                    this.clientService.saveIndustry(industry);
                }catch (Exception e){
                    return new ResponseEntity<String>(new Gson().toJson(ClientHttpStatusEnums.CLIENT_NOT_SAVED.ResponseMsg()), HttpStatus.OK);
                }
            }

            clientDetails.getClientContacts().add(clientContacts);
            String password=this.passwordGeneratorHelper.generatePassword();
            user.setPassword(this.encoder.encodePassword(password, null));
            user.setRole("Client");
            clientContacts.setContactFullName(clientContacts.getFirstName()+" "+clientContacts.getLastName());
            String isEmailSent = clientContacts.getSendUserEmail();
            User actMgr=new User();
            try{
                user.setCreatedBy(principal.getName());
                user.setUpdatedBy(principal.getName());
            }catch (Exception e){
            }
            user.setCreatedDate(new Date(sdf.format(currentDate)));
            user.setUpdatedDate(new Date(sdf.format(currentDate)));

            try{
                actMgr=this.userService.findUserByPropertyName(UserConstants.USER_NAME,principal.getName());
                actMgr.getAccountManagerClients().add(clientDetails);
            }catch (Exception e){

            }

            try{
                if(this.userService.saveUserGroups(user)){
                    if (isEmailSent!=null){
                            this.userService.saveUser(actMgr);
                            this.clientService.saveClientDetails(clientDetails);
                            this.clientService.saveClientContacts(clientContacts);
                            user.setPassword(password);
                            this.userEmailNotificationService.sendUserCredentials(user);
                            return new ResponseEntity<String>(new Gson().toJson(ClientHttpStatusEnums.CLIENT_SAVED.ResponseMsg()), HttpStatus.CREATED);
                    }else{
                        clientContacts.setSendUserEmail("No");
                        try{
                            this.userService.saveUser(actMgr);
                        }catch (Exception e){

                        }
                        this.clientService.saveClientContacts(clientContacts);
                        this.clientService.saveClientDetails(clientDetails);

                        return new ResponseEntity<String>(new Gson().toJson(ClientHttpStatusEnums.CLIENT_SAVED.ResponseMsg()), HttpStatus.CREATED);
                    }
                }
            }catch (Exception e){
                return new ResponseEntity<String>(new Gson().toJson(ClientHttpStatusEnums.CLIENT_NOT_SAVED.ResponseMsg()), HttpStatus.OK);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<String>(new Gson().toJson(ClientHttpStatusEnums.CLIENT_NOT_SAVED.ResponseMsg()), HttpStatus.OK);
        }
        return new ResponseEntity<String>(new Gson().toJson(ClientHttpStatusEnums.CLIENT_NOT_SAVED.ResponseMsg()), HttpStatus.OK);
    }

    @ApiOperation(value = "Get All the Clients ", httpMethod="GET")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Clients Found "),
            @ApiResponse(code = 500, message = "Internal Server Error")})

    @RequestMapping(value = ClientRestURIConstants.GET_ALL_CLIENT, produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.GET)
    @ResponseBody
    @JsonIgnore
    public ResponseEntity<Set<ClientDetails>> getAllClients(){

        try{
            List<ClientDetails> clientDetailsList=this.clientService.findAllClients();
            Set<ClientDetails> clients=new LinkedHashSet<ClientDetails>();
            for(ClientDetails clientObject:clientDetailsList){
                ClientDetails clientDetails=new ClientDetails(clientObject.getId(),clientObject.getClientName(),clientObject.getIndustry(),clientObject.getEngagementModel(),clientObject.getFederalId(),clientObject.getFaxNumber()
                ,clientObject.getContactNumber(),clientObject.getAlternateContact(),clientObject.getAddressOne(),clientObject.getAddressTwo(),clientObject.getCity(),
                        clientObject.getState(),clientObject.getCountry(),clientObject.getZipCode(),clientObject.getWebsiteUrl(),clientObject.getEnable(),clientObject.getIsUserActive(),
                        clientObject.getOwner(),clientObject.getNote(),clientObject.getClientContacts());
                clients.add(clientDetails);
            }
            return new ResponseEntity<Set<ClientDetails>>(clients, HttpStatus.OK);
        }catch (SQLException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @ApiOperation(value = "Update the  Client ",  code = 201, httpMethod="POST")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Client Updated Successfully")
            , @ApiResponse(code = 500, message = "Internal Server Error")})

    @RequestMapping(value = ClientRestURIConstants.UPDATE_CLIENT,produces = MediaType.APPLICATION_JSON_VALUE ,method = RequestMethod.POST)
    public  ResponseEntity<String> updateClient(@RequestBody ClientDetailsContact clientDetailsContact,
                                                final Principal principal) {

        try{
            final Date currentDate = new Date();
            ClientDetails clientDetails;
            ClientContacts clientContacts;
            User user;
            User oldUser;
            ClientDetails oldClient;

            if(clientDetailsContact.getClientDetails()!=null && clientDetailsContact.getClientContacts()!=null && clientDetailsContact.getUser()!=null){
                clientDetails=clientDetailsContact.getClientDetails();
                clientContacts=clientDetailsContact.getClientContacts();
                user=clientDetailsContact.getUser();
            }else{
                return new ResponseEntity<String>(new Gson().toJson(ClientHttpStatusEnums.CLIENT_NOT_SAVED.ResponseMsg()), HttpStatus.OK);
            }

            try{
                oldClient=this.clientService.findClientDetailsByPropertyName(ClientConstants.CLIENT_ID, clientDetails.getId());
                if(oldClient==null)
                    return new ResponseEntity<String>(new Gson().toJson(ClientHttpStatusEnums.CLIENT_NOT_SAVED.ResponseMsg()), HttpStatus.OK);

                oldUser=this.userService.findUserById(user.getId());
                if(oldUser==null)
                    return new ResponseEntity<String>(new Gson().toJson(ClientHttpStatusEnums.CLIENT_NOT_SAVED.ResponseMsg()), HttpStatus.OK);
            }catch (Exception e){
                return new ResponseEntity<String>(new Gson().toJson(ClientHttpStatusEnums.CLIENT_NOT_SAVED.ResponseMsg()), HttpStatus.OK);
            }





            if(user != null && user.getUserName() != null && (!user.getUserName().isEmpty()) && (!oldUser.getUserName().equals(user.getUserName()))){
                try{
                    User userObject=this.userService.findUserByPropertyName(UserConstants.USER_NAME,user.getUserName());
                    if(userObject != null)
                        return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.USER_NAME_EXIST.ResponseMsg()), HttpStatus.OK);
                }catch (SQLException e){
                    return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.USER_NAME_EXIST.ResponseMsg()), HttpStatus.OK);
                }catch (Exception e){
                    return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.USER_NAME_EXIST.ResponseMsg()), HttpStatus.OK);
                }
            }
            if(user != null && user.getEmailId() != null && (!user.getEmailId().isEmpty())  && (!oldUser.getEmailId().equals(user.getEmailId()))){
                try{
                    User userObject=this.userService.findUserByPropertyName(UserConstants.EMAIL_ID,user.getEmailId());
                    if(userObject != null)
                        return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.EMAIlID_EXIST.ResponseMsg()), HttpStatus.OK);

                }catch (SQLException e){
                    return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.EMAIlID_EXIST.ResponseMsg()), HttpStatus.OK);
                }catch (Exception e){
                    return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.EMAIlID_EXIST.ResponseMsg()), HttpStatus.OK);
                }
            }
            if(user != null && user.getContactNumber() != null && (!user.getContactNumber().isEmpty()) &&(!oldUser.getContactNumber().equals(user.getContactNumber()))){
                try{
                    User userObject=this.userService.findUserByPropertyName(UserConstants.CONTACT_NUMBER,user.getContactNumber());
                    if(userObject != null)
                        return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.CONTACT_NUMBER_EXIST.ResponseMsg()), HttpStatus.OK);

                }catch (SQLException e){
                    return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.CONTACT_NUMBER_EXIST.ResponseMsg()), HttpStatus.OK);
                }catch (Exception e){
                    return new ResponseEntity<String>(new Gson().toJson(UserHttpStatusEnums.CONTACT_NUMBER_EXIST.ResponseMsg()), HttpStatus.OK);
                }
            }

            if(clientDetails != null && clientDetails.getClientName() != null && (!clientDetails.getClientName().isEmpty()) &&(!oldClient.getClientName().equals(clientDetails.getClientName()))){
                try{
                    ClientDetails clientObject=this.clientService.findClientDetailsByPropertyName(ClientConstants.CLIENT_NAME, clientDetails.getClientName());
                    if(clientObject != null)
                        return new ResponseEntity<String>(new Gson().toJson(ClientHttpStatusEnums.CLIENT_NAME_EXIST.ResponseMsg()), HttpStatus.OK);

                }catch (SQLException e){
                    return new ResponseEntity<String>(new Gson().toJson(ClientHttpStatusEnums.CLIENT_NAME_EXIST.ResponseMsg()), HttpStatus.OK);
                }catch (Exception e){
                    return new ResponseEntity<String>(new Gson().toJson(ClientHttpStatusEnums.CLIENT_NAME_EXIST.ResponseMsg()), HttpStatus.OK);
                }
            }
            if(clientDetails != null && clientDetails.getContactNumber() != null && (!clientDetails.getContactNumber().isEmpty()) &&(!oldClient.getContactNumber().equals(clientDetails.getContactNumber()))){
                try{
                    ClientDetails clientObject=this.clientService.findClientDetailsByPropertyName(ClientConstants.CONTACT_NUMBER, clientDetails.getContactNumber());
                    if(clientObject != null)
                        return new ResponseEntity<String>(new Gson().toJson(ClientHttpStatusEnums.CONTACT_NUMBER_EXIST.ResponseMsg()), HttpStatus.OK);

                }catch (SQLException e){
                    return new ResponseEntity<String>(new Gson().toJson(ClientHttpStatusEnums.CONTACT_NUMBER_EXIST.ResponseMsg()), HttpStatus.OK);
                }catch (Exception e){
                    return new ResponseEntity<String>(new Gson().toJson(ClientHttpStatusEnums.CONTACT_NUMBER_EXIST.ResponseMsg()), HttpStatus.OK);
                }
            }
            if(clientDetails != null && clientDetails.getFederalId() != null && (!clientDetails.getFederalId().isEmpty()) &&(!oldClient.getFederalId().equals(clientDetails.getFederalId()))){
                try{
                    ClientDetails clientObject=this.clientService.findClientDetailsByPropertyName(ClientConstants.FEDERAL_ID, clientDetails.getFederalId());
                    if(clientObject != null)
                        return new ResponseEntity<String>(new Gson().toJson(ClientHttpStatusEnums.FEDERAL_ID_EXIST.ResponseMsg()), HttpStatus.OK);

                }catch (SQLException e){
                    return new ResponseEntity<String>(new Gson().toJson(ClientHttpStatusEnums.FEDERAL_ID_EXIST.ResponseMsg()), HttpStatus.OK);
                }catch (Exception e){
                    return new ResponseEntity<String>(new Gson().toJson(ClientHttpStatusEnums.FEDERAL_ID_EXIST.ResponseMsg()), HttpStatus.OK);
                }
            }


            if((clientDetails.getEngagementModel()==null) || (clientDetails.getEngagementModel().equals(""))){
                clientDetails.setEngagementModel(clientDetails.getEngagementModelOther());
                EngagementModel engagementModel=new EngagementModel();
                engagementModel.setEngagementModel(clientDetails.getEngagementModelOther());
                try{
                    this.clientService.saveEngagementModel(engagementModel);
                }catch (Exception e){
                    return new ResponseEntity<String>(new Gson().toJson(ClientHttpStatusEnums.CLIENT_NOT_UPDATED.ResponseMsg()), HttpStatus.OK);
                }
            }
            if((clientDetails.getIndustry()==null) || (clientDetails.getIndustry().equals(""))){
                clientDetails.setIndustry(clientDetails.getIndustryOther());
                Industry industry=new Industry();
                industry.setIndustry(clientDetails.getIndustryOther());
                try{
                    this.clientService.saveIndustry(industry);
                }catch (Exception e){
                    return new ResponseEntity<String>(new Gson().toJson(ClientHttpStatusEnums.CLIENT_NOT_UPDATED.ResponseMsg()), HttpStatus.OK);
                }
            }

            clientDetails.getClientContacts().add(clientContacts);
            String password=this.passwordGeneratorHelper.generatePassword();
            user.setPassword(this.encoder.encodePassword(password, null));
            user.setRole("Client");
            clientContacts.setContactFullName(clientContacts.getFirstName()+" "+clientContacts.getLastName());
            String isEmailSent = clientContacts.getSendUserEmail();
            try{
                user.setUpdatedBy(principal.getName());
            }catch (Exception e){
            }
            user.setUpdatedDate(new Date(sdf.format(currentDate)));


            try{
                if(this.userService.saveUserGroups(user)){
                    if (isEmailSent!=null){
                        this.clientService.saveClientDetails(clientDetails);
                        this.clientService.saveClientContacts(clientContacts);
                        user.setPassword(password);
                        this.userEmailNotificationService.sendUserCredentials(user);
                        return new ResponseEntity<String>(new Gson().toJson(ClientHttpStatusEnums.CLIENT_UPDATED.ResponseMsg()), HttpStatus.OK);
                    }else{
                        clientContacts.setSendUserEmail("No");
                        this.clientService.saveClientDetails(clientDetails);
                        this.clientService.saveClientContacts(clientContacts);
                        return new ResponseEntity<String>(new Gson().toJson(ClientHttpStatusEnums.CLIENT_UPDATED.ResponseMsg()), HttpStatus.OK);
                    }
                }
            }catch (Exception e){
                return new ResponseEntity<String>(new Gson().toJson(ClientHttpStatusEnums.CLIENT_NOT_UPDATED.ResponseMsg()), HttpStatus.OK);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<String>(new Gson().toJson(ClientHttpStatusEnums.CLIENT_NOT_UPDATED.ResponseMsg()), HttpStatus.OK);
        }
        return new ResponseEntity<String>(new Gson().toJson(ClientHttpStatusEnums.CLIENT_NOT_UPDATED.ResponseMsg()), HttpStatus.OK);
    }


    @ApiOperation(value = "Activate the  Client  based on ID  ", httpMethod="PUT")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Activate Client "),
            @ApiResponse(code = 404, message = "Client not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(value = ClientRestURIConstants.CLIENT_ACTIVATE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Object> activateClient(@PathVariable("id") final Integer clientDetailsId,
                                                 Principal principal) {

        ClientDetails clientDetailsObject;
        try{
            clientDetailsObject=this.clientService.findClientDetailsByPropertyName(ClientConstants.CLIENT_ID,clientDetailsId);
            List<ClientContacts> clientContactsList= (List<ClientContacts>) clientDetailsObject.getClientContacts();
            for(ClientContacts clientContacts:clientContactsList){
                if(clientContacts.getSendUserEmail().equals("No")){
                    User user=this.userService.findUserByPropertyName(UserConstants.USER_NAME,clientContacts.getUserName());
                    String password=this.passwordGeneratorHelper.generatePassword();
                    user.setPassword(this.encoder.encodePassword(password, null));
                    if(this.userService.saveUserGroups(user)){
                        user.setPassword(password);
                        this.clientService.saveClientContacts(clientContacts);
                        this.userEmailNotificationService.sendUserCredentials(user);
                        return new ResponseEntity<Object>(new Gson().toJson(ClientHttpStatusEnums.CLIENT_ACTIVATE_EMAIL_SEND.ResponseMsg()), HttpStatus.OK);
                    }
                }
            }
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
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
                ClientDetails clientDetails=new ClientDetails(clientDetailsObject.getId(),clientDetailsObject.getClientName(),clientDetailsObject.getIndustry(),clientDetailsObject.getEngagementModel(),clientDetailsObject.getFederalId(),clientDetailsObject.getFaxNumber()
                        ,clientDetailsObject.getContactNumber(),clientDetailsObject.getAlternateContact(),clientDetailsObject.getAddressOne(),clientDetailsObject.getAddressTwo(),clientDetailsObject.getCity(),
                        clientDetailsObject.getState(),clientDetailsObject.getCountry(),clientDetailsObject.getZipCode(),clientDetailsObject.getWebsiteUrl(),clientDetailsObject.getEnable(),clientDetailsObject.getIsUserActive(),
                        clientDetailsObject.getOwner(),clientDetailsObject.getNote(),clientDetailsObject.getClientContacts());
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
                ClientDetails clientDetails=new ClientDetails(clientDetailsObject.getId(),clientDetailsObject.getClientName(),clientDetailsObject.getIndustry(),clientDetailsObject.getEngagementModel(),clientDetailsObject.getFederalId(),clientDetailsObject.getFaxNumber()
                        ,clientDetailsObject.getContactNumber(),clientDetailsObject.getAlternateContact(),clientDetailsObject.getAddressOne(),clientDetailsObject.getAddressTwo(),clientDetailsObject.getCity(),
                        clientDetailsObject.getState(),clientDetailsObject.getCountry(),clientDetailsObject.getZipCode(),clientDetailsObject.getWebsiteUrl(),clientDetailsObject.getEnable(),clientDetailsObject.getIsUserActive(),
                        clientDetailsObject.getOwner(),clientDetailsObject.getNote(),clientDetailsObject.getClientContacts());
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
                ClientDetails clientDetails=new ClientDetails(clientDetailsObject.getId(),clientDetailsObject.getClientName(),clientDetailsObject.getIndustry(),clientDetailsObject.getEngagementModel(),clientDetailsObject.getFederalId(),clientDetailsObject.getFaxNumber()
                        ,clientDetailsObject.getContactNumber(),clientDetailsObject.getAlternateContact(),clientDetailsObject.getAddressOne(),clientDetailsObject.getAddressTwo(),clientDetailsObject.getCity(),
                        clientDetailsObject.getState(),clientDetailsObject.getCountry(),clientDetailsObject.getZipCode(),clientDetailsObject.getWebsiteUrl(),clientDetailsObject.getEnable(),clientDetailsObject.getIsUserActive(),
                        clientDetailsObject.getOwner(),clientDetailsObject.getNote(),clientDetailsObject.getClientContacts());
                return new ResponseEntity<Object>(clientDetails, HttpStatus.OK);
            }
        }catch (SQLException sql){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ApiOperation(value = "Get the Engagement Model ", httpMethod="GET")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Engagement Model  "),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(value = ClientRestURIConstants.GET_ENGAGEMENT, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> getEngagementModel() {
        try{
            List<EngagementModel> engagementModelList= this.clientService.findAllEngagementModel();
            Set<String> engagementModelSet=new HashSet<String>();
            for(EngagementModel engagementModel:engagementModelList){
                engagementModelSet.add(engagementModel.getEngagementModel());
            }
            String json = new Gson().toJson(engagementModelSet);
            return new ResponseEntity<Object>(json, HttpStatus.OK);
        }catch (SQLException sql){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Get All Industry ", httpMethod="GET")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Industry List  "),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(value = ClientRestURIConstants.GET_INDUSTRY, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> getIndustry() {
        try{
            List<Industry> industryList= this.clientService.findAllIndustry();
            Set<String> industrySet=new HashSet<String>();
            for(Industry industry:industryList){
                industrySet.add(industry.getIndustry());
            }
            String json = new Gson().toJson(industrySet);
            return new ResponseEntity<Object>(json, HttpStatus.OK);
        }catch (SQLException sql){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
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

    public HashMap<String,String> checkClientDetailsExist(ClientDetails clientDetails,ClientContacts clientContacts){

        HashMap clientDetailsMap=new HashMap<String,String>();

        if(clientDetails != null && clientDetails.getClientName() != null && (!clientDetails.getClientName().isEmpty())){
            try{
                ClientDetails clientObject=this.clientService.findClientDetailsByPropertyName(ClientConstants.CLIENT_NAME, clientDetails.getClientName());
                if(clientObject != null)
                    return (HashMap<String, String>) clientDetailsMap.put(HookedOnConstants.EXIST, ClientHttpStatusEnums.CLIENT_NAME_EXIST.ResponseMsg());
            }catch (SQLException e){
                return (HashMap<String, String>) clientDetailsMap.put(HookedOnConstants.EXIST, ClientHttpStatusEnums.DATABASE_EXCEPTION.ResponseMsg());
            }catch (Exception e){
                return (HashMap<String, String>) clientDetailsMap.put(HookedOnConstants.EXIST, ClientHttpStatusEnums.CLIENT_NOT_SAVED.ResponseMsg());
            }
        }
        if(clientDetails != null && clientDetails.getContactNumber() != null && (!clientDetails.getContactNumber().isEmpty())){
            try{
                ClientDetails clientObject=this.clientService.findClientDetailsByPropertyName(ClientConstants.CONTACT_NUMBER, clientDetails.getContactNumber());
                if(clientObject != null)
                    return (HashMap<String, String>) clientDetailsMap.put(HookedOnConstants.EXIST, ClientHttpStatusEnums.CONTACT_NUMBER_EXIST.ResponseMsg());
            }catch (SQLException e){
                return (HashMap<String, String>) clientDetailsMap.put(HookedOnConstants.EXIST, ClientHttpStatusEnums.DATABASE_EXCEPTION.ResponseMsg());
            }catch (Exception e){
                return (HashMap<String, String>) clientDetailsMap.put(HookedOnConstants.EXIST, ClientHttpStatusEnums.CLIENT_NOT_SAVED.ResponseMsg());
            }
        }
        if(clientDetails != null && clientDetails.getFederalId() != null && (!clientDetails.getFederalId().isEmpty())){
            try{
                ClientDetails clientObject=this.clientService.findClientDetailsByPropertyName(ClientConstants.FEDERAL_ID, clientDetails.getFederalId());
                if(clientObject != null)
                    return (HashMap<String, String>) clientDetailsMap.put(HookedOnConstants.EXIST, ClientHttpStatusEnums.FEDERAL_ID_EXIST.ResponseMsg());
            }catch (SQLException e){
                return (HashMap<String, String>) clientDetailsMap.put(HookedOnConstants.EXIST, ClientHttpStatusEnums.DATABASE_EXCEPTION.ResponseMsg());
            }catch (Exception e){
                return (HashMap<String, String>) clientDetailsMap.put(HookedOnConstants.EXIST, ClientHttpStatusEnums.CLIENT_NOT_SAVED.ResponseMsg());
            }
        }
       /* if(clientContacts != null && clientContacts.getUserName() != null && (!clientContacts.getUserName().isEmpty())){
            try{
                User userObject=this.userService.findUserByPropertyName(UserConstants.USER_NAME,clientContacts.getUserName());
                if(userObject != null)
                    return (HashMap<String, String>) clientDetailsMap.put(HookedOnConstants.EXIST, UserHttpStatusEnums.USER_NAME_EXIST.ResponseMsg());
            }catch (SQLException e){
                return (HashMap<String, String>) clientDetailsMap.put(HookedOnConstants.EXIST, UserHttpStatusEnums.DATABASE_EXCEPTION.ResponseMsg());
            }catch (Exception e){
                return (HashMap<String, String>) clientDetailsMap.put(HookedOnConstants.EXIST, ClientHttpStatusEnums.CLIENT_NOT_SAVED.ResponseMsg());
            }
        }
        if(clientContacts != null && clientContacts.getEmailId() != null && (!clientContacts.getEmailId().isEmpty())){
            try{
                User userObject=this.userService.findUserByPropertyName(UserConstants.EMAIL_ID,clientContacts.getEmailId());
                if(userObject != null)
                    return (HashMap<String, String>) clientDetailsMap.put(HookedOnConstants.EXIST, UserHttpStatusEnums.EMAIlID_EXIST.ResponseMsg());
            }catch (SQLException e){
                return (HashMap<String, String>) clientDetailsMap.put(HookedOnConstants.EXIST, UserHttpStatusEnums.DATABASE_EXCEPTION.ResponseMsg());
            }catch (Exception e){
                return (HashMap<String, String>) clientDetailsMap.put(HookedOnConstants.EXIST, ClientHttpStatusEnums.CLIENT_NOT_SAVED.ResponseMsg());
            }
        }
        if(clientContacts != null && clientContacts.getContactNumber() != null && (!clientContacts.getContactNumber().isEmpty())){
            try{
                User userObject=this.userService.findUserByPropertyName(UserConstants.CONTACT_NUMBER,clientContacts.getContactNumber());
                if(userObject != null)
                    return (HashMap<String, String>) clientDetailsMap.put(HookedOnConstants.EXIST, UserHttpStatusEnums.CONTACT_NUMBER_EXIST.ResponseMsg());
            }catch (SQLException e){
                return (HashMap<String, String>) clientDetailsMap.put(HookedOnConstants.EXIST, UserHttpStatusEnums.DATABASE_EXCEPTION.ResponseMsg());
            }catch (Exception e){
                return (HashMap<String, String>) clientDetailsMap.put(HookedOnConstants.EXIST, ClientHttpStatusEnums.CLIENT_NOT_SAVED.ResponseMsg());
            }
        }*/
        return (HashMap<String, String>) clientDetailsMap.put(HookedOnConstants.NOT_EXIST,"");

    }

}
