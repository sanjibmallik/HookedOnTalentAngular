package com.accion.recruitment.dao.impl;

import com.accion.recruitment.dao.UserServiceDAO;
import com.accion.recruitment.jpa.entities.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * @version $Revision: 0001 $, $Date:: 12/23/16 00:11 AM#$
 */

@Repository(value = "userServiceDAOImpl")
public class UserServiceDAOImpl<R> implements UserServiceDAO {


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
    public User findUserByUserNameOREmailId(final String userNameOREmailID) {
        final Session session = getSession();
        final Criteria criteria = session.createCriteria(User.class);
        Criterion userNameCriteria= Restrictions.eq("userName", userNameOREmailID);
        Criterion emailIDCriteria=Restrictions.eq("emailId", userNameOREmailID);
        criteria.add(Restrictions.or(userNameCriteria, emailIDCriteria));
        final User user = (User) criteria.uniqueResult();
        return user;
    }


    @Override
    public User findUserByUserNameOREmailIdAndPassword(final String userNameOREmailId,final String password) {
        final Session session = getSession();
        final Criteria criteria = session.createCriteria(User.class);
        Criterion userNameCriteria= Restrictions.eq("userName", userNameOREmailId);
        Criterion emailIDCriteria=Restrictions.eq("emailId", userNameOREmailId);
        criteria.add(Restrictions.or(userNameCriteria, emailIDCriteria));
        criteria.add(Restrictions.eq("password", password));
        final User user = (User) criteria.uniqueResult();
        return user;
    }


    @Override
    public User findUserByUserNameOREmailIdAndPasswordAndDisabledOREnabled(final String userNameOREmailId,final String password,final Boolean bolValue) {
        final Session session = getSession();
        final Criteria criteria = session.createCriteria(User.class);
        Criterion userNameCriteria= Restrictions.eq("userName", userNameOREmailId);
        Criterion emailIDCriteria=Restrictions.eq("emailId", userNameOREmailId);
        criteria.add(Restrictions.or(userNameCriteria, emailIDCriteria));
        criteria.add(Restrictions.eq("password", password));
        criteria.add(Restrictions.eq("enabled", bolValue));
        final User user = (User) criteria.uniqueResult();
        return user;
    }

    @Override
    public List<R> findAllUser() {
        final Session session = getSession();
        List<R> rList = session.createCriteria(User.class).list();
        return rList;
    }

    @Override
    public Long size() {
        final Session session = getSession();
        final Criteria criteria = session.createCriteria(User.class);
        criteria.setProjection(Projections.rowCount()).uniqueResult();
        final Long size = (Long) criteria.uniqueResult();
        return size;
    }

    @Override
    public User findUserByPropertyName(final String propName,final Object propValue) {
        final Session session = getSession();
        final Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq(propName,propValue));
        return (User) criteria.uniqueResult();
    }

    @Override
    public User findUserById(final int userId) {
        final Session session = getSession();
        final Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("id",userId));
        return (User) criteria.uniqueResult();
    }

}
