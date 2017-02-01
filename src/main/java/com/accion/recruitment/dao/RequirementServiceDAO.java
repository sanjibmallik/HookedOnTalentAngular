package com.accion.recruitment.dao;

import com.accion.recruitment.jpa.entities.Positions;

import java.util.List;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 23/01/17 00:11 AM#$
 */
public interface RequirementServiceDAO<E> {

    /**
     * saveRequirements() provide the specification for persisting
     * the Position object in the database.
     *
     * @param requirement accept the instance of the Positions class
     *             containing data.
     * @return status of the persisting operation.
     */

    public Boolean saveRequirements(final Positions requirement);

    /**
     * findAllRequirement() provide the specification for getting all the
     * Positions objects from the database.
     *
     * @return instance of the java.util.List containing the
     *         Positions object of the persisting state.
     */

    public List<E> findAllRequirement();

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
    public Positions findRequirementById(final int positionId);

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
    public Boolean deleteRecordByQuery(String query);

}
