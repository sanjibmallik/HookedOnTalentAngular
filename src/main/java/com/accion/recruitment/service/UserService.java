package com.accion.recruitment.service;

import com.accion.recruitment.jpa.entities.User;

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

    public Boolean saveUser(final User user);

    /**
     * getAllUser() provide the specification for getting all the
     * User objects from the database.
     *
     * @return instance of the java.util.List containing the
     *         User object of the persisting state.
     */

    public List<User> getAllUser();
}