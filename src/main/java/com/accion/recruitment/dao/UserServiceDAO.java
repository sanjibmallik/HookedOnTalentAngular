package com.accion.recruitment.dao;

import com.accion.recruitment.jpa.entities.User;



/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 12/23/16 00:11 AM#$
 */
public interface UserServiceDAO {

    /**
     * getUserByUserNameOREmailId() provide the specification for retrieval of the
     * User object from the database based on the UserName OR EmailId.
     *
     * @param userNameOREmailId  accept the java.lang.String type of userNameOREmailId
     *                  which is having UserName OR EmailId value .
     * @return instance of User having persisting state otherwise
     *         it return null.
     */
    public User getUserByUserNameOREmailId(final String userNameOREmailId);

    /**
     * getUserByUserNameOREmailIdAndPassword() provide the specification for retrieval of the
     * User object from the database based on the UserName OR EmailId And Password.
     *
     * @param userNameOREmailId  accept the java.lang.String type of userNameOREmailId
     *                  which is having UserName OR EmailId value .
     * @param password  accept the java.lang.String type of userNameOREmailId
     *                  which is having password value .
     * @return instance of User having persisting state otherwise
     *         it return null.
     */
    public User getUserByUserNameOREmailIdAndPassword(final String userNameOREmailId,final String password);

    /**
     * getUserByUserNameOREmailIdAndPasswordIsDisabled() provide the specification for retrieval of the
     * User object from the database based on the UserName OR EmailId, Password And User Enabled .
     *
     * @param userNameOREmailId  accept the java.lang.String type of userNameOREmailId
     *                  which is having UserName OR EmailId value .
     * @param password  accept the java.lang.String type of userNameOREmailId
     *                  which is having password value .
     * @param bolValue  accept the java.lang.Boolean type of bolValue
     *                  which is having true or false value .
     * @return instance of User having persisting state otherwise
     *         it return null.
     */
    public User getUserByUserNameOREmailIdAndPasswordAndDisabledOREnabled(final String userNameOREmailId,final String password,final Boolean bolValue);


}
