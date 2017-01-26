package com.accion.recruitment.common.enums;

/**
 * Created by MoinGodil on 1/25/17.
 */
public enum QuestionEnums {

    QUESTION_CREATED("Question Created Successfully"),
    QUESTION_NOT_CREATED("Problem with saving the Question. Try again."),
    QUESTION_UPDATED("Question Updated Successfully"),
    QUESTION_APPROVED("Question Approved Successfully"),
    QUESTION_DELETED("Question Deleted Successfully");

    private String responseMsg;

    QuestionEnums(String responseMsg){
        this.responseMsg=responseMsg;
    }

    public String ResponseMsg() {
        return responseMsg;
    }
}
