package com.accion.recruitment.service.impl;

import com.accion.recruitment.dao.CandidateResponseServiceDAO;
import com.accion.recruitment.dao.CandidateServiceDAO;
import com.accion.recruitment.jpa.entities.CandidateFinalResult;
import com.accion.recruitment.jpa.entities.Candidates;
import com.accion.recruitment.service.CandidateResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 27/01/17 00:11 AM#$
 */

@Service("candidateResponseService")
@Transactional
public class CandidateResponseServiceImpl implements CandidateResponseService {

    @Autowired
    @Qualifier(value = "candidateResponseServiceDAOImpl")
    private CandidateResponseServiceDAO candidateResponseServiceDAO;


    @Override
    public CandidateFinalResult findCandidateFinalResultByPositionIdAndCandidateId(Integer positionId,Integer candidateId) throws SQLException {
        return this.candidateResponseServiceDAO.findCandidateFinalResultByPositionIdAndCandidateId(positionId,candidateId);
    }


    @Override
    public Boolean deleteCandidateGeneralQuestionResponseByQuery(Integer positionId,Integer candidateId) throws SQLException {
        String query="delete  from default.candidate_general_question_response where positionId='"+positionId+"' and candidateId='"+candidateId+"'";
        return this.candidateResponseServiceDAO.deleteRecordByQuery(query);
    }

    @Override
    public Boolean deleteCandidateTechnicalQuestionResponseByQuery(Integer positionId,Integer candidateId) throws SQLException {
        String query="delete  from default.candidate_technical_question_response where positionId='"+positionId+"' and candidateId='"+candidateId+"'";
        return this.candidateResponseServiceDAO.deleteRecordByQuery(query);
    }

    @Override
    public Boolean deleteCandidateVideoQuestionResponseByQuery(Integer positionId,Integer candidateId) throws SQLException {
        String query="delete  from default.candidate_video_question_response where positionId='"+positionId+"' and candidateId='"+candidateId+"'";
        return this.candidateResponseServiceDAO.deleteRecordByQuery(query);
    }

    @Override
    public Boolean deleteCandidateSelfRatingResponseByQuery(Integer positionId,Integer candidateId) throws SQLException {
        String query="delete  from default.candidate_self_rating_response where positionId='"+positionId+"' and candidateId='"+candidateId+"'";
        return this.candidateResponseServiceDAO.deleteRecordByQuery(query);
    }

    @Override
    public Boolean deleteCandidateFinalResultResponseByQuery(Integer positionId,Integer candidateId) throws SQLException {
        String query="delete  from default.candidate_final_result where positionId='"+positionId+"' and candidateId='"+candidateId+"'";
        return this.candidateResponseServiceDAO.deleteRecordByQuery(query);
    }

}
