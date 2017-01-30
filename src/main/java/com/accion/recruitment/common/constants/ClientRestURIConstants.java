package com.accion.recruitment.common.constants;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 09/01/17 00:11 AM#$
 */
public class ClientRestURIConstants {

    public static final String CREATE_CLIENT = "/hot/client/create";
    public static final String UPDATE_CLIENT = "/hot/client/update";
    public static final String GET_BY_ID = "/hot/client/{id}";
    public static final String GET_ALL_CLIENT = "/hot/clients";
    public static final String CLIENT_ACTIVATE = "/hot/client/activate/{id}";
    public static final String GET_CLIENT_NAME = "/hot/client/clientName/{clientName}";
    public static final String GET_FEDERAL_ID = "/hot/client/federalId/{federalId}";
    public static final String GET_ENGAGEMENT = "/hot/engagement";
    public static final String GET_INDUSTRY = "/hot/industry";

    public static final String GET_BY_PROPERTY = "/hot/client/{propertyName}/{propertyValue:..+}";
}
