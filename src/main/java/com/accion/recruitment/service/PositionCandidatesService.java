package com.accion.recruitment.service;

import com.accion.recruitment.jpa.entities.Candidates;
import com.accion.recruitment.jpa.entities.PositionCandidates;
import com.accion.recruitment.jpa.entities.User;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 31/01/17 00:11 AM#$
 */
public interface PositionCandidatesService {

    /**
     * savePositionCandidates() provide the specification for persisting
     * the PositionCandidates object in the database.
     *
     * @param positionCandidates accept the instance of the PositionCandidates class
     *             containing data.
     * @return status of the persisting operation.
     */

    public Boolean savePositionCandidates(final PositionCandidates positionCandidates) throws SQLException;

    /**
     * findPositionCandidatesByPositionId() provide the specification for retrieval of the
     * PositionCandidates object from the database based on the position ID.
     *
     * @param requirementId accept the java.lang.int type of requirementId
     *                  which is having position ID value
     *
     * @return List of Candidates having persisting state otherwise
     *         it return null.
     */
    public List<Candidates> findPositionCandidatesByRequirementId(final int requirementId) throws SQLException;
}
