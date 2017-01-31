package com.accion.recruitment.dao;

import com.accion.recruitment.jpa.entities.User;

import java.sql.SQLException;
import java.util.List;


/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 12/23/16 00:11 AM#$
 */
public interface UserServiceDAO<E> {

    /**
     * saveUser() provide the specification for persisting
     * the User object in the database.
     *
     * @param user accept the instance of the User class
     *             containing data.
     * @return status of the persisting operation.
     */

    public Boolean saveUser(User user);

    /**
     * findUserByUserNameOREmailId() provide the specification for retrieval of the
     * User object from the database based on the UserName OR EmailId.
     *
     * @param userNameOREmailId  accept the java.lang.String type of userNameOREmailId
     *                  which is having UserName OR EmailId value .
     * @return instance of User having persisting state otherwise
     *         it return null.
     */
    public User findUserByUserNameOREmailId(final String userNameOREmailId);

    /**
     * findUserByUserNameOREmailIdAndPassword() provide the specification for retrieval of the
     * User object from the database based on the UserName OR EmailId And Password.
     *
     * @param userNameOREmailId  accept the java.lang.String type of userNameOREmailId
     *                  which is having UserName OR EmailId value .
     * @param password  accept the java.lang.String type of userNameOREmailId
     *                  which is having password value .
     * @return instance of User having persisting state otherwise
     *         it return null.
     */
    public User findUserByUserNameOREmailIdAndPassword(final String userNameOREmailId,final String password);

    /**
     * findUserByUserNameOREmailIdAndPasswordAndDisabledOREnabled() provide the specification for retrieval of the
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
    public User findUserByUserNameOREmailIdAndPasswordAndDisabledOREnabled(final String userNameOREmailId,final String password,final Boolean bolValue);

    /**
     * findAllUser() provide the specification for getting all the
     * User objects from the database.
     *
     * @return instance of the java.util.List containing the
     *         User object of the persisting state.
     */
    public List<E> findAllUser();

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
     * findUserByPropertyName() provide the specification for retrieval of the
     * User object from the database based on the table column name.
     *
     * @param propName accept the java.lang.String type of propName
     *                  which is having column name value .
     * @param propValue accept the java.lang.String type of propValue
     *                  which is having column value .
     * @return instance of User having persisting state otherwise
     *         it return null.
     */

    public User findUserByPropertyName(final String propName,final Object propValue);


    /**
     * findUserById() provide the specification for retrieval of the
     * User object from the database based on the User name.
     *
     * @param userId accept the java.lang.int type of userId
     *                  which is having user ID value
     *
     * @return instance of User having persisting state otherwise
     *         it return null.
     */
    public User findUserById(final int userId) throws SQLException;

    /**
     * findUserByRole() provide the specification for retrieval of the
     * List of User object from the database based on the role name.

     * @param role accept the java.lang.String type of role
     *                  which is having Role value
     *
     * @return instance of List of User having persisting state otherwise
     *         it return null.
     */
    public List<User> findUserByRole(final String role);


    /**
     * deleteRecordByQuery() delete the record from the DB based
     * on the Query.
     *
     * @param query accept the java.lang.String type
     *                  which is having Database Query
     *
     * @return instance of Record having persisting state otherwise
     *         it return null.
     */
    public Boolean deleteRecordByQuery(String query) throws SQLException;
}
