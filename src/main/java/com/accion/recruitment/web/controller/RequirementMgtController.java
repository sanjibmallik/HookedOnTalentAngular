package com.accion.recruitment.web.controller;

import com.accion.recruitment.common.constants.LoginRestURIConstants;
import com.accion.recruitment.common.constants.RequirementMgtConstants;
import com.accion.recruitment.common.enums.ReqtMgtEnums;
import com.accion.recruitment.common.enums.UserHttpStatusEnums;
import com.accion.recruitment.jpa.entities.Positions;
import com.accion.recruitment.service.RequirementMgtService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by MoinGodil on 1/11/17.
 */
@Controller
public class RequirementMgtController {

    @Autowired
    RequirementMgtService requirementMgtService;

    @ApiOperation(value = "Requirement Created ", httpMethod="POST"
            , notes = "Creates Position/Requirement")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Requirement Created "),
            @ApiResponse(code = 500, message = "Internal Server Error")})

    @RequestMapping(value = RequirementMgtConstants.REQUIREMENT_CREATE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> createRequirement(@RequestBody Positions positions) {

        return new ResponseEntity<Object>(ReqtMgtEnums.REQT_CREATED.ResponseMsg(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ApiOperation(value = "Display Requirement", httpMethod="POST"
            , notes = "Display Position/Requirement")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Display Requirement"),
            @ApiResponse(code = 500, message = "Internal Server Error")})

    @RequestMapping(value = RequirementMgtConstants.REQUIREMENT_DISPLAY, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> displayRequirement() {

        List<Positions> reqtList =  new ArrayList<Positions>();
        Positions tempReqt = new Positions();
        tempReqt.setAccountManager("Dummy Manager");
        tempReqt.setClientName("Dummy Client Name");
        tempReqt.setCreatedBy("HookedonTalent");
        tempReqt.setPrimarySkill("AngularJS");
        tempReqt.setSecondarySkill("Core Java");
        tempReqt.setCreatedDate(new Date());
        tempReqt.setPriority("Critical");
         reqtList.add(tempReqt);
        return new ResponseEntity<Object>(reqtList, HttpStatus.OK);
    }

    @ApiOperation(value = "Edit Requirement", httpMethod="POST"
            , notes = "Edit Position/Requirement")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Edit Requirement"),
            @ApiResponse(code = 500, message = "Internal Server Error")})

    @RequestMapping(value = RequirementMgtConstants.REQUIREMENT_EDIT, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> editRequirement(@RequestBody Positions positions) {

        return new ResponseEntity<Object>(positions, HttpStatus.OK);
    }

    @ApiOperation(value = "Requirement Detail", httpMethod="POST"
            , notes = "Position/Requirement Detail")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Requirement Detail"),
            @ApiResponse(code = 500, message = "Internal Server Error")})

    @RequestMapping(value = RequirementMgtConstants.REQUIREMENT_DETAIL, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> fetchRequirementDetails(Integer reqtId) {

        Positions tempReqt = new Positions();
        tempReqt.setAccountManager("Dummy Manager1");
        tempReqt.setClientName("Dummy Client Name1");
        tempReqt.setCreatedBy("HookedonTalent1");
        tempReqt.setPrimarySkill("AngularJS1");
        tempReqt.setSecondarySkill("Core Java1");
        tempReqt.setCreatedDate(new Date());
        tempReqt.setPriority("Critical");
        tempReqt.setPositionId(reqtId);
        return new ResponseEntity<Object>(tempReqt, HttpStatus.OK);
    }
}
