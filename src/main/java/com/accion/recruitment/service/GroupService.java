package com.accion.recruitment.service;

import com.accion.recruitment.jpa.entities.Groups;

import java.util.List;
import java.util.Set;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 12/27/16 00:11 AM#$
 */

public interface GroupService {

    /**
     * getAllGroups() provide the specification
     * for getting all the Group Names
     *
     * @return the instance of the java.util.List containing
     *         the Group Names.
     */
    public Set<String> getGroupsName() ;
}
