package com.accion.recruitment.dao.impl;

import com.accion.recruitment.dao.TechnicalScreenerDAO;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AL1451 on 11/30/16.
 */
@Repository
public class TechnicalScreenerDAOImpl implements TechnicalScreenerDAO {

    @Autowired(required = true)
    protected SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List getTSByQuery(String query){


        List<Object[]> list = new ArrayList<Object[]>();

        final Session session = getSession();

        Query query1 = session.createSQLQuery("select * from default.users");

        System.out.print("printing :"+query1);
        return list;
    }


}
