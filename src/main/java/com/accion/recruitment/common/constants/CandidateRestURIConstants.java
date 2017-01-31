package com.accion.recruitment.common.constants;

/**
 * Created by MoinGodil on 1/20/17.
 */
public class CandidateRestURIConstants {

    public static final String CREATE_CANDIDATE = "/hot/candidate/create";
    public static final String GET_BY_ID = "/hot/candidate/{id}";
    public static final String GET_BY_EMAIL = "/hot/candidate/{emailId}";
    public static final String GET_BY_CONTACT_NUMBER = "/hot/candidate/{contactNumber}";
    public static final String UPDATE_CANDIDATE = "/hot/candidate/update";
    public static final String GET_ALL_CANDIDATE = "/hot/candidates";
    public static final String SEARCH_CANDIDATE = "/hot/candidate/search";
    public static final String CANDIDATE_HISTORY = "/hot/candidate/history/{id}";
    public static final String DOWNLOAD_RESUME = "/hot/candidate/resume/{id}";

    public static final String SCREENED_CANDIDATE = "/hot/candidate/screened";
    public static final String UNSCREENED_CANDIDATE = "/hot/candidate/unscreened";
    public static final String APPROVED_CANDIDATE = "/hot/candidate/approved";
    public static final String CANDIDATE_VIDEO_COMPARISON = "/hot/candidate/videocompare";
    public static final String ADD_VIEW_NOTES = "/hot/candidate/notes";


    public static final String CANDIDATE_EDIT = "/hot/candidate/edit";
    public static final String CANDIDATE_DETAILS = "/hot/candidate/details";
    public static final String CANDIDATE_ENABLE_DISABLE = "/hot/candidate/updatestatus";
}
