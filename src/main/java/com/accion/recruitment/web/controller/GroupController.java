package com.accion.recruitment.web.controller;

import com.accion.recruitment.common.constants.GroupRestURIConstants;
import com.accion.recruitment.common.constants.UserRestURIConstants;
import com.accion.recruitment.jpa.entities.Groups;
import com.accion.recruitment.jpa.entities.User;
import com.accion.recruitment.service.GroupService;
import com.accion.recruitment.service.UserService;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.sql.SQLException;
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


    @ApiOperation(value = "Get the Groups Name  ", httpMethod="GET"
            , notes = "Return  All the group  names except client group")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful Group Names Send "),
            @ApiResponse(code = 404, message = "Group Names  not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(value = GroupRestURIConstants.GET_GROUP_NAME, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> getGroupsName() {
        try{
            List<Groups> groupsList=this.groupService.findAllGroup();
            Set<String> groupsSet=new HashSet<String>();
            for(Groups groups:groupsList){
                groupsSet.add(groups.getGroupName());
            }
            groupsSet.remove("Client");
            String json = new Gson().toJson(groupsSet );
            return new ResponseEntity<String>(json, HttpStatus.OK);
        }catch (SQLException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
