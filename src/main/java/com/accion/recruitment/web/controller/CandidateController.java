package com.accion.recruitment.web.controller;

import com.accion.recruitment.common.constants.CandidateConstants;
import com.accion.recruitment.common.constants.UserConstants;
import com.accion.recruitment.common.enums.CandidateEnums;
import com.accion.recruitment.common.enums.RequirementEnums;
import com.accion.recruitment.jpa.entities.Candidates;
import com.accion.recruitment.jpa.entities.User;
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

import java.security.Principal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by MoinGodil on 1/20/17.
 */

@Controller
public class CandidateController {

    @Autowired
    CandidateService candidateService;

    @Autowired
    private UserService userService;

    private final SimpleDateFormat sdf = new SimpleDateFormat(UserConstants.DATE_FORMAT);

    @ApiOperation(value = "Create Candidate ", httpMethod="POST"
            , notes = "Creates Candidate")
    @ApiResponses(value = {@ApiResponse(code = 201, message = " Candidate Created Successfully "),
            @ApiResponse(code = 201, message = " Successful Respond Send "),
            @ApiResponse(code = 500, message = "Internal Server Error")})

    @RequestMapping(value = CandidateConstants.CANDIDATE_CREATE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> createRequirement(@RequestBody Candidates candidates,
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
                return new ResponseEntity<Object>(RequirementEnums.REQUIREMENT_NOT_CREATED.ResponseMsg(), HttpStatus.OK);
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

    @ApiOperation(value = "Get the Candidate Details By Id ", httpMethod="POST"
            , notes = "Candidate Details")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Candidate Details"),
            @ApiResponse(code = 500, message = "Internal Server Error")})

    @RequestMapping(value = CandidateConstants.GET_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> getCandidatesById(@PathVariable("id") final int candidateId) {

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



    @ApiOperation(value = "Enable/Disable ", httpMethod="POST"
            , notes = "Enable/Disable Candidate")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Enable/Disable Candidate "),
            @ApiResponse(code = 500, message = "Internal Server Error")})

    @RequestMapping(value = CandidateConstants.CANDIDATE_ENABLE_DISABLE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> enableDisableCandidate(@RequestBody String candidateId) {

        return new ResponseEntity<Object>(CandidateEnums.CANDIDATE_ENABLED.ResponseMsg(), HttpStatus.OK);
    }


    @ApiOperation(value = "Search ", httpMethod="POST"
            , notes = "Search Candidate")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Search Candidate "),
            @ApiResponse(code = 500, message = "Internal Server Error")})

    @RequestMapping(value = CandidateConstants.CANDIDATE_SEARCH, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> searchCandidate(@RequestBody String  searchText) {

        Candidates candidates = new Candidates();
        candidates.setFirstName("Search");
        candidates.setLastName("Done");
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
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Approved Candidate "),
            @ApiResponse(code = 500, message = "Internal Server Error")})

    @RequestMapping(value = CandidateConstants.APPROVED_CANDIDATE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
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

    @RequestMapping(value = CandidateConstants.SCREENED_CANDIDATE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
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

    @RequestMapping(value = CandidateConstants.UNSCREENED_CANDIDATE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
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

    @ApiOperation(value = "Download Resume ", httpMethod="POST"
            , notes = "Download Resume")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Download Resume "),
            @ApiResponse(code = 500, message = "Internal Server Error")})

    @RequestMapping(value = CandidateConstants.DOWNLOAD_RESUME, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> getCandidateResume(@RequestBody String CandidateId) {

        return new ResponseEntity<Object>(CandidateEnums.RESUME_DOWNLOAD, HttpStatus.OK);

    }

    @ApiOperation(value = "Add/View Notes", httpMethod="POST"
            , notes = "Add/View Notes")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Add/View Notes"),
            @ApiResponse(code = 500, message = "Internal Server Error")})

    @RequestMapping(value = CandidateConstants.ADD_VIEW_NOTES, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> addViewNotes(@RequestBody String candidateId,@RequestBody String userid) {

        return new ResponseEntity<Object>(CandidateEnums.NOTES_UPDATED, HttpStatus.OK);

    }

    @ApiOperation(value = "Video Comparison ", httpMethod="POST"
            , notes = "Video Comparison")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Video Comparison"),
            @ApiResponse(code = 500, message = "Internal Server Error")})

    @RequestMapping(value = CandidateConstants.CANDIDATE_VIDEO_COMPARISON, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> compareCandidateVideos(@RequestBody String candidateId,@RequestBody String userid) {

        return new ResponseEntity<Object>(CandidateEnums.VIDEO_COMPARED, HttpStatus.OK);

    }

    @ApiOperation(value = "Regenerate Link", httpMethod="POST"
            , notes = "Regenerate Link")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Regenerate Link"),
            @ApiResponse(code = 500, message = "Internal Server Error")})

    @RequestMapping(value = CandidateConstants.REGENERATE_LINK, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> regenerateLink(@RequestBody String candidateId,@RequestBody String userid) {

        return new ResponseEntity<Object>(CandidateEnums.LINKS_GENERATED, HttpStatus.OK);

    }
}
