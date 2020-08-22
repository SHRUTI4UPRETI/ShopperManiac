package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.LoginDto;
import com.lti.dto.LoginStatusAdmin;
import com.lti.dto.Status.StatusType;
import com.lti.dto.loginStatus;
import com.lti.model.Admin;
import com.lti.service.AdminService;

@RestController
@CrossOrigin
public class AdminController {

	@Autowired
	private AdminService adminServ;
	
	@PostMapping("/adminLogin")
	public LoginStatusAdmin loginAdmin(@RequestBody LoginDto loginDto) {
		LoginStatusAdmin loginStatusAdmin = new LoginStatusAdmin();

		Admin admin = adminServ.loginAdmin(loginDto.getEmail(), loginDto.getPassword());

		if (admin != null) {

			loginStatusAdmin.setAdminEmail(admin.getAdminEmail());
			loginStatusAdmin.setMessage("Admin Login Successful");
			loginStatusAdmin.setStatus(StatusType.SUCCESS);
		}

		else {
			loginStatusAdmin.setMessage("Error Logging in Admin");
			loginStatusAdmin.setStatus(StatusType.FAILURE);
		}
		return loginStatusAdmin;
	}
}

