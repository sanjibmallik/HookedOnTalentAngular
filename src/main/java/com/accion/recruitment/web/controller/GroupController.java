package com.accion.recruitment.web.controller;

import com.accion.recruitment.jpa.entities.Groups;
import com.accion.recruitment.jpa.entities.User;
import com.accion.recruitment.service.GroupService;
import com.accion.recruitment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 12/27/16 00:11 AM#$
 */

@Controller
public class GroupController {

    @Autowired
    private GroupService groupService;


    @RequestMapping(value = "hot/getAllGroups", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public Set<String> getAllGroups() {

        Set<String> groupsSet=new HashSet<String>();
        List<Groups> groupsList=this.groupService.getAllGroups();

        for(Groups groups:groupsList){
            groupsSet.add(groups.getGroupName());
        }
        groupsSet.remove("Client");
        return  groupsSet;

    }


}
