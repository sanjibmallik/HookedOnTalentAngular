package com.accion.recruitment.dao.impl;

import com.accion.recruitment.dao.CandidatePastHistoryServiceDAO;
import com.accion.recruitment.jpa.entities.CandidatePastHistory;
import com.accion.recruitment.jpa.entities.PositionCandidates;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.logging.Logger;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 31/01/17 00:11 AM#$
 */

@Repository(value = "candidatePastHistoryServiceDAOImpl")
public class CandidatePastHistoryServiceDAOImpl<R> implements CandidatePastHistoryServiceDAO {

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
    public Boolean saveCandidatePastHistory(final CandidatePastHistory candidatePastHistory){
        final Session session = getSession();
        session.saveOrUpdate(candidatePastHistory);
        return true;
    }
}
