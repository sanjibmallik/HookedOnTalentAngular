package com.accion.recruitment.web.controller;

import com.accion.recruitment.beans.QuestionBaseClass;
import com.accion.recruitment.common.constants.*;
import com.accion.recruitment.common.enums.CandidateEnums;
import com.accion.recruitment.common.enums.RequirementEnums;
import com.accion.recruitment.jpa.entities.*;
import com.accion.recruitment.service.*;
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
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 13/01/17 00:11 AM#$
 */

@Controller
public class RequirementController {

    @Autowired
    RequirementService requirementService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private PositionCandidatesService positionCandidatesService;

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private CandidatePastHistoryService candidatePastHistoryService;

    @Autowired
    private CandidateResponseService candidateResponseService;

    @Autowired
    private RequirementEmailNotificationService requirementEmailNotificationService;


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
                        this.questionService.saveQuestion(generalQuestion);
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

    @ApiOperation(value = "Open Close Requirement", httpMethod="PUT")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Open or Close Requirement"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(value = RequirementURIConstants.OPEN_CLOSE_REQUIREMENT, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Object> openCloseRequirement(@PathVariable("id") final int requirementId,
                                                       @PathVariable("status") final String status,
                                                       Principal principal) {
        final Date currentDate = new Date();
        List<String> toUser= new ArrayList<String>();

        try{
            Positions requirements=this.requirementService.findRequirementById(requirementId);
            if(requirements!=null){
                try {
                    List<User> tsUserList = (List<User>) requirements.getTechnicalScreenerPositions();
                    for(User tsUser:tsUserList)
                        toUser.add(tsUser.getEmailId());
                }catch (Exception e){
                    return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
                }

                try{
                    List<User> recUserList = (List<User>) requirements.getRecruiterPositions();
                    for(User recUser:recUserList)
                            toUser.add(recUser.getEmailId());
                }catch (Exception e){
                    return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
                }

                try{
                    User amUser= this.userService.findUserByPropertyName(UserConstants.USER_NAME,requirements.getCreatedBy());
                    toUser.add(amUser.getEmailId());
                }catch (Exception e){
                    return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
                }

                try{
                    List<User> adminUserList=this.userService.findUserByRole(UserConstants.ADMIN);
                    for (User user: adminUserList)
                        toUser.add(user.getEmailId());
                }catch (Exception e){
                    return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
                }

                try{
                    requirements.setUpdatedBy(principal.getName());
                    requirements.setUpdatedDate(new Date(sdf.format(currentDate)));
                }catch (Exception e){
                    return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
                }

                if(this.requirementService.saveRequirements(requirements)){
                    if(status.equals(RequirementConstants.OPEN)){
                        requirements.setStatus(RequirementConstants.OPEN);
                        this.requirementEmailNotificationService.sendRequirementOpenStatus(requirements,toUser);
                    }if(status.equals(RequirementConstants.CLOSED)){
                        requirements.setStatus(RequirementConstants.CLOSED);
                        this.requirementEmailNotificationService.sendRequirementCloseStatus(requirements,toUser);
                    }
                    return new ResponseEntity(HttpStatus.OK);
                }
            }

        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ApiOperation(value = "Get the Requirement Candidates based on positionId", httpMethod="GET")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Requirement Candidates List"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(value = RequirementURIConstants.REQUIREMENT_CANDIDATES, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> getRequirementCandidates(@PathVariable("id") final int requirementId) {

        Positions requirementObject;
        try{
            requirementObject=this.requirementService.findRequirementById(requirementId);


        }catch (SQLException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ApiOperation(value = "Add No more Candidate", httpMethod="GET")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Maximum Resum Limit Reached"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(value = RequirementURIConstants.ADD_NO_MORE_CANDIDATE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> addNoMoreCandidate(@PathVariable( "id") final int requirementId) {


        return new ResponseEntity<Object>(RequirementEnums.ADD_NO_MORE_CANDIDATE.ResponseMsg(), HttpStatus.CREATED);
    }


    @ApiOperation(value = "Add Candidate to Requirement", httpMethod="GET")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Add Candidate to Requirement"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(value = RequirementURIConstants.ADD_CANDIDATE_TO_REQT, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> addCandidateToRequirement(@PathVariable String candidateID, @PathVariable String PositionId) {
        return new ResponseEntity<Object>(RequirementEnums.ADD_CANDIDATE_TO_REQT.ResponseMsg(), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Add multiple Candidate to requirement", httpMethod="POST")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Added successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(value = RequirementURIConstants.ADD_CANDIDATES_TO_REQ, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> addCandidateFromDB(@RequestParam(required = false,value="id") Integer requirementId,
                                                     @RequestParam(required = false,value="candidateId") String[] candidatesId,
                                                     Principal principal) {

        List<Integer> candidatesIdList=new ArrayList<Integer>();
        List<String> toUser= new ArrayList<String>();
        final Date currentDate = new Date();
        final UUID randomNumber=UUID.randomUUID();

        try{
            Positions requirements=this.requirementService.findRequirementById(requirementId);

            List<Candidates> positionsCandidatesList=(List)this.positionCandidatesService.findPositionCandidatesByRequirementId(requirementId);

            for(Candidates candidates:positionsCandidatesList)
                candidatesIdList.add(candidates.getCandidateId());

            for(String candidateId:candidatesId){
                int cId ;
                cId=Integer.parseInt(candidateId);

                if(!(candidatesIdList.contains(cId))){
                    Candidates candidates=this.candidateService.findCandidatesById(cId);

                    PositionCandidates positionCandidates=new PositionCandidates();
                    positionCandidates.setCandidateLink(randomNumber.toString());
                    positionCandidates.setCandidates(candidates);
                    positionCandidates.setPositions(requirements);
                    positionCandidates.setLinkValidity(PositionCandidatesConstant.ACTIVE);
                    positionCandidates.setScreenedStatus(PositionCandidatesConstant.UN_SCREENED);
                    positionCandidates.setCandidateEnableDisable(PositionCandidatesConstant.ENABLE);
                    positionCandidates.setIsShortListed(PositionCandidatesConstant.NO);
                    positionCandidates.setLinkCount(PositionCandidatesConstant.ONE);
                    positionCandidates.setAutoReminderCount(PositionCandidatesConstant.ZERO);
                    positionCandidates.setStatus(PositionCandidatesConstant.EMAIL_SENT);
                    positionCandidates.setAddedBy(principal.getName());
                    positionCandidates.setCreatedDate(currentDate);
                    positionCandidates.setUpdatedDate(currentDate);
                    positionCandidates.setUpdatedBy(principal.getName());

                    CandidatePastHistory candidatePastHistory=new CandidatePastHistory();
                    try{

                        candidatePastHistory.setPositionName(requirements.getJobTitle());
                        candidatePastHistory.setClientName(requirements.getClientName());
                        candidatePastHistory.setPrimarySkill(requirements.getPrimarySkill());
                        candidatePastHistory.setPositionId(requirementId);
                        candidatePastHistory.setCandidatePastHistory(candidates);

                        candidatePastHistory.setAddedBy(principal.getName());
                        candidatePastHistory.setCreatedDate(currentDate);
                        candidatePastHistory.setUpdatedDate(currentDate);
                        candidatePastHistory.setUpdatedBy(principal.getName());

                    }catch (Exception e){
                        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
                    }

                    try{
                        User user = this.userService.findUserByPropertyName(UserConstants.USER_NAME,requirements.getCreatedBy());
                        toUser.add(user.getEmailId());
                    }catch (Exception e){
                        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
                    }

                    try{
                        List<User> recUserList = (List<User>) requirements.getRecruiterPositions();
                        for(User recUser:recUserList)
                            toUser.add(recUser.getEmailId());
                    }catch (Exception e){
                        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
                    }

                    try{

                        this.candidatePastHistoryService.saveCandidatePastHistory(candidatePastHistory);
                        if(this.positionCandidatesService.savePositionCandidates(positionCandidates))
                            this.requirementEmailNotificationService.sendCandidateMail(toUser, requirements, candidates, positionCandidates);

                    }catch (Exception e){
                        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                }
                return new ResponseEntity<Object>(HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }



    @ApiOperation(value = "Requirement Candidate Regenerate Link", httpMethod="POST"
            , notes = "Regenerate Link")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Regenerated Link Successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error")})

    @RequestMapping(value = RequirementURIConstants.REGENERATE_LINK, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> regenerateLink(@PathVariable("candidateId") Integer candidateId,
                                                 @PathVariable("id") Integer requirementId,
                                                 Principal principal) {

        final UUID randomNumber=UUID.randomUUID();
        List<String> toUser= new ArrayList<String>();
        final Date currentDate = new Date();
        try{
            PositionCandidates positionCandidates=this.positionCandidatesService.findPositionCandidatesByRequirementIdAndCandidateId(requirementId,candidateId);

            if(positionCandidates.getLinkCount()<=3){

                try{
                    this.candidateResponseService.deleteCandidateGeneralQuestionResponseByQuery(requirementId, candidateId);
                    this.candidateResponseService.deleteCandidateTechnicalQuestionResponseByQuery(requirementId, candidateId);
                    this.candidateResponseService.deleteCandidateVideoQuestionResponseByQuery(requirementId, candidateId);
                    this.candidateResponseService.deleteCandidateSelfRatingResponseByQuery(requirementId,candidateId);
                    this.candidateResponseService.deleteCandidateFinalResultResponseByQuery(requirementId, candidateId);
                    this.candidatePastHistoryService.deleteCandidatePastHistoryResponseByQuery(requirementId,candidateId);
                }catch (Exception e){
                    return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
                }

                try{
                    positionCandidates.setLinkCount(positionCandidates.getLinkCount()+1);
                    positionCandidates.setEvalutedByTS(null);
                    positionCandidates.setLinkValidity(PositionCandidatesConstant.ACTIVE);
                    positionCandidates.setStatus(PositionCandidatesConstant.EMAIL_SENT);
                    positionCandidates.setIsShortListed(PositionCandidatesConstant.NO);
                    positionCandidates.setScreenedStatus(PositionCandidatesConstant.UN_SCREENED);
                    positionCandidates.setCandidateEnableDisable(PositionCandidatesConstant.ENABLE);
                    positionCandidates.setAutoReminderCount(PositionCandidatesConstant.ZERO);
                    positionCandidates.setCandidateLink(randomNumber.toString());
                    positionCandidates.setUpdatedDate(currentDate);
                    positionCandidates.setUpdatedBy(principal.getName());
                }catch (Exception e){
                    return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
                }

                Positions requirements=this.requirementService.findRequirementById(requirementId);
                Candidates candidates=this.candidateService.findCandidatesById(candidateId);

                try{
                    User user = this.userService.findUserByPropertyName(UserConstants.USER_NAME,requirements.getCreatedBy());
                    toUser.add(user.getEmailId());
                }catch (Exception e){
                    return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
                }

                try{
                    List<User> recUserList = (List<User>) requirements.getRecruiterPositions();
                    for(User recUser:recUserList)
                        toUser.add(recUser.getEmailId());
                }catch (Exception e){
                    return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
                }

                CandidatePastHistory candidatePastHistory=new CandidatePastHistory();
                try{

                    candidatePastHistory.setPositionName(requirements.getJobTitle());
                    candidatePastHistory.setClientName(requirements.getClientName());
                    candidatePastHistory.setPrimarySkill(requirements.getPrimarySkill());
                    candidatePastHistory.setPositionId(requirementId);
                    candidatePastHistory.setCandidatePastHistory(candidates);
                    candidatePastHistory.setUpdatedDate(currentDate);
                    candidatePastHistory.setUpdatedBy(principal.getName());
                }catch (Exception e){
                    return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
                }

                try{
                    this.candidatePastHistoryService.saveCandidatePastHistory(candidatePastHistory);
                    if(this.positionCandidatesService.savePositionCandidates(positionCandidates))
                        this.requirementEmailNotificationService.sendCandidateMail(toUser, requirements, candidates, positionCandidates);
                    return new ResponseEntity(HttpStatus.OK);
                }catch (Exception e){
                    return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ApiOperation(value = "Display Requirement Technical Screener  ", httpMethod="GET")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Display Requirement Technical Screener"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(value = RequirementURIConstants.DISPLAY_TECH_SCREENER, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> displayRequirementTS(@PathVariable(required = true,value="id") Integer requirementId) {

        try{
            Positions requirements=this.requirementService.findRequirementById(requirementId);

            List<User> technicalScreenerUsersList= (List<User>) requirements.getTechnicalScreenerPositions();

          /*  for(int i=0;i<technicalScreenerUsersList.size();i++){
                Collection<TechnicalScreenerSkills> technicalScreenerSkillsList=technicalScreenerUsersList.get(i).getTechnicalScreenerDetailsDSkillsSet();
                for(TechnicalScreenerSkills technicalScreenerSkills:technicalScreenerSkillsList){
                    technicalScreenerUsersList.get(i).setPrimarySkills(technicalScreenerSkills.getPrimarySkills());
                    technicalScreenerUsersList.get(i).setSecondarySkills(technicalScreenerSkills.getSecondarySkills());
                }
            }

            Set<User> positionsHashSets = new HashSet<>();
            positionsHashSets.addAll(technicalScreenerUsersList);
            technicalScreenerUsersList.clear();
            technicalScreenerUsersList.addAll(positionsHashSets);
          */

            return new ResponseEntity<Object>(technicalScreenerUsersList,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @ApiOperation(value = "Add Technical Screener to Requirement", httpMethod="POST")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Add Technical Screener to Requirement"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(value = RequirementURIConstants.ADD_TECH_SCREENER, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> addTSToRequirement(@RequestParam(required = true,value="id") Integer requirementId,
                                                     @RequestParam(required = true,value="userIds") String[] technicalScreenerIds) {

        List<Integer> existingTsListId=new ArrayList<Integer>();
        try{
            Positions requirements=this.requirementService.findRequirementById(requirementId);
            if(requirements!=null){

                final List<User> userList= (List<User>) requirements.getTechnicalScreenerPositions();

                for(User user:userList)
                    existingTsListId.add(user.getId());

                for(String technicalScreenerId:technicalScreenerIds){
                    int tsId ;
                    tsId=Integer.parseInt(technicalScreenerId);
                    try{
                        if(!(existingTsListId.contains(tsId))){
                            User user=this.userService.findUserById(tsId);
                            requirements.setTechnicalScreener(String.valueOf(technicalScreenerIds.length));
                            requirements.getTechnicalScreenerPositions().add(user);
                            if(this.requirementService.saveRequirements(requirements))
                                this.requirementEmailNotificationService.sendRequirementMailToTs(requirements,user);
                            userList.remove(user);
                        }
                    }catch (Exception e){
                        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                }
                return new ResponseEntity<Object>(RequirementEnums.ADD_TS_TO_REQT.ResponseMsg(), HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ApiOperation(value = "Delete Requirement Technical Screener  ", httpMethod="DELETE")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Delete Requirement Technical Screener"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(value = RequirementURIConstants.DELETE_TECH_SCREENER, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Object> deleteRequirementTS(@PathVariable(required = true,value="id") Integer requirementId,
                                                      @PathVariable(required = true,value="userId") Integer screenerId) {

        try{
            Positions requirements=this.requirementService.findRequirementById(requirementId);
            String query="delete  FROM default.technical_screener_positions where position_id='"+requirementId+"' and user_id='"+screenerId+"'";
            if(this.requirementService.deleteRecordByQuery(query)){
                requirements.setTechnicalScreener(RequirementConstants.ZERO);
                if(this.requirementService.saveRequirements(requirements))
                    return new ResponseEntity(HttpStatus.OK);
            }

        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }



    @ApiOperation(value = "Display Requirement Recruiter  ", httpMethod="GET")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Display Requirement Recruiter"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(value = RequirementURIConstants.DISPLAY_RECRUITER, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> displayRequirementRecruiter(@PathVariable(required = true,value="id") Integer requirementId) {

        try{
            Positions requirements=this.requirementService.findRequirementById(requirementId);

            List<User> recruiterUsersList= (List<User>) requirements.getRecruiterPositions();

            return new ResponseEntity<Object>(recruiterUsersList,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Add Recruiter to Requirement", httpMethod="POST")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Add Recruiters to Requirement"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(value = RequirementURIConstants.ADD_RECRUITER, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> addRecruiterToRequirement(@RequestParam(required = true,value="id") Integer requirementId,
                                                            @RequestParam(required = true,value="recruiterIdS") String[] recruiterIdS) {

        List<Integer> integerList=new ArrayList<Integer>();
        try{
            Positions requirements=this.requirementService.findRequirementById(requirementId);
            if(requirements!=null){
                final List<User> recUserList= (List<User>) requirements.getRecruiterPositions();

                for(User user:recUserList)
                    integerList.add(user.getId());

                for(String recruiterId:recruiterIdS){
                    int rId ;
                    rId=Integer.parseInt(recruiterId);
                    try{
                        if(!(integerList.contains(rId))){
                            User user=this.userService.findUserById(rId);
                            requirements.setRecruiter(String.valueOf(recruiterIdS.length));
                            requirements.getRecruiterPositions().add(user);
                            if(this.requirementService.saveRequirements(requirements))
                                this.requirementEmailNotificationService.sendRequirementMailToRecruiter(requirements,user);
                            recUserList.remove(user);
                        }

                    }catch (Exception e){
                        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                }
                return new ResponseEntity<Object>(RequirementEnums.ADD_RECRUITER_TO_REQT.ResponseMsg(), HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }



    @ApiOperation(value = "Delete Requirement Recruiter  ", httpMethod="DELETE")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Delete Requirement Recruiter"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(value = RequirementURIConstants.DELETE_RECRUITER, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Object> deleteRequirementRecruiter(@PathVariable(required = true,value="id") Integer requirementId,
                                                      @PathVariable(required = true,value="userId") Integer recruiterId) {

        try{
            String query="delete  FROM default.recruiter_positions where position_id='"+requirementId+"' and user_id='"+recruiterId+"'";
            if(this.requirementService.deleteRecordByQuery(query)){
                    return new ResponseEntity(HttpStatus.OK);
            }

        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }



    @ApiOperation(value = "Fetches Questions from DB", httpMethod="POST")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Fetches Questions from DB"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(value = RequirementURIConstants.ADD_QUESTION_FROM_DB, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> addQuestionsFromDB(@PathVariable String questionType) {

        ArrayList<QuestionBaseClass> questions = new ArrayList<QuestionBaseClass>();
        if (questionType.equals(HookedOnConstants.QUESTION_TYPE_GENERAL))
        {
            //TODO Fetching logic from DB
            GeneralQuestion generalQuestion = new GeneralQuestion();
            generalQuestion.setCreatedBy("Moin");
            generalQuestion.setAnswer("Human");
            generalQuestion.setOption1("Why");
            generalQuestion.setOption2("Whom");
            generalQuestion.setOption3("How");
            generalQuestion.setOption4("When");
            generalQuestion.setOption5("Human");
            generalQuestion.setOption6("Whatever");
            generalQuestion.setSubAnswer("Ok");
            generalQuestion.setQuestionName("GENERAL");
            generalQuestion.setId(1);
            /*questions.add(generalQuestion)*///TODO add all question to return object
        }
        else if (questionType.equals(HookedOnConstants.QUESTION_TYPE_TECHNICAL))
        {
            //TODO Fetching logic from DB
            TechnicalQuestion techQuestion = new TechnicalQuestion();
            techQuestion.setCreatedBy("Moin");
            techQuestion.setAnswer("Human");
            techQuestion.setOption1("Why");
            techQuestion.setOption2("Whom");
            techQuestion.setOption3("How");
            techQuestion.setOption4("When");
            techQuestion.setOption5("Human");
            techQuestion.setOption6("Whatever");
            techQuestion.setSubAnswer("Ok");
            techQuestion.setId(2);
            techQuestion.setQuestionName("TECHNICAL");
            techQuestion.setDomain("JAVA");
            techQuestion.setLevel("Expert");
           /* questions.add(techQuestion);*///TODO add all question to return object
        }
        else if (questionType.equals(HookedOnConstants.QUESTION_TYPE_VIDEO))
        {
            //TODO Fetching logic from DB
            VideoQuestion vidQuestion = new VideoQuestion();
            vidQuestion.setCreatedBy("Moin");
            vidQuestion.setQuestionName("VIDEO");
            vidQuestion.setDomain("Angular");
            vidQuestion.setId(3);
          /*  questions.add(vidQuestion);*///TODO add all question to return object
        }

        return new ResponseEntity<Object>(questions, HttpStatus.CREATED);
    }

    @ApiOperation(value = "ShortList Candidate for Requirement", httpMethod="GET")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "ShortList Candidate for Requirement"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(value = RequirementURIConstants.SHORTLIST_CANDIDATE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> shortlistCandidate(@PathVariable String candidateID, @PathVariable String PositionId) {
        return new ResponseEntity<Object>(RequirementEnums.SHORTLIST_CANDIDATE.ResponseMsg(), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Submit Candidate to Client", httpMethod="GET")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Submit Candidate to Client"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(value = RequirementURIConstants.SUBMIT_TO_CLIENT, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> submitToClient(@PathVariable String candidateID, @PathVariable String PositionId) {
        return new ResponseEntity<Object>(RequirementEnums.SUBMIT_TO_CLIENT.ResponseMsg(), HttpStatus.CREATED);
    }
}
