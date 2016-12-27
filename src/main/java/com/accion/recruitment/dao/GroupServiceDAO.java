package com.accion.recruitment.dao;

import com.accion.recruitment.jpa.entities.Groups;
import com.accion.recruitment.jpa.entities.User;

import java.util.List;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 12/26/16 00:11 AM#$
 */
public interface GroupServiceDAO<E> {

    /**
     * saveUser() provide the specification for persisting
     * the User object in the database.
     *
     * @param user accept the instance of the User class
     *             containing data.
     * @return status of the persisting operation.
     */

    public Boolean saveUserGroups(User user);

    /**
     * getGroupByGroupName() provide the specification for retrieval of the
     * Groups object from the database based on the groupName.
     *
     * @param groupName  accept the java.lang.String type of groupName
     *                  which is having Group Name value .
     * @return instance of Groups having persisting state otherwise
     *         it return null.
     */
    public Groups getGroupByGroupName(final String groupName);

    /**
     * getAllGroups() provide the specification
     * for getting all the Groups form database
     * for particular user.
     *
     * @return the instance of the java.util.List containing
     *         the Groups for the particular Groups.
     */
    public List<E> getAllGroups();

}
