package com.accion.recruitment.common.constants;

/**
 * Created by AL1028 on 1/19/17.
 */
public class RequirementURIConstants {

    public static final String REQUIREMENT_CREATE = "/hot/requirement/create";
    public static final String GET_ALL_REQUIREMENT = "/hot/requirements";
    public static final String REQUIREMENT_UPDATE = "/hot/requirement/update";
    public static final String GET_BY_ID = "/hot/requirement/{id}";
    public static final String REQUIREMENT_CANDIDATES = "/hot/requirement/candidates/{id}";
    public static final String ADD_TECH_SCREENER ="/hot/requirement/techscreener/{id}";
    public static final String ADD_RECRUITER = "/hot/requirement/recruiter/{id}";




    public static final String ADD_NO_MORE_CANDIDATE = "/hot/requirement/nomorecanidate/{id}";
    public static final String OPEN_CLOSE_REQUIREMENT = "/hot/requirement/openclose/{status}/{id}";
    public static final String ADD_CANDIDATE_TO_REQT = "/hot/requirement/addcandidate/{id}/{id}";
    public static final String ADD_CANDIDATE_FROM_DB = "/hot/requirement/addcandidatefromdb";
    public static final String ADD_QUESTION_FROM_DB = "/hot/requirement/addquestionfromdb";

    public static final String SHORTLIST_CANDIDATE = "/hot/requirement/shortlist";
    public static final String SUBMIT_TO_CLIENT = "/hot/requirement/submittoclient";
}
