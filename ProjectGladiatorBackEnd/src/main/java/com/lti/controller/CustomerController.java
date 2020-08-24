package com.lti.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.ChangeCartQuantityDto;
import com.lti.dto.ItemDto;
import com.lti.dto.ItemsInCartDto;
import com.lti.dto.LoginDto;
import com.lti.dto.PlaceOrderDto;
import com.lti.dto.SpecificProductDto;
import com.lti.dto.Status;
import com.lti.dto.Status.StatusType;
import com.lti.dto.loginStatus;
import com.lti.exception.CustomerServiceException;
import com.lti.model.Customer;
import com.lti.model.Items;
import com.lti.model.Order;
import com.lti.model.Product;
import com.lti.service.CustomerService;
import com.lti.service.RetailerService;

import ch.qos.logback.core.net.SyslogOutputStream;

@RestController
@CrossOrigin
public class CustomerController {

	  @Autowired
	  private MailSender mailSender;
	
	@Autowired
	private CustomerService customerServ;

	@PostMapping("/customerRegister")
	public Status register(@RequestBody Customer customer) {
		Status status = new Status();
		try {
			customerServ.register(customer);
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Registration Successful");
			

			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("ShoppingManiac4@outlook.com");
			message.setTo(customer.getCustomerEmail());       /* retailer.getRetailerEmail() */ 
			message.setSubject("Thank You for registration");
			message.setText("Thank you "+customer.getCustomerName()+" for registration /n Have a nice Day :)");
			mailSender.send(message);
			
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
		int i = customerServ.changeQuantityInCart(changeCartQuantityDto.getCustomerId(),
				changeCartQuantityDto.getItemId(), changeCartQuantityDto.getItemQuantity());
		if (i > 0) {
			status.setMessage("Revised Quantity");
			status.setStatus(StatusType.SUCCESS);
		} else {
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
		} else {
			status.setMessage("Order Not placed");
			status.setStatus(StatusType.FAILURE);
		}
		return status;
	}

	@PostMapping("/viewItemsInCart")
	public List<ItemsInCartDto> viewItemsInCart(@RequestBody PlaceOrderDto customerId) {
		List<Items> items = customerServ.viewItemsInCart(customerId.getCustomerId());

		List<ItemsInCartDto> returnItems = new ArrayList<>();

		for (Items i : items) {
			ItemsInCartDto returnItem = new ItemsInCartDto();
			returnItem.setItemId(i.getItemId());
			returnItem.setItemName(i.getItemName());
			returnItem.setItemStock(i.getItemStock());
			returnItem.setItemPrice(i.getItemPrice());
			returnItem.setItemQuantity(i.getItemQuantity());
			returnItem.setItemImagePath(i.getItemImagePath());
			returnItem.setItemTotalPrice(i.getItemTotalPrice());

			returnItems.add(returnItem);
		}

		return returnItems;
	}

}
