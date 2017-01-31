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
}
