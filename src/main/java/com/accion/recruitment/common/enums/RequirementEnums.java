package com.accion.recruitment.common.enums;

/**
 * Created by MoinGodil on 1/19/17.
 */
public enum RequirementEnums {

    REQUIREMENT_CREATED("Requirement Created Successfully"),

    DURATION("Enter the duration correctly"),
    BILL_RATE("Enter the Bill Rate correctly"),
    PAY_RATE("Enter the Pay Rate correctly"),

    CLIENT_NOT_EXIST("The client name you selected is not exist"),

    REQUIREMENT_NOT_CREATED("Problem with saving the Requirement. Try again."),

    REQUIREMENT_UPDATED("Requirement Updated Successfully."),
    REQUIREMENT_NOT_UPDATED("Problem with Updating the Requirement. Try again.");

    private String responseMsg;

    RequirementEnums(String responseMsg){
        this.responseMsg=responseMsg;
    }

    public String ResponseMsg() {
        return responseMsg;
    }
}
