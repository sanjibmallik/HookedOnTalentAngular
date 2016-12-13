package com.accion.recruitment.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accion.recruitment.dao.AdminDAO;
import com.accion.recruitment.jpa.entities.Admin;
import com.accion.recruitment.service.AdminService;

/**
 * @author Manas
 *
 */
@Service("adminService")
@Transactional
public class AdminServiceImpl implements AdminService {

	 private AdminDAO adminDAO;

	@Autowired
	public AdminServiceImpl(final AdminDAO adminDAO) {
		this.adminDAO = adminDAO;
	}

	@Override
	public List<Admin> findAll() {
		List<Admin> adminList = new ArrayList<Admin>();
		adminList.add(new Admin(1L, "First Name 1", "Second Name 1"));
		adminList.add(new Admin(2L, "First Name 2", "Second Name 2"));
		adminList.add(new Admin(3L, "First Name 3", "Second Name 3"));
		adminList.add(new Admin(4L, "First Name 4", "Second Name 4"));
		adminList.add(new Admin(5L, "First Name 5", "Second Name 5"));
		return adminList;
	}
}