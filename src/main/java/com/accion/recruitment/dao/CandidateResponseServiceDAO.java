package com.accion.recruitment.dao;

import com.accion.recruitment.jpa.entities.*;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 27/01/17 00:11 AM#$
 */
public interface CandidateResponseServiceDAO<E> {


    /**
     * findCandidateGeneralQuestionResponseByPositionIdAndCandidateId() provide the specification for retrieval of the
     * CandidateGeneralQuestionResponse object from the database based on the search query.
     *
     * @param positionId accept the java.lang.int type of userId
     *                  which is having positionId ID value
     *
     * @param candidateId accept the java.lang.int type of userId
     *                  which is having candidateId ID value
     *
     *
     * @return instance of CandidateGeneralQuestionResponse having persisting state otherwise
     *         it return null.
     */

    public List<CandidateGeneralQuestionResponse>  findCandidateGeneralQuestionResponseByPositionIdAndCandidateId(Integer positionId,Integer candidateId) throws SQLException;

    /**
     * findCandidateTechnicalQuestionResponseByPositionIdAndCandidateId() provide the specification for retrieval of the
     * CandidateTechnicalQuestionResponse object from the database based on the search query.
     *
     * @param positionId accept the java.lang.int type of userId
     *                  which is having positionId ID value
     *
     * @param candidateId accept the java.lang.int type of userId
     *                  which is having candidateId ID value
     *
     *
     * @return instance of CandidateTechnicalQuestionResponse having persisting state otherwise
     *         it return null.
     */

    public List<CandidateTechnicalQuestionResponse>  findCandidateTechnicalQuestionResponseByPositionIdAndCandidateId(Integer positionId,Integer candidateId) throws SQLException;


    /**
     * findCandidateVideoQuestionResponseByPositionIdAndCandidateId() provide the specification for retrieval of the
     * CandidateVideoQuestionResponse object from the database based on the search query.
     *
     * @param positionId accept the java.lang.int type of userId
     *                  which is having positionId ID value
     *
     * @param candidateId accept the java.lang.int type of userId
     *                  which is having candidateId ID value
     *
     *
     * @return instance of CandidateVideoQuestionResponse having persisting state otherwise
     *         it return null.
     */

    public List<CandidateVideoQuestionResponse>  findCandidateVideoQuestionResponseByPositionIdAndCandidateId(Integer positionId,Integer candidateId) throws SQLException;


    /**
     * findCandidateSelfRatingResponseByPositionIdAndCandidateId() provide the specification for retrieval of the
     * CandidateSelfRatingResponse object from the database based on the search query.
     *
     * @param positionId accept the java.lang.int type of userId
     *                  which is having positionId ID value
     *
     * @param candidateId accept the java.lang.int type of userId
     *                  which is having candidateId ID value
     *
     *
     * @return instance of CandidateSelfRatingResponse having persisting state otherwise
     *         it return null.
     */

    public List<CandidateSelfRatingResponse>  findCandidateSelfRatingResponseByPositionIdAndCandidateId(Integer positionId,Integer candidateId) throws SQLException;

    /**
     * findCandidateUserNotesByPositionAndCandidateId() provide the specification for retrieval of the
     * CandidateUserNotes object from the database based on the search query.
     *
     * @param positionId accept the java.lang.int type of userId
     *                  which is having positionId ID value
     *
     * @param candidateId accept the java.lang.int type of userId
     *                  which is having candidateId ID value
     *
     *
     * @return instance of CandidateUserNotes having persisting state otherwise
     *         it return null.
     */

    public List<CandidateUserNotes>  findCandidateUserNotesByPositionAndCandidateId(Integer positionId,Integer candidateId) throws SQLException;

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


    /**
     * getRecordByQuery() delete the record from the DB based
     * on the Query.
     *
     * @param query accept the java.lang.String type
     *                  which is having Database Query
     *
     * @return instance of Record having persisting state otherwise
     *         it return null.
     */
    public List<Object[]> getCandidateVideoResponseByQuery(String query) ;
}
