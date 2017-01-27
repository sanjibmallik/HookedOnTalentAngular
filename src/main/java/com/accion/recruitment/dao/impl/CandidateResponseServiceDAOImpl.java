package com.accion.recruitment.dao.impl;

import com.accion.recruitment.dao.CandidateResponseServiceDAO;
import com.accion.recruitment.jpa.entities.CandidateFinalResult;
import com.accion.recruitment.jpa.entities.Candidates;
import com.accion.recruitment.service.CandidateResponseService;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.logging.Logger;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 27/01/17 00:11 AM#$
 */
@Repository(value = "candidateServiceDAOImpl")
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
}
