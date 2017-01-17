package com.accion.recruitment.dao;

import com.accion.recruitment.jpa.entities.ClientContacts;
import com.accion.recruitment.jpa.entities.ClientDetails;
import com.accion.recruitment.jpa.entities.EngagementModel;
import com.accion.recruitment.jpa.entities.Industry;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 10/01/17 00:11 AM#$
 */
public interface ClientServiceDAO<E> {

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
    public ClientDetails findClientDetailsByPropertyName(final String propName,final Object propValue) ;

    /**
     * saveClientDetails() provide the specification for persisting
     * the User object in the database.
     *
     * @param clientDetails accept the instance of the ClientDetails class
     *             containing data.
     * @return status of the persisting operation.
     */

    public Boolean saveClientDetails(final ClientDetails clientDetails) ;

    /**
     * saveClientContacts() provide the specification for persisting
     * the User object in the database.
     *
     * @param clientContacts accept the instance of the ClientContacts class
     *             containing data.
     * @return status of the persisting operation.
     */

    public Boolean saveClientContacts(final ClientContacts clientContacts);

    /**
     * saveObject() provide the specification for persisting
     * the User object in the database.
     *
     * @param o accept the instance of the Object class
     *             containing data.
     * @return status of the persisting operation.
     */

    public Boolean saveObject(final Object o) throws SQLException;

    /**
     * findAllEngagementModel() provide the specification
     * for getting all EngagementModel.

     * the instance of the java.util.List containing
     *         the EngagementModel Names.
     */

    public List<EngagementModel> findAllEngagementModel() ;

    /**
     * findAllIndustry() provide the specification
     * for getting all EngagementModel.

     * the instance of the java.util.List containing
     *         the EngagementModel Names.
     */

    public List<Industry> findAllIndustry() ;

    /**
     * findAllClients() provide the specification for getting all the
     * Client objects from the database.
     *
     * @return instance of the java.util.List containing the
     *         Client object of the persisting state.
     */

    public List<E> findAllClients();
}
