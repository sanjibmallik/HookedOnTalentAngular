package com.accion.recruitment.dao.impl;

import com.accion.recruitment.dao.CandidateServiceDAO;
import com.accion.recruitment.jpa.entities.Candidates;
import com.accion.recruitment.jpa.entities.Positions;
import com.accion.recruitment.jpa.entities.User;
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
 * $Date:: 25/01/17 00:11 AM#$
 */


@Repository(value = "candidateServiceDAOImpl")
public class CandidateServiceDAOImpl<R> implements CandidateServiceDAO {


    protected Logger logger;

    @Autowired(required = true)
    protected SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public Boolean saveCandidates(final Candidates candidates){
        final Session session = getSession();
        session.saveOrUpdate(candidates);
        return true;
    }


    @Override
    public List<R> findAllCandidates() {
        final Session session = getSession();
        final Criteria criteria = session.createCriteria(Candidates.class);
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.addOrder(Order.desc("candidateId"));
        List<R> rList = criteria.list();
        return rList;
    }


    @Override
    public Candidates findCandidatesByPropertyName(final String propName,final Object propValue) {
        final Session session = getSession();
        final Criteria criteria = session.createCriteria(Candidates.class);
        criteria.add(Restrictions.eq(propName, propValue));
        return (Candidates) criteria.uniqueResult();
    }

    @Override
    public Candidates findCandidatesById(final int candidateId) {
        final Session session = getSession();
        final Criteria criteria = session.createCriteria(Candidates.class);
        criteria.add(Restrictions.eq("candidateId",candidateId));
        return (Candidates) criteria.uniqueResult();
    }

    @Override
    public List<Candidates> searchCandidatesByQuery(String sqlQuery) {
        final Session session = getSession();
        List<Candidates> list = null;
        Query query = session.createSQLQuery(sqlQuery).addEntity(Candidates.class);
        list = query.list();
        return list;
    }

}
