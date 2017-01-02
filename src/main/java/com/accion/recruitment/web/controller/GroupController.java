package com.accion.recruitment.web.controller;

import com.accion.recruitment.common.constants.GroupRestURIConstants;
import com.accion.recruitment.jpa.entities.Groups;
import com.accion.recruitment.jpa.entities.User;
import com.accion.recruitment.service.GroupService;
import com.accion.recruitment.service.UserService;
import io.swagger.annotations.Api;
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
@Api(value="Roles Controller", description = "Roles Operation")
public class GroupController {

    @Autowired
    private GroupService groupService;


    @RequestMapping(value = GroupRestURIConstants.GET_GROUP_NAME, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public Set<String> getGroupsName() {
        Set<String> groupsSet=this.groupService.getGroupsName();
        return  groupsSet;
    }


}
