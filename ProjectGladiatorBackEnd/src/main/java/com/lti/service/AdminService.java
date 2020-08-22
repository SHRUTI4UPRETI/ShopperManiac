package com.lti.service;

import com.lti.model.Admin;

public interface AdminService {

	Admin loginAdmin(String email, String password);
	
}
