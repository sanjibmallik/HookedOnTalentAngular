package com.accion.recruitment.common.enums;

/**
 * Created by MoinGodil on 1/19/17.
 */
public enum ReqtMgtEnums {

    REQT_CREATED("Requirement Created");

    private String responseMsg;

    ReqtMgtEnums(String responseMsg){
        this.responseMsg=responseMsg;
    }

    public String ResponseMsg() {
        return responseMsg;
    }
}
