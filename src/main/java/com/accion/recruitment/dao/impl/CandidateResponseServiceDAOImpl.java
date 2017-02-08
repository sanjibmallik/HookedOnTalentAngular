package com.accion.recruitment.dao.impl;

import com.accion.recruitment.dao.CandidateResponseServiceDAO;
import com.accion.recruitment.jpa.entities.*;
import com.accion.recruitment.service.CandidateResponseService;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.logging.Logger;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 27/01/17 00:11 AM#$
 */
@Repository(value = "candidateResponseServiceDAOImpl")
public class CandidateResponseServiceDAOImpl<R> implements CandidateResponseServiceDAO {

    protected Logger logger;

    @Autowired(required = true)
    protected SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public CandidateFinalResult findCandidateFinalResultByPositionIdAndCandidateId(final Integer positionId,final Integer candidateId) {
        final Session session = getSession();
        final Criteria criteria = session.createCriteria(CandidateFinalResult.class);
        criteria.add(Restrictions.eq("candidateId", candidateId));
        criteria.add(Restrictions.eq("positionId", positionId));
        return (CandidateFinalResult) criteria.uniqueResult();
    }

    @Override
    public List<CandidateGeneralQuestionResponse>  findCandidateGeneralQuestionResponseByPositionIdAndCandidateId(final Integer positionId,final Integer candidateId) {
        final Session session = getSession();
        final Criteria criteria = session.createCriteria(CandidateGeneralQuestionResponse.class);
        criteria.add(Restrictions.eq("candidateId", candidateId));
        criteria.add(Restrictions.eq("positionId", positionId));
        return  criteria.list();
    }

    @Override
    public List<CandidateTechnicalQuestionResponse>  findCandidateTechnicalQuestionResponseByPositionIdAndCandidateId(final Integer positionId,final Integer candidateId) {
        final Session session = getSession();
        final Criteria criteria = session.createCriteria(CandidateTechnicalQuestionResponse.class);
        criteria.add(Restrictions.eq("candidateId", candidateId));
        criteria.add(Restrictions.eq("positionId", positionId));
        return  criteria.list();
    }

    @Override
    public List<CandidateVideoQuestionResponse>  findCandidateVideoQuestionResponseByPositionIdAndCandidateId(final Integer positionId,final Integer candidateId) {
        final Session session = getSession();
        final Criteria criteria = session.createCriteria(CandidateVideoQuestionResponse.class);
        criteria.add(Restrictions.eq("candidateId", candidateId));
        criteria.add(Restrictions.eq("positionId", positionId));
        return  criteria.list();
    }

    @Override
    public List<CandidateSelfRatingResponse>  findCandidateSelfRatingResponseByPositionIdAndCandidateId(final Integer positionId,final Integer candidateId) {
        final Session session = getSession();
        final Criteria criteria = session.createCriteria(CandidateSelfRatingResponse.class);
        criteria.add(Restrictions.eq("candidateId", candidateId));
        criteria.add(Restrictions.eq("positionId", positionId));
        return  criteria.list();
    }

    @Override
    public List<CandidateUserNotes>  findCandidateUserNotesByPositionAndCandidateId(final Integer positionId,final Integer candidateId) {
        final Session session = getSession();
        final Criteria criteria = session.createCriteria(CandidateUserNotes.class);
        criteria.add(Restrictions.eq("candidateId", candidateId));
        criteria.add(Restrictions.eq("positionId", positionId));
        return  criteria.list();
    }

    @Override
    public Boolean deleteRecordByQuery(final String query) {
        final Session session = getSession();
        final Query sqlQuery = session.createSQLQuery(query);
        sqlQuery.executeUpdate();
        return true;
    }

    @Override
    public List<Object[]> getCandidateVideoResponseByQuery(final String SqlQuery) {
        final Session session = getSession();
        Query query = session.createSQLQuery(SqlQuery).addEntity(CandidateVideoQuestionResponse.class);
        return  query.list();
    }
}
