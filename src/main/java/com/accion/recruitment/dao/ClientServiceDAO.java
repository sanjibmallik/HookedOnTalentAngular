package com.accion.recruitment.dao;

import com.accion.recruitment.jpa.entities.ClientDetails;

import java.sql.SQLException;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 10/01/17 00:11 AM#$
 */
public interface ClientServiceDAO {

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
}
