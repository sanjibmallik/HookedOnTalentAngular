package com.accion.recruitment.service.impl;

import com.accion.recruitment.dao.GroupServiceDAO;
import com.accion.recruitment.jpa.entities.Groups;
import com.accion.recruitment.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 12/27/16 00:11 AM#$
 */
@Service("groupService")
@Transactional
public class GroupServiceImpl implements GroupService {


    @Autowired
    @Qualifier(value = "groupServiceDAOImpl")
    private GroupServiceDAO groupServiceDAO;

    @Override
    public Set<String> getGroupsName() {
        Set<String> groupsSet=new HashSet<String>();
        final List<Groups> groupsList =this.groupServiceDAO.getAllGroups();
        for(Groups groups:groupsList){
            groupsSet.add(groups.getGroupName());
        }
        groupsSet.remove("Client");
        return groupsSet;
    }
}
