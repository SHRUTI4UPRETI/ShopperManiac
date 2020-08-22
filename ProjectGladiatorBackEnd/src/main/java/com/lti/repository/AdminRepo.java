package com.lti.repository;

import com.lti.model.Admin;

public interface AdminRepo {

	String isValidAdmin(String adminEmail, String adminPassword);
	Admin findAdminByEmail(String adminEmail);
}
