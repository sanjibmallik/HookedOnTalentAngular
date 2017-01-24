package com.accion.recruitment.web.controller;

import com.accion.recruitment.common.constants.ClientConstants;
import com.accion.recruitment.common.constants.RequirementConstants;
import com.accion.recruitment.common.constants.RequirementURIConstants;
import com.accion.recruitment.common.constants.UserConstants;
import com.accion.recruitment.common.enums.RequirementEnums;
import com.accion.recruitment.jpa.entities.ClientDetails;
import com.accion.recruitment.jpa.entities.GeneralQuestion;
import com.accion.recruitment.jpa.entities.Positions;
import com.accion.recruitment.jpa.entities.User;
import com.accion.recruitment.service.ClientService;
import com.accion.recruitment.service.QuestionService;
import com.accion.recruitment.service.RequirementService;
import com.accion.recruitment.service.UserService;
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
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by MoinGodil on 1/11/17.
 */
@Controller
public class RequirementMgtController {

    @Autowired
    RequirementService requirementService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;

    private final SimpleDateFormat sdf = new SimpleDateFormat(UserConstants.DATE_FORMAT);

    @ApiOperation(value = "Requirement Created ", httpMethod="POST"
            , notes = "Creates Position/Requirement")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Requirement Created "),
            @ApiResponse(code = 200, message = "Successful respond send "),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(value = RequirementURIConstants.REQUIREMENT_CREATE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> createRequirement(@RequestBody Positions requirements,
                                                    Principal principal) {
        try{
            final Date currentDate = new Date();
            ClientDetails  clientDetails=new ClientDetails();
            User user=new User();

            requirements.setStatus(RequirementConstants.OPEN);
            requirements.setTechnicalScreener(RequirementConstants.ZERO);
            requirements.setRecruiter(RequirementConstants.ZERO);
            requirements.setAddNoMoreCandidates(RequirementConstants.NO);
            requirements.setReadyForInterview(RequirementConstants.NO);
            requirements.setIsQuestionAdded(RequirementConstants.NO);

            try{
                requirements.setCreatedBy(principal.getName());
                requirements.setUpdatedBy(principal.getName());
            }catch (Exception e){
                return new ResponseEntity<Object>(RequirementEnums.REQUIREMENT_NOT_CREATED.ResponseMsg(), HttpStatus.OK);
            }

            requirements.setCreatedDate(new Date(sdf.format(currentDate)));
            requirements.setUpdatedDate(new Date(sdf.format(currentDate)));

            try{
                requirements.setDuration(requirements.getDuration().concat(" ").concat(requirements.getDurationPeriod()));
            }catch (Exception e){
                return new ResponseEntity<Object>(RequirementEnums.DURATION.ResponseMsg(), HttpStatus.OK);
            }
            try{
                requirements.setBillRate(requirements.getBillRate().concat(" ").concat(requirements.getBillRatePeriod()));
            }catch(Exception e){
                return new ResponseEntity<Object>(RequirementEnums.BILL_RATE.ResponseMsg(), HttpStatus.OK);
            }
            try{
                requirements.setPayRate(requirements.getPayRate().concat(" ").concat(requirements.getPayRatePeriod()));
            }catch(Exception e){
                return new ResponseEntity<Object>(RequirementEnums.PAY_RATE.ResponseMsg(), HttpStatus.OK);
            }

            try{
                clientDetails=this.clientService.findClientDetailsByPropertyName(ClientConstants.CLIENT_NAME, requirements.getClientName());
            }catch (Exception e){
                return new ResponseEntity<Object>(RequirementEnums.CLIENT_NOT_EXIST.ResponseMsg(), HttpStatus.OK);
            }
            requirements.getClientDetails().add(clientDetails);

            try{
                user=this.userService.findUserByPropertyName(UserConstants.USER_NAME, principal.getName());
                user.getAccountManagerPositions().add(requirements);
            }catch (Exception e){
                return new ResponseEntity<Object>(RequirementEnums.REQUIREMENT_NOT_CREATED.ResponseMsg(), HttpStatus.OK);
            }

            if(this.requirementService.saveRequirements(requirements)){
                this.userService.saveUser(user);
                try{
                    List<GeneralQuestion> generalQuestionList= this.questionService.findRequirementGenericGeneralQuestion(RequirementConstants.YES);
                    for(GeneralQuestion generalQuestion:generalQuestionList){
                        generalQuestion.getGeneralQuestionPositionsSet().add(requirements);
                        this.questionService.saveGeneralQuestion(generalQuestion);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                return new ResponseEntity<Object>(RequirementEnums.REQUIREMENT_CREATED.ResponseMsg(), HttpStatus.CREATED);
            }

        }catch (Exception e){
            return new ResponseEntity<Object>(RequirementEnums.REQUIREMENT_NOT_CREATED.ResponseMsg(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Object>(RequirementEnums.REQUIREMENT_NOT_CREATED.ResponseMsg(), HttpStatus.INTERNAL_SERVER_ERROR);

     }


    @ApiOperation(value = "Get All the  Requirements", httpMethod="GET"
                , notes = "Display Position/Requirement")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Display ALL the  Requirement"),
                @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(value = RequirementURIConstants.GET_ALL_REQUIREMENT, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Set<Positions>> getAllRequirement(Principal principal) {
        try {
            List<Positions> requirementList=this.requirementService.findAllRequirement();
            Set<Positions> requirementSet=new LinkedHashSet<Positions>();
            for(Positions requirementObject:requirementList){
                Positions requirement=new Positions(requirementObject.getPositionId(),requirementObject.getClientName(),requirementObject.getContactPerson(),requirementObject.getDuration(),requirementObject.getStartDate(),
                        requirementObject.getClientLocation(),requirementObject.getBroadcastLocation(),requirementObject.getTypeOfReq(),requirementObject.getPriority(),requirementObject.getJobTitle(),
                        requirementObject.getOpenPositions(),requirementObject.getAddNoMoreCandidates(),requirementObject.getBillRate(),requirementObject.getPayRate(),requirementObject.getJobDescription(),
                        requirementObject.getPrimarySkill(),requirementObject.getSecondarySkill(),requirementObject.getIsQuestionAdded(),requirementObject.getRecruiter(),
                        requirementObject.getTechnicalScreener(),requirementObject.getAccountManager(),requirementObject.getStatus(),requirementObject.getReadyForInterview(),
                        requirementObject.getBillRatePeriod(),requirementObject.getDurationPeriod(),requirementObject.getPayRatePeriod(),requirementObject.getIsApprovedCandidateEmailSent(),
                        requirementObject.getIsCandidateAnswered());
                requirementSet.add(requirement);
            }
            return new ResponseEntity<Set<Positions>>(requirementSet, HttpStatus.OK);
        }catch (SQLException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @ApiOperation(value = "Edit Requirement", httpMethod="PUT"
            , notes = "Edit Position/Requirement")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Updated Requirement Successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(value = RequirementURIConstants.REQUIREMENT_UPDATE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Object> editRequirement(@RequestBody Positions requirements,
                                                  Principal principal) {
        final Date currentDate = new Date();
        Positions oldRequirements=new Positions();
        try{
            try{
                 oldRequirements= this.requirementService.findRequirementById(requirements.getPositionId());
                 if(oldRequirements==null)
                     return new ResponseEntity<Object>(RequirementEnums.REQUIREMENT_NOT_UPDATED.ResponseMsg(), HttpStatus.OK);
            }catch (Exception e){
                return new ResponseEntity<Object>(RequirementEnums.REQUIREMENT_NOT_UPDATED.ResponseMsg(), HttpStatus.OK);
            }


            requirements.setStatus(oldRequirements.getStatus());
            requirements.setCreatedDate(oldRequirements.getCreatedDate());
            requirements.setCreatedBy(oldRequirements.getCreatedBy());
            requirements.setReadyForInterview(oldRequirements.getReadyForInterview());
            requirements.setIsQuestionAdded(oldRequirements.getIsQuestionAdded());
            requirements.setTechnicalScreener(oldRequirements.getTechnicalScreener());
            requirements.setRecruiter(oldRequirements.getRecruiter());
            requirements.setAddNoMoreCandidates(oldRequirements.getAddNoMoreCandidates());

            try{
                requirements.setUpdatedBy(principal.getName());
            }catch (Exception e){
                return new ResponseEntity<Object>(RequirementEnums.REQUIREMENT_NOT_UPDATED.ResponseMsg(), HttpStatus.OK);
            }

            requirements.setUpdatedDate(new Date(sdf.format(currentDate)));

            try{
                requirements.setDuration(requirements.getDuration().concat(" ").concat(requirements.getDurationPeriod()));
            }catch (Exception e){
                return new ResponseEntity<Object>(RequirementEnums.DURATION.ResponseMsg(), HttpStatus.OK);
            }
            try{
                requirements.setBillRate(requirements.getBillRate().concat(" ").concat(requirements.getBillRatePeriod()));
            }catch(Exception e){
                return new ResponseEntity<Object>(RequirementEnums.BILL_RATE.ResponseMsg(), HttpStatus.OK);
            }
            try{
                requirements.setPayRate(requirements.getPayRate().concat(" ").concat(requirements.getPayRatePeriod()));
            }catch(Exception e){
                return new ResponseEntity<Object>(RequirementEnums.PAY_RATE.ResponseMsg(), HttpStatus.OK);
            }

            if(this.requirementService.saveRequirements(requirements))
                return new ResponseEntity<Object>(RequirementEnums.REQUIREMENT_UPDATED.ResponseMsg(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Object>(RequirementEnums.REQUIREMENT_NOT_UPDATED.ResponseMsg(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Object>(RequirementEnums.REQUIREMENT_NOT_UPDATED.ResponseMsg(), HttpStatus.OK);

    }


    @ApiOperation(value = "Get the Requirement based on positionId", httpMethod="GET"
            , notes = "Position/Requirement Detail")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Requirement Detail"),
                @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(value = RequirementURIConstants.GET_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> getRequirementById(@PathVariable("id") final int requirementId) {

        Positions requirementObject;
        try{
            requirementObject=this.requirementService.findRequirementById(requirementId);
            if(requirementObject!=null){
                Positions requirement=new Positions(requirementObject.getPositionId(),requirementObject.getClientName(),requirementObject.getContactPerson(),requirementObject.getDuration(),requirementObject.getStartDate(),
                        requirementObject.getClientLocation(),requirementObject.getBroadcastLocation(),requirementObject.getTypeOfReq(),requirementObject.getPriority(),requirementObject.getJobTitle(),
                        requirementObject.getOpenPositions(),requirementObject.getAddNoMoreCandidates(),requirementObject.getBillRate(),requirementObject.getPayRate(),requirementObject.getJobDescription(),
                        requirementObject.getPrimarySkill(),requirementObject.getSecondarySkill(),requirementObject.getIsQuestionAdded(),requirementObject.getRecruiter(),
                        requirementObject.getTechnicalScreener(),requirementObject.getAccountManager(),requirementObject.getStatus(),requirementObject.getReadyForInterview(),
                        requirementObject.getBillRatePeriod(),requirementObject.getDurationPeriod(),requirementObject.getPayRatePeriod(),requirementObject.getIsApprovedCandidateEmailSent(),
                        requirementObject.getIsCandidateAnswered());
                return new ResponseEntity<Object>(requirement, HttpStatus.OK);
            }
        }catch (SQLException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
