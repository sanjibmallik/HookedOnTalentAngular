package com.accion.recruitment.common.enums;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 12/29/16 00:11 AM#$
 */
public enum UserHttpStatusEnums {

    /*User Controller Status*/

    USER_NAME_EXIST("That username is exist in database. Try another."),
    EMAIlID_EXIST("That emailId is exist in database. Try another."),
    CONTACT_NUMBER_EXIST("That contact number is exist in database. Try another."),
    USER_IMAGE_EXCEPTION("Problem with uploading the image. Try another"),
    USER_PROFILE_EXCEPTION("Problem with uploading the profile. Try another"),
    USER_SKILLS_EXCEPTION("Enter the screener details correctly."),

    /*Login Controller Status*/

    LOGIN_ERROR("Wrong  Credentials"),



    DATABASE_EXCEPTION("Database connectivity issue. Try again."),
    RECORD_NOT_SAVED("Problem with saving the record. Try again."),
    RECORD_SAVED_EMAIL_SEND("Record saved and Email Send Successfully."),
    RECORD_SAVED_EMAIL_NOT_SEND("Record saved and Email Not Send ."),

    USER_ENABLED_EMAIL_SEND("User Enabled  and Email  Send Successfully."),
    USER_ENABLED_EMAIL_NOT_SEND("User Enabled  and Email Not Send."),
    USER_DISABLED_EMAIL_SEND("User Disabled and Email  Send Successfully."),
    USER_DISABLED_EMAIL_NOT_SEND("User Enabled  and Email Not Send."),
    USER_STATUS_NOT_CHANGED("Status Not Changed."),

    PASSWORD_NOT_CHANGED("Password Changed"),
    PASSWORD_CHANGED_EMAIL_SEND("Password Changed and Email  Send Successfully."),
    PASSWORD_CHANGED_EMAIL_NOT_SEND("Password Changed and Email Not Send .");


    private String responseMsg;

    UserHttpStatusEnums(String responseMsg){
        this.responseMsg=responseMsg;
    }

    public String ResponseMsg() {
        return responseMsg;
    }
}
