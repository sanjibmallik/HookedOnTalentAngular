package com.accion.recruitment.service.impl;

import com.accion.recruitment.dao.GroupServiceDAO;
import com.accion.recruitment.jpa.entities.Groups;
import com.accion.recruitment.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
    public List<Groups> getAllGroups() {
        final List<Groups> groupsList =this.groupServiceDAO.getAllGroups();
        return groupsList;
    }
}
