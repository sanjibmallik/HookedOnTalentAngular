package com.accion.recruitment.service;

import com.accion.recruitment.jpa.entities.Groups;

import java.util.List;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 12/27/16 00:11 AM#$
 */

public interface GroupService {

    /**
     * getAllGroups() provide the specification
     * for getting all the Groups form database
     * for particular user.
     *
     * @return the instance of the java.util.List containing
     *         the Groups for the particular Groups.
     */
    public List<Groups> getAllGroups() ;
}
