package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.RegisterStatus;
import com.lti.dto.Status;
import com.lti.dto.Status.StatusType;

import com.lti.exception.RetailerServiceException;
import com.lti.model.Retailer;
import com.lti.service.RetailerService;

@RestController
@CrossOrigin
public class RetailerController {

	@Autowired
	private RetailerService retailerService;
	
	@PostMapping("/retailerRegister")
	public Status register(@RequestBody Retailer retailer) {
		Status status= new Status();
		try {
			retailerService.register(retailer);
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Registration Successful");
		}catch (RetailerServiceException e) {
			status.setStatus(StatusType.FAILURE);
			status.setMessage(e.getMessage());
		}
		return status;
	}
}
