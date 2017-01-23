package com.accion.recruitment.common.enums;

/**
 * Created by MoinGodil on 1/20/17.
 */
public enum CandidateEnums {

    CANDIDATE_CREATED("Candidate Created");

    private String responseMsg;

    CandidateEnums(String responseMsg){
        this.responseMsg=responseMsg;
    }

    public String ResponseMsg() {
        return responseMsg;
    }
}
