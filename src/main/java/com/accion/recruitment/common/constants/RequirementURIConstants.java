package com.accion.recruitment.common.constants;

/**
 * Created by AL1028 on 1/19/17.
 */
public class RequirementURIConstants {

    public static final String REQUIREMENT_CREATE = "/hot/requirement/create";
    public static final String GET_ALL_REQUIREMENT = "/hot/requirements";
    public static final String REQUIREMENT_UPDATE = "/hot/requirement/update";
    public static final String GET_BY_ID = "/hot/requirement/{id}";
    public static final String OPEN_CLOSE_REQUIREMENT = "/hot/requirement/openclose/{status}/{id}";


    public static final String REQUIREMENT_CANDIDATES = "/hot/requirement/candidates/{id}";
    public static final String ADD_CANDIDATES_TO_REQ = "/hot/requirement/addcandidates/{id}/{candidateIds}";
    public static final String REGENERATE_LINK = "/hot/requirement/regenerate/{id}/{candidateId}";

    public static final String ADD_TECH_SCREENER ="/hot/requirement/techscreener/{id}/{userIds}";
    public static final String DISPLAY_TECH_SCREENER ="/hot/requirement/displaytechscreener/{id}";
    public static final String DELETE_TECH_SCREENER ="/hot/requirement/deletetechscreener/{id}/{userId}";

    public static final String ADD_RECRUITER = "/hot/requirement/recruiter/{id}/{userIds}";
    public static final String DISPLAY_RECRUITER ="/hot/requirement/displayrecruiter/{id}";
    public static final String DELETE_RECRUITER ="/hot/requirement/deleterecruiter/{id}/{userId}";

    public static final String CANDIDATE_VIDEO_COMPARISON = "/hot/requirement/compare/{id}";

    public static final String CANDIDATE_RESPONSE = "/hot/requirement/candidate/response/{id}/{candidateId}";
    public static final String CANDIDATE_SUBJECTIVE = "/hot/requirement/candidate/response/subjective";


    public static final String ADD_NO_MORE_CANDIDATE = "/hot/requirement/nomorecanidate/{id}";

    public static final String ADD_CANDIDATE_TO_REQT = "/hot/requirement/addcandidate/{id}/{id}";
    public static final String ADD_QUESTION_FROM_DB = "/hot/requirement/addquestionfromdb";

    public static final String SHORTLIST_CANDIDATE = "/hot/requirement/shortlist";
    public static final String SUBMIT_TO_CLIENT = "/hot/requirement/submittoclient";
}
