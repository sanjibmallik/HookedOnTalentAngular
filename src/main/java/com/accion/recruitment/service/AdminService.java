package com.accion.recruitment.service;

import java.util.List;

import com.accion.recruitment.jpa.entities.Admin;

/**
 * @author Manas
 *
 */
public interface AdminService {
	
	public List<Admin> findAll();

}
