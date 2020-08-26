package com.lti.controller;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.mail.MailException;

import com.lti.dto.ChangeProductStockInRetailerUIDto;
import com.lti.dto.DisplayCustomerDto;
import com.lti.dto.DisplayProductsForRetailerDto;
import com.lti.dto.DisplayRetailerDto;
import com.lti.dto.LoginDto;
import com.lti.dto.PlaceOrderDto;
import com.lti.dto.RetailerIdDto;
import com.lti.dto.RetailerStatus;
import com.lti.dto.Status;
import com.lti.dto.UpdateCustomerPasswordDto;
import com.lti.dto.UpdateRetailerPasswordDto;
import com.lti.dto.Status.StatusType;
import com.lti.exception.RetailerServiceException;
import com.lti.model.Customer;
import com.lti.model.Product;
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
			message.setText("Hi, " + retailer.getRetailerName() + " \n Your UserName is: "+retailer.getRetailerEmail()+"and your Password is: "+retailer.getRetailerPassword()+" \n Thankyou for collaborating with ShopperManiac");
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
	
	@PostMapping("/updateRetailerPassword")
	public Status updateRetailerPassword(@RequestBody UpdateRetailerPasswordDto updateDto) {
		Status status = new Status();
		int i = retailerService.updateRetailerPassword(updateDto.getRetailerId(), updateDto.getRetailerPassword());
		if (i>0) {
			status.setMessage("Password Updated");
			status.setStatus(StatusType.SUCCESS);
			
		}else {
			status.setMessage("Password update Failed");
			status.setStatus(StatusType.FAILURE);
		}
		return status;
	}
	
	@PostMapping("/dislayRetailerDetails")
	public DisplayRetailerDto displayRetailerDetails(@RequestBody RetailerIdDto retailerId) {
		
	Retailer retailer = retailerService.displayRetailerDetails(retailerId.getRetailerId());
    
	DisplayRetailerDto displayRetailerDto = new DisplayRetailerDto();
	
	displayRetailerDto.setRetailerName(retailer.getRetailerName());
	displayRetailerDto.setRetailerEmail(retailer.getRetailerEmail());
	displayRetailerDto.setRetailerMobile(retailer.getRetailerMobile());
	
	return displayRetailerDto;	
	
	}
	
	@PostMapping("/displayproductsByRetailerId")
	public List<DisplayProductsForRetailerDto> displayProductsByRetailerId(@RequestBody RetailerIdDto retailerId) {
		
		List<Product> products = retailerService.viewProductsOfRetailer(retailerId.getRetailerId());
		
		List<DisplayProductsForRetailerDto> productList = new ArrayList<>();
		
		
		for(Product pd: products) {
			DisplayProductsForRetailerDto dto = new DisplayProductsForRetailerDto();
			dto.setProductId(pd.getProductId());
			dto.setProductName(pd.getProductName());
			dto.setProductQuantity(pd.getProductQuantity());
			dto.setProductImagePath(pd.getProductImagePath());
			
			productList.add(dto);
		}
		
		return productList;
		
	}
	
	@PostMapping("/changeProductStock")
	public Status changeProductStockInInventory(@RequestBody ChangeProductStockInRetailerUIDto dto) {
		
		int i = retailerService.changeProductStockInInventory(dto.getProductId(), dto.getProductQuantity());
		
		Status status = new Status();
		if(i>0) {
			status.setMessage("Product Quantity Changed");
			status.setStatus(Status.StatusType.SUCCESS);
		}
		
		else {
			status.setMessage("Error in changing product quantity");
			status.setStatus(Status.StatusType.FAILURE);
		}
		
		return status;
		
	}
	
}
