package com.accion.recruitment.dao.impl;

import com.accion.recruitment.dao.RequirementServiceDAO;
import com.accion.recruitment.jpa.entities.PositionCandidates;
import com.accion.recruitment.jpa.entities.Positions;
import com.accion.recruitment.jpa.entities.User;
import com.accion.recruitment.jpa.entities.VideoQuestion;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.logging.Logger;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 23/01/17 00:11 AM#$
 */

@Repository(value = "requirementServiceDAOImpl")
public class RequirementServiceDAOImpl<R> implements RequirementServiceDAO {


    protected Logger logger;

    @Autowired(required = true)
    protected SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public Boolean saveRequirements(final Positions requirement){
        final Session session = getSession();
        session.saveOrUpdate(requirement);
        return true;
    }

    @Override
    public List<R> findAllRequirement() {
        final Session session = getSession();
        final Criteria criteria = session.createCriteria(Positions.class);
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.addOrder(Order.desc("positionId"));
        List<R> rList = criteria.list();
        return rList;
    }

    @Override
    public Positions findRequirementById(final int positionId) {
        final Session session = getSession();
        final Criteria criteria = session.createCriteria(Positions.class);
        criteria.add(Restrictions.eq("positionId", positionId));
        return (Positions) criteria.uniqueResult();
    }

    @Override
    public Boolean deleteRecordByQuery(final String query) {
        final Session session = getSession();
        final Query sqlQuery = session.createSQLQuery(query);
        sqlQuery.executeUpdate();
        return true;
    }

    @Override
    public List<R> findAllRequirementVideoQuestions(String query){
        final Session session = getSession();
        final Query sqlQuery = session.createSQLQuery(query).addEntity(VideoQuestion.class);
        List<R> rList = sqlQuery.list();
        return rList;
    }


    @Override
    public List<PositionCandidates> findAllRequirementCandidateByRequirementId(final Integer requirementId) {
        final Session session = getSession();
        String sqlQuery="select * from default.position_candidates where positionId='"+requirementId+"' ORDER BY position_candidates_id DESC";
        Query query = session.createSQLQuery(sqlQuery).addEntity(PositionCandidates.class);
        List<PositionCandidates> positionCandidatesList=query.list();
        return positionCandidatesList;
    }

}
