package com.lti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.exception.CustomerServiceException;
import com.lti.model.Customer;
import com.lti.model.Items;
import com.lti.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepo;
	
	@Override
	public void register(Customer customer) {
		if (!customerRepo.isCustomerPresent(customer.getCustomerEmail()))
			customerRepo.addNewCustomer(customer);
		else
			throw new CustomerServiceException("Customer Already Registered");
	}

	@Override
	public Customer loginCustomer(String customerEmail, String customerPassword) {

		int customerId = customerRepo.isValidCustomer(customerEmail, customerPassword);

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

}
