package com.accion.recruitment.dao.impl;

import com.accion.recruitment.dao.SettingServiceDAO;
import com.accion.recruitment.jpa.entities.Settings;
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
 * $Date:: 01/02/17 00:11 AM#$
 */
@Repository(value = "settingServiceDAOImpl")
public class SettingServiceDAOImpl<R> implements SettingServiceDAO{

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
    public Boolean saveSettings(Settings settings){
        final Session session = getSession();
        session.saveOrUpdate(settings);
        return true;
    }

    @Override
    public Settings findSettingsDetailsById(final int settingId){
        final Session session = getSession();
        final Criteria criteria = session.createCriteria(Settings.class);
        criteria.add(Restrictions.eq("id", settingId));
        return (Settings) criteria.uniqueResult();

    }

}
