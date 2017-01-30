package com.accion.recruitment.common.constants;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 12/29/16 00:11 AM#$
 */

public class UserRestURIConstants {

    public static final String CREATE_USER = "/hot/user/create";
    public static final String UPDATE_USER = "/hot/user/update";
    public static final String GET_ALL_USER = "/hot/users";
    public static final String CHANGE_STATUS = "/hot/user/status/{id}/{status}";
    public static final String RESET_PASSWORD = "/hot/user/reset/{id}";
    public static final String GET_BY_ID = "/hot/user/{id}";
    public static final String GET_CONTACT_NUMBER = "/hot/user/contactNumber/{contactNumber}";
    public static final String GET_USER_NAME = "/hot/user/userName/{userName}";
    public static final String GET_EMAIL_ID = "/hot/user/emailId/{emailId:..+}";
    public static final String CHANGE_PASSWORD = "/hot/user/{currentPassword}/{newPassword}";

    public static final String GET_BY_PROPERTY = "/hot/user/{propertyName}/{propertyValue:..+}";


}
