package com.accion.recruitment.dao;

import com.accion.recruitment.jpa.entities.CandidatePastHistory;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 31/01/17 00:11 AM#$
 */
public interface CandidatePastHistoryServiceDAO<E> {

    /**
     * saveCandidatePastHistory() provide the specification for persisting
     * the CandidatePastHistory object in the database.
     *
     * @param candidatePastHistory accept the instance of the CandidatePastHistory class
     *             containing data.
     * @return status of the persisting operation.
     */

    public Boolean saveCandidatePastHistory(final CandidatePastHistory candidatePastHistory);

    /**
     * deleteRecordByQuery() delete the record from the DB based
     * on the Query.
     *
     * @param query accept the java.lang.String type
     *                  which is having Database Query
     *
     * @return instance of Record having persisting state otherwise
     *         it return null.
     */
    public Boolean deleteRecordByQuery(String query) ;
}
