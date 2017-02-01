package com.accion.recruitment.web.controller;

import com.accion.recruitment.common.constants.SettingsRestURIConstants;
import com.accion.recruitment.jpa.entities.Settings;
import com.accion.recruitment.service.SettingsService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 13/01/17 00:11 AM#$
 */
@Controller
public class SettingsController {

    @Autowired
    private SettingsService settingsService;


    @ApiOperation(value = "Get the Settings details  ", httpMethod="GET")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Setting Details Found "),
            @ApiResponse(code = 500, message = "Internal Server Error")})

    @RequestMapping(value = SettingsRestURIConstants.GET_SETTINGS, produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Settings> getSettingsDetails(){

        try{
            Settings settings=this.settingsService.findSettingsDetailsById(1);
            if(settings!=null)
                return new ResponseEntity<Settings>(settings, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ApiOperation(value = "Update the Settings ",  code = 200, httpMethod="PUT")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Settings Updated Successfully")
            , @ApiResponse(code = 500, message = "Internal Server Error")})

    @RequestMapping(value = SettingsRestURIConstants.UPDATE_SETTINGS,produces = MediaType.APPLICATION_JSON_VALUE ,method = RequestMethod.PUT)
    public  ResponseEntity<String> createUser(@RequestBody Settings settings,
                                              final Principal principal) {
        final Date currentDate = new Date();
        try{
            settings.setId(1);
            try{
                settings.setUpdatedBy(principal.getName());
                settings.setUpdatedDate(currentDate);
            }catch (Exception e){

            }
            if(this.settingsService.saveSettings(settings))
                return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
