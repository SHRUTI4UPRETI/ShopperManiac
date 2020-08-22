package com.lti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.model.Admin;
import com.lti.repository.AdminRepo;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepo adminRepo;
	
	@Override
	public Admin loginAdmin(String email, String password) {

		String adminEmail=adminRepo.isValidAdmin(email, password);
		if(adminEmail!=null)
		{
			return adminRepo.findAdminByEmail(adminEmail);
		}
		
		return null;
	}

}
