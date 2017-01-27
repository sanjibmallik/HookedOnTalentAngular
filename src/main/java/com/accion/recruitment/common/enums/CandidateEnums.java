package com.accion.recruitment.common.enums;

/**
 * Created by MoinGodil on 1/20/17.
 */
public enum CandidateEnums {

    CANDIDATE_CREATED("Candidate Created Successfully"),
    CANDIDATE_NOT_CREATED("Problem with saving the Candidate. Try again."),
    CANDIDATE_PROFILE_EXCEPTION("Problem with uploading the profile. Try another"),

    CANDIDATE_NOT_UPDATED("Problem with updating the Candidate. Try again."),
    CANDIDATE_UPDATED("Candidate Updated Successfully"),


    RESUME_DOWNLOAD("Resume Downloaded"),
    CANDIDATE_ENABLED("Candidate Enabled"),
    VIDEO_COMPARED("Video Compared"),
    LINKS_GENERATED("Link Generated Successfully"),
    NOTES_UPDATED("Notes Updated");

    private String responseMsg;

    CandidateEnums(String responseMsg){
        this.responseMsg=responseMsg;
    }

    public String ResponseMsg() {
        return responseMsg;
    }
}
