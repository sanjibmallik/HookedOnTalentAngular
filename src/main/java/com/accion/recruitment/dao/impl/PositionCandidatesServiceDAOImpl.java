package com.accion.recruitment.dao.impl;

import com.accion.recruitment.dao.PositionCandidatesServiceDAO;
import com.accion.recruitment.jpa.entities.Candidates;
import com.accion.recruitment.jpa.entities.PositionCandidates;
import com.accion.recruitment.jpa.entities.User;
import com.accion.recruitment.service.PositionCandidatesService;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.logging.Logger;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 31/01/17 00:11 AM#$
 */

@Repository(value = "positionCandidatesServiceDAOImpl")
public class PositionCandidatesServiceDAOImpl<R> implements PositionCandidatesServiceDAO{

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
    public Boolean savePositionCandidates(PositionCandidates positionCandidates){
        final Session session = getSession();
        session.saveOrUpdate(positionCandidates);
        return true;
    }

    @Override
    public List<Candidates> findPositionCandidatesByRequirementId(final int requirementId) {
        final Session session = getSession();
        String sqlQuery="SELECT * \n" +
                "FROM default.position_candidates \n" +
                "inner join default.candidates \n" +
                "on position_candidates.candidateId=candidates.candidateId \n" +
                "where positionId ='"+requirementId+"'";
        Query query = session.createSQLQuery(sqlQuery).addEntity(Candidates.class);
        return query.list();
    }

    @Override
    public PositionCandidates findPositionCandidatesByRequirementIdAndCandidateId(final int requirementId,final int candidateId) {
        final Session session = getSession();
        final Criteria criteria = session.createCriteria(PositionCandidates.class);
        Criterion userNameCriteria= Restrictions.eq("positionId", requirementId);
        Criterion emailIDCriteria=Restrictions.eq("candidateId", candidateId);
        criteria.add(Restrictions.or(userNameCriteria, emailIDCriteria));
        final PositionCandidates positionCandidates = (PositionCandidates) criteria.uniqueResult();
        return positionCandidates;
    }


}
