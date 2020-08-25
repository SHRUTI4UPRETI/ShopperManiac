package com.lti.controller;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.mail.MailException;


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
	  private MailSender mailSender;
	
	@Autowired
	private RetailerService retailerService;
	
	@PostMapping("/retailerRegister")
	public Status register(@RequestBody Retailer retailer) {
		Status status= new Status();
		try {
			retailerService.register(retailer);
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Registration Successful");

			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("ShoppingManiac4@outlook.com");
			message.setTo(retailer.getRetailerEmail());       /* retailer.getRetailerEmail() */ 
			message.setSubject("Thank You for registration");
			String encodedPass = retailer.getRetailerPassword();
			byte[] actualByte = Base64.getDecoder().decode(encodedPass);
			String decodedPass = new String(actualByte); 
			retailer.setRetailerPassword(decodedPass);
			message.setText("Hi, " + retailer.getRetailerName() + "Your UserName is: "+retailer.getRetailerEmail()+"and your Password is: "+retailer.getRetailerPassword()+" Thankyou for collaborating with ShopperManiac Ltd");
			mailSender.send(message);
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
