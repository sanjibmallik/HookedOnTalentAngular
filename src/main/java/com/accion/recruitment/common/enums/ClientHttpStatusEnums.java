package com.accion.recruitment.common.enums;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 11/01/17 00:11 AM#$
 */
public enum ClientHttpStatusEnums {

    CLIENT_NAME_EXIST("That ClientName is exist in database. Try another.");


    private String responseMsg;

    ClientHttpStatusEnums(String responseMsg){
        this.responseMsg=responseMsg;
    }

    public String ResponseMsg() {
        return responseMsg;
    }


}
