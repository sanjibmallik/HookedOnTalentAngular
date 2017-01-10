package com.accion.recruitment.dao.impl;

import com.accion.recruitment.dao.ClientServiceDAO;
import com.accion.recruitment.jpa.entities.ClientDetails;
import com.accion.recruitment.jpa.entities.User;
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
 * $Date:: 10/01/17 00:11 AM#$
 */


@Repository(value = "clientServiceDAOImpl")
public class ClientServiceDAOImpl implements ClientServiceDAO {


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
    public ClientDetails findClientDetailsByPropertyName(final String propName,final Object propValue) {
        final Session session = getSession();
        final Criteria criteria = session.createCriteria(ClientDetails.class);
        criteria.add(Restrictions.eq(propName, propValue));
        return (ClientDetails) criteria.uniqueResult();
    }
}
