package com.accion.recruitment.web.controller;

import com.accion.recruitment.beans.CandidateSearch;
import com.accion.recruitment.common.constants.CandidateConstants;
import com.accion.recruitment.common.constants.CandidateRestURIConstants;
import com.accion.recruitment.common.constants.UserConstants;
import com.accion.recruitment.common.enums.CandidateEnums;
import com.accion.recruitment.jpa.entities.*;
import com.accion.recruitment.service.CandidateResponseService;
import com.accion.recruitment.service.CandidateService;
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
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 25/01/17 00:11 AM#$
 */

@Controller
public class CandidateController {

    @Autowired
    CandidateService candidateService;

    @Autowired
    CandidateResponseService candidateResponseService;

    @Autowired
    private UserService userService;

    private final SimpleDateFormat sdf = new SimpleDateFormat(UserConstants.DATE_FORMAT);

    @ApiOperation(value = "Create Candidate ", httpMethod="POST"
            , notes = "Creates Candidate")
    @ApiResponses(value = {@ApiResponse(code = 201, message = " Candidate Created Successfully "),
            @ApiResponse(code = 201, message = " Successful Respond Send "),
            @ApiResponse(code = 500, message = "Internal Server Error")})

    @RequestMapping(value = CandidateRestURIConstants.CREATE_CANDIDATE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> createCandidate(@RequestBody Candidates candidates,
                                                    Principal principal) {
        final Date currentDate = new Date();
        try{
            try{
                String candidateFullName=candidates.getFirstName()+" "+candidates.getLastName();
                candidates.setCandidateName(candidateFullName);
            }catch(Exception e){
                return new ResponseEntity<Object>(CandidateEnums.CANDIDATE_NOT_CREATED.ResponseMsg(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            try{
                candidates.setCreatedBy(principal.getName());
                candidates.setUpdatedBy(principal.getName());
            }catch (Exception e){
                return new ResponseEntity<Object>(CandidateEnums.CANDIDATE_NOT_CREATED.ResponseMsg(), HttpStatus.INTERNAL_SERVER_ERROR);
            }

            candidates.setCreatedDate(new Date(sdf.format(currentDate)));
            candidates.setUpdatedDate(new Date(sdf.format(currentDate)));

            try{
                byte[] bytes = candidates.getProfileResume().getBytes();
                candidates.setResume(bytes);
                candidates.setResumeType(candidates.getProfileResume().getContentType());
                candidates.setOriginalFileName(candidates.getProfileResume().getOriginalFilename());
            }catch (Exception e){
                return new ResponseEntity<Object>(CandidateEnums.CANDIDATE_PROFILE_EXCEPTION.ResponseMsg(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            try{
                User recUser=this.userService.findUserByPropertyName(UserConstants.USER_NAME,principal.getName());
                candidates.getRecruiterCandidates().add(recUser);
            }catch (Exception e){
                return new ResponseEntity<Object>(CandidateEnums.CANDIDATE_NOT_CREATED.ResponseMsg(), HttpStatus.OK);
            }
            if(this.candidateService.saveCandidates(candidates))
                return new ResponseEntity<Object>(CandidateEnums.CANDIDATE_CREATED.ResponseMsg(), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<Object>(CandidateEnums.CANDIDATE_NOT_CREATED.ResponseMsg(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Object>(CandidateEnums.CANDIDATE_NOT_CREATED.ResponseMsg(), HttpStatus.INTERNAL_SERVER_ERROR);
    }




    @ApiOperation(value = "Get All Candidates  ", httpMethod="GET")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "All Candidate Details"),
            @ApiResponse(code = 500, message = "Internal Server Error")})

    @RequestMapping(value = CandidateRestURIConstants.GET_ALL_CANDIDATE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Set<Candidates>> getAllCandidates(Principal principal) {

        try{

            List<Candidates> candidatesList=this.candidateService.findAllCandidates();
            Set<Candidates> candidatesSet=new LinkedHashSet<Candidates>();

            for(Candidates candidateObject:candidatesList){
                Candidates candidates=new Candidates(candidateObject.getCandidateId(),candidateObject.getEmailId(),candidateObject.getMiddleName(),candidateObject.getLastName(),candidateObject.getFirstName(),
                        candidateObject.getCellPhoneNumber(),candidateObject.getHomePhoneNumber(),candidateObject.getLastName(),candidateObject.getJobTitle(),candidateObject.getPrimarySkill(),
                        candidateObject.getSecondarySkill(),candidateObject.getCurrentLocation(),candidateObject.getTotalExperience(),candidateObject.getTotalExperiencePeriod(),candidateObject.getUsExperience(),
                        candidateObject.getUsExperiencePeriod(),candidateObject.getAddress(),candidateObject.getImmigrationStatus(),candidateObject.getAddress2(),candidateObject.getCity(),
                        candidateObject.getState(),candidateObject.getCountry(),candidateObject.getCurrentEmployer(),candidateObject.getStatus(),candidateObject.getBillRate(),
                        candidateObject.getBillRateCurrency(),candidateObject.getPayRate(),candidateObject.getPayRateCurrency(),candidateObject.getNote(),candidateObject.getAllReadyAdded(),candidateObject.getEvaluatedByTS(),candidateObject.getFinalVerdict(),
                        candidateObject.getLinkCount(),candidateObject.getAddedBy(),candidateObject.getIsShortListed(),candidateObject.getScreenedStatus(),candidateObject.getEnableDisableStatus(),
                        candidateObject.getComment(),candidateObject.getInterviewStatus(),candidateObject.getScore(),candidateObject.getPositionId(),candidateObject.getPositionName());
                candidatesSet.add(candidates);
            }
            return new ResponseEntity<Set<Candidates>>(candidatesSet, HttpStatus.OK);
        }catch (SQLException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }



    @ApiOperation(value = "Update the  Candidate ", httpMethod="PUT")
    @ApiResponses(value = {@ApiResponse(code = 200, message = " Candidate Updated Successfully "),
            @ApiResponse(code = 500, message = "Internal Server Error")})

    @RequestMapping(value = CandidateRestURIConstants.UPDATE_CANDIDATE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Object> updateCandidate(@RequestBody Candidates candidates,
                                                    Principal principal) {
        final Date currentDate = new Date();
        Candidates candidateObject;
        try{
            try{
                candidateObject=this.candidateService.findCandidatesById(candidates.getCandidateId());
                if(candidateObject!=null)
                    return new ResponseEntity<Object>(CandidateEnums.CANDIDATE_NOT_UPDATED.ResponseMsg(), HttpStatus.OK);
            }catch (Exception e){
                return new ResponseEntity<Object>(CandidateEnums.CANDIDATE_NOT_UPDATED.ResponseMsg(), HttpStatus.INTERNAL_SERVER_ERROR);
            }

            try{
                String candidateFullName=candidates.getFirstName()+" "+candidates.getLastName();
                candidates.setCandidateName(candidateFullName);
            }catch(Exception e){
                return new ResponseEntity<Object>(CandidateEnums.CANDIDATE_NOT_UPDATED.ResponseMsg(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            try{
                candidates.setUpdatedBy(principal.getName());
            }catch (Exception e){
                return new ResponseEntity<Object>(CandidateEnums.CANDIDATE_NOT_UPDATED.ResponseMsg(), HttpStatus.INTERNAL_SERVER_ERROR);
            }

            candidates.setUpdatedDate(new Date(sdf.format(currentDate)));

            try{
                byte[] bytes = candidates.getProfileResume().getBytes();
                if(candidates.getProfileResume().isEmpty()){
                    candidates.setResume(candidateObject.getResume());
                    candidates.setResumeType(candidateObject.getResumeType());
                    candidates.setOriginalFileName(candidateObject.getOriginalFileName());

                }else{
                    candidates.setResume(bytes);
                    candidates.setResumeType(candidates.getProfileResume().getContentType());
                    candidates.setOriginalFileName(candidates.getProfileResume().getOriginalFilename()); }

            }catch (Exception e){
                return new ResponseEntity<Object>(CandidateEnums.CANDIDATE_PROFILE_EXCEPTION.ResponseMsg(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            candidates.setStatus(candidateObject.getStatus());
            candidates.setCreatedDate(candidateObject.getCreatedDate());
            candidates.setCreatedBy(candidateObject.getCreatedBy());

            if(this.candidateService.saveCandidates(candidates))
                return new ResponseEntity<Object>(CandidateEnums.CANDIDATE_UPDATED.ResponseMsg(), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<Object>(CandidateEnums.CANDIDATE_NOT_UPDATED.ResponseMsg(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Object>(CandidateEnums.CANDIDATE_NOT_UPDATED.ResponseMsg(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ApiOperation(value = "Search Candidate", httpMethod="POST"
            , notes = "Search Candidate")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Search Candidate Details "),
            @ApiResponse(code = 500, message = "Internal Server Error")})

    @RequestMapping(value = CandidateRestURIConstants.SEARCH_CANDIDATE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> searchCandidate(@RequestBody CandidateSearch candidateSearch) {
        List<Candidates> candidatesList=new ArrayList<Candidates>();
        try{
            if(candidateSearch.getExperience().equals(null) || candidateSearch.getExperience().equals("")){
                candidateSearch.setExperience("0");
            }

            final String query="SELECT * from default.candidates WHERE primarySkill LIKE '%"+candidateSearch.getPrimarySkill()+"%' AND secondarySkill LIKE '%"+candidateSearch.getSecondarySkill()+"%' AND" +
                    " candidateName LIKE '%"+candidateSearch.getCandidateName()+"%' AND emailId LIKE '%"+candidateSearch.getEmailId()+"%'  AND cellPhoneNumber LIKE '%"+candidateSearch.getPhoneNumber()+"%' AND " +
                    "totalExperience >="+candidateSearch.getExperience()+" AND jobTitle LIKE '%"+candidateSearch.getJobTitle()+"%';";
            try{
                candidatesList=this.candidateService.searchCandidatesByQuery(query);
                return new ResponseEntity<Object>(candidatesList, HttpStatus.OK);
            }catch (SQLException e){
                return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
            }catch (Exception e){
                return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Candidate History List", httpMethod="GET"
            , notes = "Search Candidate")
    @ApiResponses(value = {@ApiResponse(code = 200, message = " Candidate  History Details "),
            @ApiResponse(code = 500, message = "Internal Server Error")})

    @RequestMapping(value = CandidateRestURIConstants.CANDIDATE_HISTORY, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Set<CandidatePastHistory>> candidateHistory(@PathVariable("id") Integer candidateId,
                                                   Principal principal) {

        Candidates candidateObject;
        try{
            candidateObject=this.candidateService.findCandidatesById(candidateId);
            if(candidateObject!=null){
                Set<CandidatePastHistory> candidatePastHistoryList=  candidateObject.getCandidatePastHistorySet();
                Set<CandidatePastHistory> candidatePastHistoryFinalList= new HashSet<CandidatePastHistory>();

                for(CandidatePastHistory candidatePastHistory:candidatePastHistoryList){
                    CandidateFinalResult candidateFinalResult=this.candidateResponseService.findCandidateFinalResultByPositionIdAndCandidateId(candidatePastHistory.getPositionId(),candidateId);
                    try{
                        String score="Pending";
                        try{
                            score=String.valueOf(candidateFinalResult.getAverage());
                        }catch (Exception e){e.printStackTrace();
                            score="";
                        }

                        if(score.equals("null")||score.equals("")){
                            score="Pending";
                        }
                        candidatePastHistory.setAverage(score);
                        candidatePastHistory.setFinalVerdict(candidateFinalResult.getFinalVerdict());
                        candidatePastHistory.setEvaluatedBy(candidateFinalResult.getEvalutedByTS());
                        PositionCandidates positionCandidates= (PositionCandidates) candidatePastHistory.getCandidatePastHistory().getPositionCandidatesSet();
                        candidatePastHistory.setAddedBy(positionCandidates.getAddedBy());
                        candidatePastHistory.setCreatedDate(positionCandidates.getCreatedDate());
                        candidatePastHistoryFinalList.add(candidatePastHistory);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                return new ResponseEntity<Set<CandidatePastHistory>>(candidatePastHistoryFinalList, HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ApiOperation(value = "Download Resume ", httpMethod="GET"
            , notes = "Download Resume")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Download Resume "),
            @ApiResponse(code = 500, message = "Internal Server Error")})

    @RequestMapping(value = CandidateRestURIConstants.DOWNLOAD_RESUME, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> getCandidateResume(@PathVariable("id")Integer candidateId,
                                                     HttpServletResponse response) {

        try{
            Candidates candidates=new Candidates();
            try{
                candidates=this.candidateService.findCandidatesById(candidateId);
            }catch (Exception e){
                e.printStackTrace();
            }
            try {
                response.setHeader("Content-Disposition", "inline;filename=\"" +candidates.getOriginalFileName()+ "\"");
                OutputStream out = response.getOutputStream();
                Blob blob= new javax.sql.rowset.serial.SerialBlob(candidates.getResume());
                response.setContentType(candidates.getResumeType());
                IOUtils.copy(blob.getBinaryStream(), out);
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    @ApiOperation(value = "Get the Candidate Details By Id ", httpMethod="GET"
            , notes = "Candidate Details")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Candidate Details"),
            @ApiResponse(code = 500, message = "Internal Server Error")})

    @RequestMapping(value = CandidateRestURIConstants.GET_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> getCandidateDetailsById(@PathVariable("id") final int candidateId) {

        Candidates candidateObject;
        try{
            candidateObject=this.candidateService.findCandidatesById(candidateId);
            if(candidateObject!=null){
                Candidates candidates=new Candidates(candidateObject.getCandidateId(),candidateObject.getEmailId(),candidateObject.getMiddleName(),candidateObject.getLastName(),candidateObject.getFirstName(),
                        candidateObject.getCellPhoneNumber(),candidateObject.getHomePhoneNumber(),candidateObject.getLastName(),candidateObject.getJobTitle(),candidateObject.getPrimarySkill(),
                        candidateObject.getSecondarySkill(),candidateObject.getCurrentLocation(),candidateObject.getTotalExperience(),candidateObject.getTotalExperiencePeriod(),candidateObject.getUsExperience(),
                        candidateObject.getUsExperiencePeriod(),candidateObject.getAddress(),candidateObject.getImmigrationStatus(),candidateObject.getAddress2(),candidateObject.getCity(),
                        candidateObject.getState(),candidateObject.getCountry(),candidateObject.getCurrentEmployer(),candidateObject.getStatus(),candidateObject.getBillRate(),
                        candidateObject.getBillRateCurrency(),candidateObject.getPayRate(),candidateObject.getPayRateCurrency(),candidateObject.getNote(),candidateObject.getAllReadyAdded(),candidateObject.getEvaluatedByTS(),candidateObject.getFinalVerdict(),
                        candidateObject.getLinkCount(),candidateObject.getAddedBy(),candidateObject.getIsShortListed(),candidateObject.getScreenedStatus(),candidateObject.getEnableDisableStatus(),
                        candidateObject.getComment(),candidateObject.getInterviewStatus(),candidateObject.getScore(),candidateObject.getPositionId(),candidateObject.getPositionName());
                return new ResponseEntity<Object>(candidates, HttpStatus.OK);
            }
        }catch (SQLException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ApiOperation(value = "Get the Candidate Details By EmailId ", httpMethod="GET"
            , notes = "Candidate Details")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Candidate Details"),
            @ApiResponse(code = 500, message = "Internal Server Error")})

    @RequestMapping(value = CandidateRestURIConstants.GET_BY_EMAIL, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> getCandidateDetailsByEmailId(@PathVariable("emailId") final String emailId) {

        Candidates candidateObject;
        try{
            candidateObject=this.candidateService.findCandidatesByPropertyName(CandidateConstants.EMAIL_ID, emailId);
            if(candidateObject!=null){
                Candidates candidates=new Candidates(candidateObject.getCandidateId(),candidateObject.getEmailId(),candidateObject.getMiddleName(),candidateObject.getLastName(),candidateObject.getFirstName(),
                        candidateObject.getCellPhoneNumber(),candidateObject.getHomePhoneNumber(),candidateObject.getLastName(),candidateObject.getJobTitle(),candidateObject.getPrimarySkill(),
                        candidateObject.getSecondarySkill(),candidateObject.getCurrentLocation(),candidateObject.getTotalExperience(),candidateObject.getTotalExperiencePeriod(),candidateObject.getUsExperience(),
                        candidateObject.getUsExperiencePeriod(),candidateObject.getAddress(),candidateObject.getImmigrationStatus(),candidateObject.getAddress2(),candidateObject.getCity(),
                        candidateObject.getState(),candidateObject.getCountry(),candidateObject.getCurrentEmployer(),candidateObject.getStatus(),candidateObject.getBillRate(),
                        candidateObject.getBillRateCurrency(),candidateObject.getPayRate(),candidateObject.getPayRateCurrency(),candidateObject.getNote(),candidateObject.getAllReadyAdded(),candidateObject.getEvaluatedByTS(),candidateObject.getFinalVerdict(),
                        candidateObject.getLinkCount(),candidateObject.getAddedBy(),candidateObject.getIsShortListed(),candidateObject.getScreenedStatus(),candidateObject.getEnableDisableStatus(),
                        candidateObject.getComment(),candidateObject.getInterviewStatus(),candidateObject.getScore(),candidateObject.getPositionId(),candidateObject.getPositionName());
                return new ResponseEntity<Object>(candidates, HttpStatus.OK);
            }
        }catch (SQLException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ApiOperation(value = "Get the Candidate Details By Contact Number ", httpMethod="GET"
            , notes = "Candidate Details")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Candidate Details"),
            @ApiResponse(code = 500, message = "Internal Server Error")})

    @RequestMapping(value = CandidateRestURIConstants.GET_BY_CONTACT_NUMBER, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> getCandidateDetailsByContactNumber(@PathVariable("contactNumber") final String contactNumber) {

        Candidates candidateObject;
        try{
            candidateObject=this.candidateService.findCandidatesByPropertyName(CandidateConstants.CONTACT_NUMBER, contactNumber);
            if(candidateObject!=null){
                Candidates candidates=new Candidates(candidateObject.getCandidateId(),candidateObject.getEmailId(),candidateObject.getMiddleName(),candidateObject.getLastName(),candidateObject.getFirstName(),
                        candidateObject.getCellPhoneNumber(),candidateObject.getHomePhoneNumber(),candidateObject.getLastName(),candidateObject.getJobTitle(),candidateObject.getPrimarySkill(),
                        candidateObject.getSecondarySkill(),candidateObject.getCurrentLocation(),candidateObject.getTotalExperience(),candidateObject.getTotalExperiencePeriod(),candidateObject.getUsExperience(),
                        candidateObject.getUsExperiencePeriod(),candidateObject.getAddress(),candidateObject.getImmigrationStatus(),candidateObject.getAddress2(),candidateObject.getCity(),
                        candidateObject.getState(),candidateObject.getCountry(),candidateObject.getCurrentEmployer(),candidateObject.getStatus(),candidateObject.getBillRate(),
                        candidateObject.getBillRateCurrency(),candidateObject.getPayRate(),candidateObject.getPayRateCurrency(),candidateObject.getNote(),candidateObject.getAllReadyAdded(),candidateObject.getEvaluatedByTS(),candidateObject.getFinalVerdict(),
                        candidateObject.getLinkCount(),candidateObject.getAddedBy(),candidateObject.getIsShortListed(),candidateObject.getScreenedStatus(),candidateObject.getEnableDisableStatus(),
                        candidateObject.getComment(),candidateObject.getInterviewStatus(),candidateObject.getScore(),candidateObject.getPositionId(),candidateObject.getPositionName());
                return new ResponseEntity<Object>(candidates, HttpStatus.OK);
            }
        }catch (SQLException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }




    @ApiOperation(value = "Enable/Disable ", httpMethod="POST"
            , notes = "Enable/Disable Candidate")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Enable/Disable Candidate "),
            @ApiResponse(code = 500, message = "Internal Server Error")})

    @RequestMapping(value = CandidateRestURIConstants.CANDIDATE_ENABLE_DISABLE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> enableDisableCandidate(@RequestBody String candidateId) {

        return new ResponseEntity<Object>(CandidateEnums.CANDIDATE_ENABLED.ResponseMsg(), HttpStatus.OK);
    }



    @ApiOperation(value = "Approved ", httpMethod="POST"
            , notes = "Approved Candidate")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Approved Candidate "),
            @ApiResponse(code = 500, message = "Internal Server Error")})

    @RequestMapping(value = CandidateRestURIConstants.APPROVED_CANDIDATE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> fetchApprovedCandidate(@RequestBody String PositionId) {

        Candidates candidates = new Candidates();
        candidates.setFirstName("Approved");
        candidates.setLastName("Candidate");
        candidates.setCellPhoneNumber("999999999");
        candidates.setPrimarySkill("Java");
        candidates.setSecondarySkill("AngularJs");
        candidates.setNote("MyNote");
        candidates.setBillRate("24$");
        candidates.setPayRate("12$");
        candidates.setImmigrationStatus("L1");
        candidates.setAddedBy("Moin");
        candidates.setAddress("BTM");
        candidates.setCreatedBy("REcruiter");
        candidates.setCreatedDate(new Date());
        candidates.setAddress2("Layout");
        candidates.setCity("Bengaluru");
        candidates.setState("Karnataka");
        candidates.setCountry("India");
        return new ResponseEntity<Object>(candidates, HttpStatus.OK);

    }

    @ApiOperation(value = "Approved ", httpMethod="POST"
            , notes = "Approved Candidate")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Screened Candidate "),
            @ApiResponse(code = 500, message = "Internal Server Error")})

    @RequestMapping(value = CandidateRestURIConstants.SCREENED_CANDIDATE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> fetchScreenedCandidate(@RequestBody String PositionId) {

        Candidates candidates = new Candidates();
        candidates.setFirstName("Screened");
        candidates.setLastName("Candidate");
        candidates.setCellPhoneNumber("999999999");
        candidates.setPrimarySkill("Java");
        candidates.setSecondarySkill("AngularJs");
        candidates.setNote("MyNote");
        candidates.setBillRate("24$");
        candidates.setPayRate("12$");
        candidates.setImmigrationStatus("L1");
        candidates.setAddedBy("Moin");
        candidates.setAddress("BTM");
        candidates.setCreatedBy("REcruiter");
        candidates.setCreatedDate(new Date());
        candidates.setAddress2("Layout");
        candidates.setCity("Bengaluru");
        candidates.setState("Karnataka");
        candidates.setCountry("India");
        return new ResponseEntity<Object>(candidates, HttpStatus.OK);

    }

    @ApiOperation(value = "Unscreened ", httpMethod="POST"
            , notes = "Unscreened Candidate")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Unscreened Candidate "),
            @ApiResponse(code = 500, message = "Internal Server Error")})

    @RequestMapping(value = CandidateRestURIConstants.UNSCREENED_CANDIDATE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> fetchUnscreenedCandidate(@RequestBody String PositionId) {

        Candidates candidates = new Candidates();
        candidates.setFirstName("UnScreened");
        candidates.setLastName("Candidate");
        candidates.setCellPhoneNumber("999999999");
        candidates.setPrimarySkill("Java");
        candidates.setSecondarySkill("AngularJs");
        candidates.setNote("MyNote");
        candidates.setBillRate("24$");
        candidates.setPayRate("12$");
        candidates.setImmigrationStatus("L1");
        candidates.setAddedBy("Moin");
        candidates.setAddress("BTM");
        candidates.setCreatedBy("REcruiter");
        candidates.setCreatedDate(new Date());
        candidates.setAddress2("Layout");
        candidates.setCity("Bengaluru");
        candidates.setState("Karnataka");
        candidates.setCountry("India");
        return new ResponseEntity<Object>(candidates, HttpStatus.OK);

    }



    @ApiOperation(value = "Add/View Notes", httpMethod="POST"
            , notes = "Add/View Notes")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Add/View Notes"),
            @ApiResponse(code = 500, message = "Internal Server Error")})

    @RequestMapping(value = CandidateRestURIConstants.ADD_VIEW_NOTES, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> addViewNotes(@RequestBody String candidateId,@RequestBody String userid) {

        return new ResponseEntity<Object>(CandidateEnums.NOTES_UPDATED, HttpStatus.OK);

    }

    @ApiOperation(value = "Video Comparison ", httpMethod="POST"
            , notes = "Video Comparison")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Video Comparison"),
            @ApiResponse(code = 500, message = "Internal Server Error")})

    @RequestMapping(value = CandidateRestURIConstants.CANDIDATE_VIDEO_COMPARISON, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> compareCandidateVideos(@RequestBody String candidateId,@RequestBody String userid) {

        return new ResponseEntity<Object>(CandidateEnums.VIDEO_COMPARED, HttpStatus.OK);

    }

    @ApiOperation(value = "Regenerate Link", httpMethod="POST"
            , notes = "Regenerate Link")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Regenerate Link"),
            @ApiResponse(code = 500, message = "Internal Server Error")})

    @RequestMapping(value = CandidateRestURIConstants.REGENERATE_LINK, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> regenerateLink(@RequestBody String candidateId,@RequestBody String userid) {

        return new ResponseEntity<Object>(CandidateEnums.LINKS_GENERATED, HttpStatus.OK);

    }


}
