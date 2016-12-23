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
import java.util.logging.Logger;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * @version $Revision: 0001 $, $Date:: 12/23/16 00:11 AM#$
 */

@Repository
public class UserServiceDAOImpl implements UserServiceDAO {


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
    public User getLoginUserByUserNameAndPassword(final String userName,final String password){

        final Session session = getSession();
        User userObject=new User();

        /*final String checkUserNameOrEmailExistQuery="select id,userName,firstName,LastName,record_Active,CREATED_BY,CREATED_DATE,UPDATED_BY,UPDATED_DATE,dob from default.users where userName='"+userName+"' or email='"+userName+"'";*/

        final String checkUserNameOrEmailExistQuery="select * from default.users where userName='"+userName+"' or email='"+userName+"'";
        final Query checkUserNameOrEmail = session.createSQLQuery(checkUserNameOrEmailExistQuery).addEntity(User.class);
        if(checkUserNameOrEmail.list().isEmpty()){
            userObject.setErrorMessage("userName/emailId is wrong");
            return userObject;
        }

        final String checkUserNameAndPasswordExistQuery="select * from default.users where (userName='"+userName+"' or email='"+userName+"') and password='"+password+"'";
        final Query  checkUserNameAndPassword = session.createSQLQuery(checkUserNameAndPasswordExistQuery).addEntity(User.class);
        if(checkUserNameAndPassword.list().isEmpty()){
            userObject.setErrorMessage("userName and password is wrong");
            return userObject;
        }

        final String checkUserIsDisabledExistQuery="select * from default.users where enabled=1 and (userName='"+userName+"' or email='"+userName+"') and password='"+password+"'";
        final Query  checkUserIsDisabled = session.createSQLQuery(checkUserIsDisabledExistQuery).addEntity(User.class);
        if(checkUserIsDisabled.list().isEmpty()){
            userObject.setErrorMessage("user is disabled");
            return userObject;
        }else{
            userObject=(User)checkUserIsDisabled.uniqueResult();
        }

        return userObject;
    }

}
