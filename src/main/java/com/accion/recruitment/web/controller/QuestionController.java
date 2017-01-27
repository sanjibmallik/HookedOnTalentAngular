package com.accion.recruitment.web.controller;

import com.accion.recruitment.beans.QuestionBaseClass;
import com.accion.recruitment.common.constants.HookedOnConstants;
import com.accion.recruitment.common.constants.QuestionConstants;
import com.accion.recruitment.common.constants.QuestionRestURIConstants;
import com.accion.recruitment.common.enums.QuestionEnums;
import com.accion.recruitment.jpa.entities.Domain;
import com.accion.recruitment.jpa.entities.GeneralQuestion;
import com.accion.recruitment.jpa.entities.TechnicalQuestion;
import com.accion.recruitment.jpa.entities.VideoQuestion;
import com.accion.recruitment.service.QuestionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 13/01/17 00:11 AM#$
 */

@Controller
public class QuestionController {



    @Autowired
    QuestionService questionService;



    @ApiOperation(value = "Create  Question", httpMethod="POST")
    @ApiResponses(value = {@ApiResponse(code = 201, message = " Question Created Successfully"),
            @ApiResponse(code = 200, message = "Successful Respond Send"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(value = QuestionRestURIConstants.QUESTION_CREATE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> addQuestion(@RequestBody QuestionBaseClass questionBaseClass) {

        try{
            if (questionBaseClass.getQuestionType().equals(QuestionConstants.QUESTION_TYPE_GENERAL)){

                GeneralQuestion generalQuestion=questionBaseClass.getGeneralQuestion();
                if(generalQuestion!=null)
                    if(this.questionService.saveQuestion(generalQuestion))
                        return new ResponseEntity<Object>(QuestionEnums.QUESTION_CREATED, HttpStatus.CREATED);

            }else if(questionBaseClass.getQuestionType().equals(QuestionConstants.QUESTION_TYPE_TECHNICAL)){
                TechnicalQuestion technicalQuestion=questionBaseClass.getTechnicalQuestion();
                if(technicalQuestion!=null){
                    if((technicalQuestion.getDomain()==null || technicalQuestion.getDomain()=="")){
                        technicalQuestion.setDomain(technicalQuestion.getOtherDomain());
                        Domain domain=new Domain();
                        domain.setDomain(technicalQuestion.getOtherDomain());
                        this.questionService.saveDomain(domain);
                    }
                    if(this.questionService.saveQuestion(technicalQuestion))
                        return new ResponseEntity<Object>(QuestionEnums.QUESTION_CREATED, HttpStatus.CREATED);
                }

            }else if(questionBaseClass.getQuestionType().equals(QuestionConstants.QUESTION_TYPE_VIDEO)){
                VideoQuestion videoQuestion=questionBaseClass.getVideoQuestion();
                if(videoQuestion!=null){
                    if((videoQuestion.getDomain()==null || videoQuestion.getDomain()=="")){
                        videoQuestion.setDomain(videoQuestion.getOtherDomain());
                        Domain domain=new Domain();
                        domain.setDomain(videoQuestion.getOtherDomain());
                        this.questionService.saveDomain(domain);
                    }
                    if(this.questionService.saveQuestion(videoQuestion))
                        return new ResponseEntity<Object>(QuestionEnums.QUESTION_CREATED, HttpStatus.CREATED);
                }
            }
        }catch (Exception e){
            return new ResponseEntity<Object>(QuestionEnums.QUESTION_NOT_CREATED, HttpStatus.OK);
        }

        return new ResponseEntity<Object>(QuestionEnums.QUESTION_NOT_CREATED, HttpStatus.OK);

    }

    @ApiOperation(value = "Update the  Question", httpMethod="PUT")
    @ApiResponses(value = {@ApiResponse(code = 200, message = " Question Updates Successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(value = QuestionRestURIConstants.QUESTION_UPDATE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Object> updateQuestion(@RequestBody QuestionBaseClass questionBaseClass) {

        try{
            if (questionBaseClass.getQuestionType().equals(QuestionConstants.QUESTION_TYPE_GENERAL)){

                GeneralQuestion generalQuestion=questionBaseClass.getGeneralQuestion();
                if(generalQuestion!=null)
                    if(this.questionService.saveQuestion(generalQuestion))
                        return new ResponseEntity<Object>(QuestionEnums.QUESTION_CREATED, HttpStatus.CREATED);

            }else if(questionBaseClass.getQuestionType().equals(QuestionConstants.QUESTION_TYPE_TECHNICAL)){
                TechnicalQuestion technicalQuestion=questionBaseClass.getTechnicalQuestion();
                if(technicalQuestion!=null){
                    if((technicalQuestion.getDomain()==null || technicalQuestion.getDomain()=="")){
                        technicalQuestion.setDomain(technicalQuestion.getOtherDomain());
                        Domain domain=new Domain();
                        domain.setDomain(technicalQuestion.getOtherDomain());
                        this.questionService.saveDomain(domain);
                    }
                    if(this.questionService.saveQuestion(technicalQuestion))
                        return new ResponseEntity<Object>(QuestionEnums.QUESTION_CREATED, HttpStatus.CREATED);
                }

            }else if(questionBaseClass.getQuestionType().equals(QuestionConstants.QUESTION_TYPE_VIDEO)){
                VideoQuestion videoQuestion=questionBaseClass.getVideoQuestion();
                if(videoQuestion!=null){
                    if((videoQuestion.getDomain()==null || videoQuestion.getDomain()=="")){
                        videoQuestion.setDomain(videoQuestion.getOtherDomain());
                        Domain domain=new Domain();
                        domain.setDomain(videoQuestion.getOtherDomain());
                        this.questionService.saveDomain(domain);
                    }
                    if(this.questionService.saveQuestion(videoQuestion))
                        return new ResponseEntity<Object>(QuestionEnums.QUESTION_CREATED, HttpStatus.CREATED);
                }
            }
        }catch (Exception e){
            return new ResponseEntity<Object>(QuestionEnums.QUESTION_NOT_CREATED, HttpStatus.OK);
        }

        return new ResponseEntity<Object>(QuestionEnums.QUESTION_NOT_CREATED, HttpStatus.OK);

    }


    @ApiOperation(value = "Question Details By Id and Question Type", httpMethod="GET")
    @ApiResponses(value = {@ApiResponse(code = 200, message = " Question Details Send"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(value = QuestionRestURIConstants.QUESTION_UPDATE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> questionDetails(@PathVariable("questionType") String questionType,
                                                  @PathVariable("id") Integer questionId) {

        try{
            if(questionType.equalsIgnoreCase(QuestionConstants.QUESTION_TYPE_GENERAL)){
                GeneralQuestion generalQuestion= (GeneralQuestion) this.questionService.findQuestionById(questionId);
                if(generalQuestion!=null)
                        return new ResponseEntity<Object>(generalQuestion, HttpStatus.OK);
            }else if(questionType.equalsIgnoreCase(QuestionConstants.QUESTION_TYPE_TECHNICAL)){
                TechnicalQuestion technicalQuestion= (TechnicalQuestion) this.questionService.findQuestionById(questionId);
                if(technicalQuestion!=null)
                    return new ResponseEntity<Object>(technicalQuestion, HttpStatus.OK);
            }else if(questionType.equalsIgnoreCase(QuestionConstants.QUESTION_TYPE_VIDEO)){
                VideoQuestion videoQuestion= (VideoQuestion) this.questionService.findQuestionById(questionId);
                if(videoQuestion!=null)
                    return new ResponseEntity<Object>(videoQuestion, HttpStatus.OK);            }
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

    }



    @ApiOperation(value = "Approve Question", httpMethod="POST")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Approve Question"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(value = QuestionRestURIConstants.QUESTION_APPROVE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
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
