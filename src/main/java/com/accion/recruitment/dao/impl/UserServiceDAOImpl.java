package com.accion.recruitment.dao.impl;

import com.accion.recruitment.dao.UserServiceDAO;
import com.accion.recruitment.jpa.entities.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * @version $Revision: 0001 $, $Date:: 12/23/16 00:11 AM#$
 */

@Repository
public class UserServiceDAOImpl implements UserServiceDAO {


    @Autowired(required = true)
    protected SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User getLoginUserByUserNameAndPassword(final String userName,final String password){
        final Session session = getSession();
        final String loginQuery="select * from default.users where userName='"+userName+"' and password='"+password+"'";
        final Query resultSet = session.createSQLQuery(loginQuery).addEntity(User.class);
        final User userObject=(User)resultSet.uniqueResult();
        return userObject;
    }

}
