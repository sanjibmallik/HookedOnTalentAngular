package com.accion.recruitment.dao.impl;

import com.accion.recruitment.dao.GroupServiceDAO;
import com.accion.recruitment.jpa.entities.Groups;
import com.accion.recruitment.jpa.entities.User;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 12/26/16 00:11 AM#$
 */

@Repository(value = "groupServiceDAOImpl")
public class GroupServiceDAOImpl<R> implements GroupServiceDAO{

    @Autowired(required = true)
    protected SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Boolean saveUserGroups(User user){
        final Session session = getSession();
        Groups groups=this.getGroupByGroupName(user.getRole());
        groups.setUser(user);
        session.saveOrUpdate(user);
        return true;
    }

    @Override
    public Groups getGroupByGroupName(final String groupName) {
        final Session session = getSession();
        final Criteria criteria = session.createCriteria(Groups.class);
        criteria.add(Restrictions.eq("groupName", groupName));
        final Groups groups = (Groups) criteria.uniqueResult();
        return groups;
    }

    @Override
    public List<R> getAllGroups() {
        final Session session = getSession();
        List<R> rList = session.createCriteria(Groups.class).list();
        return rList;
    }

}
