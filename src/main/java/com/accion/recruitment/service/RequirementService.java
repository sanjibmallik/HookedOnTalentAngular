package com.accion.recruitment.service;

import com.accion.recruitment.jpa.entities.ClientDetails;
import com.accion.recruitment.jpa.entities.Positions;
import com.accion.recruitment.jpa.entities.User;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 23/01/17 00:11 AM#$
 */

public interface RequirementService {

    /**
     * saveRequirements() provide the specification for persisting
     * the Position object in the database.
     *
     * @param requirement accept the instance of the Positions class
     *             containing data.
     * @return status of the persisting operation.
     */

    public Boolean saveRequirements(final Positions requirement) throws SQLException;

    /**
     * findAllRequirement() provide the specification for getting all the
     * Positions objects from the database.
     *
     * @return instance of the java.util.List containing the
     *         Positions object of the persisting state.
     */

    public List<Positions> findAllRequirement() throws SQLException;

    /**
     * findRequirementById() provide the specification for retrieval of the
     * Positions object from the database based on the positionId.
     *
     * @param positionId accept the java.lang.int type of positionId
     *                  which is having position ID value
     *
     * @return instance of Positions having persisting state otherwise
     *         it return null.
     */
    public Positions findRequirementById(final int positionId) throws SQLException;

    /**
     * deleteUserByQuery() delete the record from the DB based
     * on the Query.
     *
     * @param query accept the java.lang.String type
     *                  which is having Database Query
     *
     * @return instance of Record having persisting state otherwise
     *         it return null.
     */
    public Boolean deleteRecordByQuery(String query)throws SQLException;

}
