package com.accion.recruitment.web.controller;

import com.accion.recruitment.common.constants.RequirementMgtConstants;
import com.accion.recruitment.common.enums.ReqtMgtEnums;
import com.accion.recruitment.jpa.entities.Positions;
import com.accion.recruitment.service.QuestionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 13/01/17 00:11 AM#$
 */

@Controller
public class QuestionController {



    //Duplicate code from requirement page.. caused running  problem 
/*

    @Autowired
    QuestionService questionService;

    @ApiOperation(value = "Created ", httpMethod="POST"
            , notes = "Creates Question")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Question Created "),
            @ApiResponse(code = 500, message = "Internal Server Error")})

    @RequestMapping(value = RequirementMgtConstants.REQUIREMENT_CREATE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> createRequirement(@RequestBody Positions positions) {

        return new ResponseEntity<Object>(ReqtMgtEnums.REQT_CREATED.ResponseMsg(), HttpStatus.OK      );
    }
*/


}
