package com.accion.recruitment.web.controller;

import com.accion.recruitment.common.constants.LoginRestURIConstants;
import com.accion.recruitment.common.constants.RequirementMgtConstants;
import com.accion.recruitment.common.enums.ReqtMgtEnums;
import com.accion.recruitment.common.enums.UserHttpStatusEnums;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public ResponseEntity<Object> createRequirement() {

        return new ResponseEntity<Object>(ReqtMgtEnums.REQT_CREATED.ResponseMsg(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
