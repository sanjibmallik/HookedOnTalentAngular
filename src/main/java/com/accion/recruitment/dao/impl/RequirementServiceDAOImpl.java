package com.accion.recruitment.dao.impl;

import com.accion.recruitment.dao.RequirementServiceDAO;
import com.accion.recruitment.jpa.entities.Positions;
import com.accion.recruitment.jpa.entities.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
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
        criteria.addOrder(Order.desc("id"));
        List<R> rList = criteria.list();
        return rList;
    }
}
