package com.accion.recruitment.web.controller;

import com.accion.recruitment.common.constants.CandidateConstants;
import com.accion.recruitment.common.constants.RequirementMgtConstants;
import com.accion.recruitment.common.enums.CandidateEnums;
import com.accion.recruitment.common.enums.ReqtMgtEnums;
import com.accion.recruitment.jpa.entities.Candidates;
import com.accion.recruitment.jpa.entities.Positions;
import com.accion.recruitment.service.CandidateService;
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
 * Created by MoinGodil on 1/20/17.
 */

@Controller
public class CandidateController {

    @Autowired
    CandidateService candidateService;

    @ApiOperation(value = "Created ", httpMethod="POST"
            , notes = "Creates Candidate")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Creates Candidate "),
            @ApiResponse(code = 500, message = "Internal Server Error")})

    @RequestMapping(value = CandidateConstants.CANDIDATE_CREATE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> createRequirement(@RequestBody Candidates candidates) {

        return new ResponseEntity<Object>(CandidateEnums.CANDIDATE_CREATED.ResponseMsg(), HttpStatus.OK);
    }

    @ApiOperation(value = "Created ", httpMethod="POST"
            , notes = "Creates Candidate")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Creates Candidate "),
            @ApiResponse(code = 500, message = "Internal Server Error")})

    @RequestMapping(value = CandidateConstants.CANDIDATE_DETAILS, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> createRequirement(@RequestBody Candidates candidates) {

        return new ResponseEntity<Object>(CandidateEnums.CANDIDATE_CREATED.ResponseMsg(), HttpStatus.OK);
    }

    @Autowired
    CandidateService candidateService;

    @ApiOperation(value = "Created ", httpMethod="POST"
            , notes = "Creates Candidate")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Creates Candidate "),
            @ApiResponse(code = 500, message = "Internal Server Error")})

    @RequestMapping(value = CandidateConstants.CANDIDATE_CREATE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> createRequirement(@RequestBody Candidates candidates) {

        return new ResponseEntity<Object>(CandidateEnums.CANDIDATE_CREATED.ResponseMsg(), HttpStatus.OK);
    }

    @Autowired
    CandidateService candidateService;

    @ApiOperation(value = "Created ", httpMethod="POST"
            , notes = "Creates Candidate")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Creates Candidate "),
            @ApiResponse(code = 500, message = "Internal Server Error")})

    @RequestMapping(value = CandidateConstants.CANDIDATE_CREATE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> createRequirement(@RequestBody Candidates candidates) {

        return new ResponseEntity<Object>(CandidateEnums.CANDIDATE_CREATED.ResponseMsg(), HttpStatus.OK);
    }

    @Autowired
    CandidateService candidateService;

    @ApiOperation(value = "Created ", httpMethod="POST"
            , notes = "Creates Candidate")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Creates Candidate "),
            @ApiResponse(code = 500, message = "Internal Server Error")})

    @RequestMapping(value = CandidateConstants.CANDIDATE_CREATE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> createRequirement(@RequestBody Candidates candidates) {

        return new ResponseEntity<Object>(CandidateEnums.CANDIDATE_CREATED.ResponseMsg(), HttpStatus.OK);
    }

}
