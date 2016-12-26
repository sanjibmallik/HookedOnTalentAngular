package com.accion.recruitment.dao;

import com.accion.recruitment.jpa.entities.User;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 12/26/16 00:11 AM#$
 */
public interface LoginServiceDAO {

    /**
     * getLoginUserByUserNameOREmailIdAndPassword() provide the specification for retrieval of the
     * User object from the database based on the UserName OR EmailId And Password.
     *
     * @param userNameOREmailId  accept the java.lang.String type of userNameOREmailId
     *                  which is having UserName OR EmailId value .
     * @param password  accept the java.lang.String type of userNameOREmailId
     *                  which is having password value .
     * @return instance of User having persisting state otherwise
     *         it return null.
     */

    public User getLoginUserByUserNameOREmailIdAndPassword(final String userNameOREmailId,final String password);
}
