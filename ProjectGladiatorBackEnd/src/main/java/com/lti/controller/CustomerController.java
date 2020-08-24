package com.lti.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.ChangeCartQuantityDto;
import com.lti.dto.ItemDto;
import com.lti.dto.LoginDto;
import com.lti.dto.PlaceOrderDto;
import com.lti.dto.Status;
import com.lti.dto.Status.StatusType;
import com.lti.dto.loginStatus;
import com.lti.exception.CustomerServiceException;
import com.lti.model.Customer;
import com.lti.model.Items;
import com.lti.model.Order;
import com.lti.service.CustomerService;
import com.lti.service.RetailerService;

import ch.qos.logback.core.net.SyslogOutputStream;

@RestController
@CrossOrigin
public class CustomerController {

	@Autowired
	private CustomerService customerServ;

	@PostMapping("/customerRegister")
	public Status register(@RequestBody Customer customer) {
		Status status = new Status();
		try {
			customerServ.register(customer);
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Registration Successful");
		} catch (CustomerServiceException e) {
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

	@PostMapping("/addItem")
	public Status addItemToCart(@RequestBody ItemDto itemDto) {
		List<Items> items = new ArrayList<>();
		Status status = new Status();
		Items item = new Items();
		item.setItemQuantity(1);
		items.add(item);
		int i = customerServ.addItem(items, itemDto.getCustomerId(), itemDto.getProductId());
		if (i > 0) {
			status.setMessage("Added to cart");
			status.setStatus(StatusType.SUCCESS);
		}

		return status;

	}
	
	@PostMapping("/changeItemQuantity")
	public Status changeQuantityInCart(@RequestBody ChangeCartQuantityDto changeCartQuantityDto) {
		Status status = new Status();
		int i = customerServ.changeQuantityInCart(changeCartQuantityDto.getCustomerId(), changeCartQuantityDto.getItemId(), changeCartQuantityDto.getItemQuantity());
		if (i > 0) {
			status.setMessage("Revised Quantity");
			status.setStatus(StatusType.SUCCESS);
		}
		else {
			status.setMessage(" not Revised Quantity");
			status.setStatus(StatusType.FAILURE);
		}
		System.out.println(i);
		return status;
	}

	@PostMapping("/placeCustomerOrder")
	public Status placeCustomerOrder(@RequestBody PlaceOrderDto placeOrder) {
		Status status = new Status();
		Order order = new Order();
		order.setOrderDate(LocalDate.now());
		int i = customerServ.placeOrderforCustomer(order, placeOrder.getCustomerId());
		if (i > 0) {
			status.setMessage("Order Placed");
			status.setStatus(StatusType.SUCCESS);
		}
		else {
			status.setMessage("Order Not placed");
			status.setStatus(StatusType.FAILURE);
		}
		return status;
	}
}
