package com.accion.recruitment.dao;

import com.accion.recruitment.jpa.entities.CandidateFinalResult;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 27/01/17 00:11 AM#$
 */
public interface CandidateResponseServiceDAO<E> {

    /**
     * findCandidateFinalResultByPositionIdAndCandidateId() provide the specification for retrieval of the
     * CandidateFinalResult object from the database based on the search query.
     *
     * @param positionId accept the java.lang.int type of userId
     *                  which is having positionId ID value
     *
     * @param candidateId accept the java.lang.int type of userId
     *                  which is having candidateId ID value
     *
     *
     * @return instance of CandidateFinalResult having persisting state otherwise
     *         it return null.
     */

    public CandidateFinalResult findCandidateFinalResultByPositionIdAndCandidateId(Integer positionId,Integer candidateId);
}
