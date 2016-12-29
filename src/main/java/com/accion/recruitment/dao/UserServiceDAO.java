package com.accion.recruitment.dao;

import com.accion.recruitment.jpa.entities.User;

import java.util.List;


/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 12/23/16 00:11 AM#$
 */
public interface UserServiceDAO<E> {

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

    /**
     * getAllUser() provide the specification for getting all the
     * User objects from the database.
     *
     * @return instance of the java.util.List containing the
     *         User object of the persisting state.
     */
    public List<E> getAllUser();

    /**
     * size() provide the specification for getting the
     * number of entity having persisting state in
     * database table.
     *
     * @return java.lang.Long object containing the size of
     *         the records in the database table.
     */
    public Long size();

    /**
     * getUserByUserName() provide the specification for retrieval of the
     * User object from the database based on the User name.
     *
     * @param user accept the instance of the User containing the
     *             User Name for selection from the database.
     * @return instance of User having persisting state otherwise
     *         it return null.
     */

    public User getUserByUserName(User user);

    /**
     * getUserByEmailId() provide the specification for retrieval of the
     * User object from the database based on the Email Id.
     *
     * @param user accept the instance of the User containing the
     *             User Name for selection from the database.
     * @return instance of User having persisting state otherwise
     *         it return null.
     */

    public User getUserByEmailId(User user);

    /**
     * getUserByContactNumber() provide the specification for retrieval of the
     * User object from the database based on the Email Id.
     *
     * @param user accept the instance of the User containing the
     *             Contact Number for selection from the database.
     * @return instance of User having persisting state otherwise
     *         it return null.
     */

    public User getUserByContactNumber(User user);
}
