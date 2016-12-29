package com.accion.recruitment.common.enums;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 12/29/16 00:11 AM#$
 */
public enum HttpStatusEnums {

    /*User Controller Status*/

    USER_NAME_EXIST("That username is exist in database. Try another."),
    EMAIlID_EXIST("That emailId is exist in database. Try another."),
    CONTACT_NUMBER_EXIST("That contact number is exist in database. Try another."),
    USER_IMAGE_EXCEPTION("Problem with uploading the image. Try another"),
    USER_PROFILE_EXCEPTION("Problem with uploading the profile. Try another"),
    USER_SKILLS_EXCEPTION("Enter the screener details correctly."),


    DATABASE_EXCEPTION("Database connectivity issue. Try again."),
    RECORD_NOT_SAVED("Problem with saving the record. Try again."),
    RECORD_SAVED("Record saved successfully.");

    private String responseMsg;

    HttpStatusEnums(String responseMsg){
        this.responseMsg=responseMsg;
    }

    public String ResponseMsg() {
        return responseMsg;
    }
}
