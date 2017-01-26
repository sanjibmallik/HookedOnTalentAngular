package com.accion.recruitment.dao;

import com.accion.recruitment.jpa.entities.Candidates;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 25/01/17 00:11 AM#$
 */
public interface CandidateServiceDAO<E> {

    /**
     * saveCandidates() provide the specification for persisting
     * the Candidates object in the database.
     *
     * @param candidates accept the instance of the Candidates class
     *             containing data.
     * @return status of the persisting operation.
     */

    public Boolean saveCandidates(final Candidates candidates);

    /**
     * findAllCandidates() provide the specification for getting all the
     * Candidates objects from the database.
     *
     * @return instance of the java.util.List containing the
     *         Candidates object of the persisting state.
     */

    public List<E> findAllCandidates() ;

    /**
     * findCandidatesByPropertyName() provide the specification for retrieval of the
     * Candidates object from the database based on the Property name.
     *
     * @param propName accept the java.lang.String type of propName
     *                  which is having column name value .
     * @param propValue accept the java.lang.String type of propValue
     *                  which is having column value .
     *
     * @return instance of Candidates having persisting state otherwise
     *         it return null.
     */
    public Candidates findCandidatesByPropertyName(final String propName,final Object propValue) ;

    /**
     * findCandidatesById() provide the specification for retrieval of the
     * Candidates object from the database based on the User name.
     *
     * @param candidateId accept the java.lang.int type of userId
     *                  which is having user ID value
     *
     * @return instance of User having persisting state otherwise
     *         it return null.
     */
    public Candidates findCandidatesById(final int candidateId);
}
