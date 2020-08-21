package com.lti.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "tbl_admin")
public class Admin {
	
	@Id
	private String adminEmail;
	@Column
	private String adminPassword;

}
