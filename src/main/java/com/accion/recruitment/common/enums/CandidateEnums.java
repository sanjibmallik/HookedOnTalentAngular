package com.accion.recruitment.common.enums;

/**
 * Created by MoinGodil on 1/20/17.
 */
public enum CandidateEnums {

    CANDIDATE_CREATED("Candidate Created"),
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
