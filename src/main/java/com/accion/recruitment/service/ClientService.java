package com.accion.recruitment.service;

import com.accion.recruitment.jpa.entities.*;


import java.sql.SQLException;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 10/01/17 00:11 AM#$
 */

public interface ClientService {

    /**
     * findClientDetailsByPropertyName() provide the specification for retrieval of the
     * ClientDetails object from the database based on the Property  name.
     *
     * @param propName accept the java.lang.String type of propName
     *                  which is having column name value .
     * @param propValue accept the java.lang.String type of propValue
     *                  which is having column value .
     *
     * @return instance of ClientDetails having persisting state otherwise
     *         it return null.
     */
    public ClientDetails findClientDetailsByPropertyName(final String propName,final Object propValue) throws SQLException;

    /**
     * saveClientDetails() provide the specification for persisting
     * the User object in the database.
     *
     * @param clientDetails accept the instance of the ClientDetails class
     *             containing data.
     * @return status of the persisting operation.
     */

    public Boolean saveClientDetails(final ClientDetails clientDetails) throws SQLException;

    /**
     * saveClientContacts() provide the specification for persisting
     * the User object in the database.
     *
     * @param clientContacts accept the instance of the ClientContacts class
     *             containing data.
     * @return status of the persisting operation.
     */

    public Boolean saveClientContacts(final ClientContacts clientContacts) throws SQLException;


    /**
     * saveIndustry() provide the specification for persisting
     * the User object in the database.
     *
     * @param industry accept the instance of the Industry class
     *             containing data.
     * @return status of the persisting operation.
     */

    public Boolean saveIndustry(final Industry industry) throws SQLException;

    /**
     * saveEngagementModel() provide the specification for persisting
     * the User object in the database.
     *
     * @param engagementModel accept the instance of the EngagementModel class
     *             containing data.
     * @return status of the persisting operation.
     */

    public Boolean saveEngagementModel(final EngagementModel engagementModel) throws SQLException;

}
