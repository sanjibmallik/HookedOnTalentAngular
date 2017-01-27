package com.accion.recruitment.dao.impl;

import com.accion.recruitment.dao.QuestionServiceDAO;
import com.accion.recruitment.jpa.entities.*;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 23/01/17 00:11 AM#$
 */

@Repository(value = "questionServiceDAOImpl")
public class QuestionServiceDAOImpl<R> implements QuestionServiceDAO {

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
    public Boolean saveQuestion(final Object question){
        final Session session = getSession();
        session.saveOrUpdate(question);
        return true;
    }

    @Override
    public Boolean saveDomain(final Domain domain){
        final Session session = getSession();
        session.saveOrUpdate(domain);
        return true;
    }

    @Override
    public List<GeneralQuestion> findRequirementGenericGeneralQuestion(String addToPositionStatus) {
        final Session session = getSession();
        final Criteria criteria = session.createCriteria(GeneralQuestion.class);
        criteria.add(Restrictions.eq("addToPosition", addToPositionStatus));
        List<GeneralQuestion> list = criteria.list();
        return list;
    }


    @Override
    public Object findQuestionById(final int questionId) {
        final Session session = getSession();
        final Criteria criteria = session.createCriteria(Object.class);
        criteria.add(Restrictions.eq("id",questionId));
        return (Object) criteria.uniqueResult();
    }


}
