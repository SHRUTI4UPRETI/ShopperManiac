package com.lti.service;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.exception.CustomerServiceException;
import com.lti.model.Customer;
import com.lti.model.Items;
import com.lti.model.Order;
import com.lti.model.Product;
import com.lti.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepo;
	
	@Override
	public void register(Customer customer) {
		if (!customerRepo.isCustomerPresent(customer.getCustomerEmail())) {
			String pass = customer.getCustomerPassword();
			String encodedPassword = Base64.getEncoder().encodeToString(pass.getBytes()); 
			customer.setCustomerPassword(encodedPassword);
			customerRepo.addNewCustomer(customer);
		}
			
		else
			throw new CustomerServiceException("Customer Already Registered");
	}

	@Override
	public Customer loginCustomer(String customerEmail, String customerPassword) {

		System.out.println(customerPassword);
		/*
		 * byte[] actualByte = Base64.getDecoder() .decode(customerPassword); String
		 * decodedPassword = new String(actualByte);
		 * System.out.println(decodedPassword);
		 */
		String encodedPassword = Base64.getEncoder().encodeToString(customerPassword.getBytes());
		int customerId = customerRepo.isValidCustomer(customerEmail, encodedPassword);
        
		if (customerId > 0) {
			Customer customer = customerRepo.findCustomerbyCustomerId(customerId);
			return customer;
		}
		return null;

	}

	@Override
	public int addItem(List<Items> items, int customerId, int productId) {
		return customerRepo.addItem(items, customerId, productId);
	}
	
	@Override
	public int changeQuantityInCart(int customerId, int itemId, int itemQuantity) {
		//System.out.println("hello");
		return customerRepo.changeQuantityInCart( customerId, itemId,itemQuantity);
	}
	
	@Override
	public String placeOrderforCustomer(Order order, int customerId) {
		return customerRepo.placeOrderforCustomer(order, customerId);
	}

	@Override
	public List<Items> viewItemsInCart(int customerId) {
		 List<Items> items=customerRepo.viewItemsInCart(customerId);
		 
		for (Items i : items) {
			String imageName = i.getItemImagePath();
			imageName = "assets/" + imageName + ".jpg";
			i.setItemImagePath(imageName);
		}
		return items;
	}
	@Override
	public List<Order> displayOrderForCustomer(int customerId){
		return customerRepo.displayOrderForCustomer(customerId);
	}
	
	@Override
	public List<Items> displayProductByOrderId(int orderId){
		return customerRepo.displayProductByOrderId(orderId);
	}
	
	@Override
	public Customer findCustomerbyCustomerId(int customerId) {
		return customerRepo.findCustomerbyCustomerId(customerId);
	}
}
