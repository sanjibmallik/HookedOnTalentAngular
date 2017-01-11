package com.accion.recruitment.common.enums;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 11/01/17 00:11 AM#$
 */
public enum ClientHttpStatusEnums {

    CLIENT_NAME_EXIST("That ClientName is exist in database. Try another."),
    CONTACT_NUMBER_EXIST("That Contact Number is exist in database. Try another."),
    FEDERAL_ID_EXIST("That FederalId is exist in database. Try another."),


    CLIENT_NOT_SAVED("Problem with saving the Client. Try again."),
    CLIENT_SAVED_EMAIL_SEND("Client saved and Email Send Successfully."),
    CLIENT_SAVED_EMAIL_NOT_SEND("Client saved and Email Not Send ."),

    DATABASE_EXCEPTION("Database connectivity issue. Try again.");

    private String responseMsg;

    ClientHttpStatusEnums(String responseMsg){
        this.responseMsg=responseMsg;
    }

    public String ResponseMsg() {
        return responseMsg;
    }


}
