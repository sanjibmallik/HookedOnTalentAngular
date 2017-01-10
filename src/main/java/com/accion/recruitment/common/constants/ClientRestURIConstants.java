package com.accion.recruitment.common.constants;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 09/01/17 00:11 AM#$
 */
public class ClientRestURIConstants {

    public static final String GET_BY_ID = "/hot/client/{id}";
    public static final String GET_CLIENT_NAME = "/hot/client/clientName/{clientName}";
    public static final String GET_FEDERAL_ID = "/hot/client/federalId/{federalId}";

    public static final String GET_BY_PROPERTY = "/hot/client/{propertyName}/{propertyValue:..+}";
}
