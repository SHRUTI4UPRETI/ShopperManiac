package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.LoginDto;
import com.lti.dto.Status.StatusType;
import com.lti.dto.loginStatus;
import com.lti.exception.CustomerServiceException;
import com.lti.model.Customer;
import com.lti.service.CustomerService;
import com.lti.service.RetailerService;

@RestController
@CrossOrigin
public class CustomerController {

	@Autowired
	private CustomerService customerServ;
	
	@PostMapping("/register")
	public loginStatus register(@RequestBody Customer customer) {
		loginStatus status= new loginStatus();
		try {
			customerServ.register(customer);
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Registration Successful");
		}
			catch (CustomerServiceException e) {
				status.setStatus(StatusType.FAILURE);
				status.setMessage(e.getMessage());
			}
		
		return status;
	}

	@Autowired
	private RetailerService retailerService;

	@PostMapping("/customerLogin")
	public loginStatus login(@RequestBody LoginDto loginDto) {
		loginStatus loginStatus = new loginStatus();

		Customer customer = customerServ.loginCustomer(loginDto.getEmail(), loginDto.getPassword());

		if (customer != null) {

			loginStatus.setCustomerId(customer.getCustomerId());
			loginStatus.setMessage("Login Successful");
			loginStatus.setName(customer.getCustomerName());
			loginStatus.setStatus(StatusType.SUCCESS);
		}

		else {
			loginStatus.setStatus(StatusType.FAILURE);
			loginStatus.setMessage("Invalid credentials");
		}
		return loginStatus;
	}

}
