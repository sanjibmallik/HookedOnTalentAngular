package com.accion.recruitment.service;

import com.accion.recruitment.jpa.entities.CandidatePastHistory;
import com.accion.recruitment.jpa.entities.PositionCandidates;

import java.sql.SQLException;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 31/01/17 00:11 AM#$
 */
public interface CandidatePastHistoryService {

    /**
     * saveCandidatePastHistory() provide the specification for persisting
     * the CandidatePastHistory object in the database.
     *
     * @param candidatePastHistory accept the instance of the CandidatePastHistory class
     *             containing data.
     * @return status of the persisting operation.
     */

    public Boolean saveCandidatePastHistory(final CandidatePastHistory candidatePastHistory) throws SQLException;

    /**
     * deleteCandidatePastHistoryResponseByQuery() delete the record from the DB based
     * on the Query.
     *
     * @param positionId accept the java.lang.int type of userId
     *                  which is having positionId ID value
     *
     * @param candidateId accept the java.lang.int type of userId
     *                  which is having candidateId ID value
     *
     *
     * @return instance of Record having persisting state otherwise
     *         it return null.
     */

    public Boolean deleteCandidatePastHistoryResponseByQuery(Integer positionId,Integer candidateId) throws SQLException;

}
