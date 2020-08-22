package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.LoginDto;
import com.lti.dto.RetailerStatus;
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
	
	@PostMapping("/retailerLogin")
	public RetailerStatus retailerLogin(@RequestBody LoginDto loginDto) {
		
	System.out.println(loginDto);
		RetailerStatus retailerStatus = new RetailerStatus();
		try {
			Retailer retailer = retailerService.loginRetailer(loginDto.getEmail(), loginDto.getPassword());
			retailerStatus.setRetailerId(retailer.getRetailerId());
			retailerStatus.setMessage("Login Successful");
			retailerStatus.setRetailerName(retailer.getRetailerName());
			retailerStatus.setStatus(StatusType.SUCCESS);
		} catch (RetailerServiceException e) {
			retailerStatus.setStatus(StatusType.FAILURE);
			retailerStatus.setMessage(e.getMessage());
		}
		return retailerStatus;
	}
}
