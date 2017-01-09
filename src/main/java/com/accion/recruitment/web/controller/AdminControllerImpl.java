package com.accion.recruitment.web.controller;

import java.util.List;

import javax.inject.Qualifier;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.accion.recruitment.jpa.entities.Admin;
import com.accion.recruitment.service.AdminService;


/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 12/26/16 00:11 AM#$
 */
@Controller
public class AdminControllerImpl {
/*
    @Autowired
    private AdminService adminService;

    @ApiOperation(value = "Get all Admin")
    @RequestMapping(value = "api/admin", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public List<Admin> list() {
        return adminService.findAll();
    }

	@ApiOperation(value = "Get Admin")
	@RequestMapping(value = "api/admin/{adminId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	@ResponseBody
	public Admin get(@PathVariable final Long adminId) {
		return adminService.get(adminId);
	}

	@ApiOperation(value = "Create Admin")
	@RequestMapping(value = "api/admin", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Admin create(@RequestBody final Admin admin) {
		return adminService.save(admin);
	}

	@ApiOperation(value = "Update Admin")
	@RequestMapping(value = "api/admin/{adminId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
	@ResponseBody
	public Admin update(@PathVariable final Long adminId, @RequestBody final Admin admin) {
		admin.setId(adminId);
		return adminService.update(admin);
	}

	@ApiOperation(value = "Delete Admin")
	@RequestMapping(value = "api/admin/{adminId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE)
	@ResponseBody
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable final Long adminId) {
		adminService.delete(adminId);
	}*/
}