package com.accion.recruitment.web.controller;

import com.accion.recruitment.common.constants.HookedOnConstants;
import com.accion.recruitment.common.constants.QuestionRestURIConstants;
import com.accion.recruitment.common.constants.RequirementURIConstants;
import com.accion.recruitment.common.enums.QuestionEnums;
import com.accion.recruitment.jpa.entities.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @RequestMapping(value = RequirementURIConstants.REQUIREMENT_CREATE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> createRequirement(@RequestBody Positions positions) {

        return new ResponseEntity<Object>(ReqtEnums.REQT_CREATED.ResponseMsg(), HttpStatus.OK      );
    }
*/

    @ApiOperation(value = "Add Question", httpMethod="POST")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Add Question"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(value = QuestionRestURIConstants.QUESTION_CREATE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> addQuestion(@RequestBody QuestionBaseClass question, String UserId) {

        if (question.getQuestionType().equals(HookedOnConstants.QUESTION_TYPE_GENERAL))
        {
        //TODO ADD Question to DB
            return new ResponseEntity<Object>(QuestionEnums.QUESTION_CREATED, HttpStatus.CREATED);
        }
        return new ResponseEntity<Object>(QuestionEnums.QUESTION_CREATED, HttpStatus.OK);

    }

    @ApiOperation(value = "Edit Question", httpMethod="POST")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Edit Question"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(value = QuestionRestURIConstants.QUESTION_EDIT, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> editQuestion(@RequestBody QuestionBaseClass question) {

        if (question.getQuestionType().equals(HookedOnConstants.QUESTION_TYPE_GENERAL))
        {
            //TODO Edit Question to DB
            return new ResponseEntity<Object>(QuestionEnums.QUESTION_UPDATED, HttpStatus.CREATED);
        }
        return new ResponseEntity<Object>(QuestionEnums.QUESTION_UPDATED, HttpStatus.OK);

    }

    @ApiOperation(value = "Delete Question", httpMethod="POST")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Delete Question"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(value = QuestionRestURIConstants.QUESTION_DELETE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> deleteQuestion(@PathVariable String questionId, String questionType) {

        if (questionType.equals(HookedOnConstants.QUESTION_TYPE_GENERAL))
        {
            //TODO Delete Question to DB
            return new ResponseEntity<Object>(QuestionEnums.QUESTION_DELETED, HttpStatus.CREATED);
        }
        return new ResponseEntity<Object>(QuestionEnums.QUESTION_DELETED, HttpStatus.OK);

    }

    @ApiOperation(value = "Approve Question", httpMethod="POST")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Approve Question"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(value = QuestionRestURIConstants.QUESTION_DELETE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> approveQuestion(@PathVariable String questionId, @PathVariable String questionType,@PathVariable String PositionId) {

        if (questionType.equals(HookedOnConstants.QUESTION_TYPE_GENERAL))
        {
            //TODO Approve Question to DB
            return new ResponseEntity<Object>(QuestionEnums.QUESTION_APPROVED, HttpStatus.CREATED);
        }
        return new ResponseEntity<Object>(QuestionEnums.QUESTION_APPROVED, HttpStatus.OK);

    }
}
