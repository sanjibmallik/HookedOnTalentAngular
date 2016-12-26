package com.accion.recruitment.jpa.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Manas
 *
 */

//@Table(name = "admin")
public class Admin  {

	private static final long serialVersionUID = -5778963590024982356L;
	private Long id;
	private String firstName;
	private String secondName;

	public Admin(Long id, String firstName, String secondName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.secondName = secondName;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

}