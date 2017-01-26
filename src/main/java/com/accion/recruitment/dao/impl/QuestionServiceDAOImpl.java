package com.accion.recruitment.dao.impl;

import com.accion.recruitment.dao.QuestionServiceDAO;
import com.accion.recruitment.jpa.entities.GeneralQuestion;
import com.accion.recruitment.jpa.entities.Positions;
import com.accion.recruitment.jpa.entities.User;
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

    public Boolean saveGeneralQuestion(final GeneralQuestion generalQuestion){
        final Session session = getSession();
        session.saveOrUpdate(generalQuestion);
        return true;
    }


    public List<GeneralQuestion> findRequirementGenericGeneralQuestion(String addToPositionStatus) {
        final Session session = getSession();
        final Criteria criteria = session.createCriteria(GeneralQuestion.class);
        criteria.add(Restrictions.eq("addToPosition", addToPositionStatus));
        List<GeneralQuestion> list = criteria.list();
        return list;
    }

}
