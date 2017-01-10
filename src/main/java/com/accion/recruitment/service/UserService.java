package com.accion.recruitment.service;

import com.accion.recruitment.jpa.entities.User;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 12/26/16 00:11 AM#$
 */
public interface UserService {


    /**
     * saveUser() provide the specification for persisting
     * the User object in the database.
     *
     * @param user accept the instance of the User class
     *             containing data.
     * @return status of the persisting operation.
     */

    public Boolean saveUser(final User user) throws SQLException;

    /**
     * saveUserGroups() provide the specification for persisting
     * the User object in the database.
     *
     * @param user accept the instance of the User class
     *             containing data.
     * @return status of the persisting operation.
     */

    public Boolean saveUserGroups(final User user) throws SQLException;

    /**
     * findAllUser() provide the specification for getting all the
     * User objects from the database.
     *
     * @return instance of the java.util.List containing the
     *         User object of the persisting state.
     */

    public List<User> findAllUser() throws SQLException;

    /**
     * findUserByPropertyName() provide the specification for retrieval of the
     * User object from the database based on the Property name.
     *
     * @param propName accept the java.lang.String type of propName
     *                  which is having column name value .
     * @param propValue accept the java.lang.String type of propValue
     *                  which is having column value .
     *
     * @return instance of User having persisting state otherwise
     *         it return null.
     */
    public User findUserByPropertyName(final String propName,final Object propValue) throws SQLException;

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
     * deleteUserByQuery() delete the record from the DB based
     * on the Query.
     *
     * @param query accept the java.lang.String type
     *                  which is having Database Query
     *
     * @return instance of Record having persisting state otherwise
     *         it return null.
     */
    public Boolean deleteRecordByQuery(String query)throws SQLException;

}
