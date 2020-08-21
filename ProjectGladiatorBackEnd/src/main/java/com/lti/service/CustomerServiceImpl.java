package com.lti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.model.Customer;
import com.lti.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepo;

	@Override
	public Customer loginCustomer(String customerEmail, String customerPassword) {

		int customerId = customerRepo.isValidCustomer(customerEmail, customerPassword);

		if (customerId > 0) {
			Customer customer = customerRepo.findCustomerbyCustomerId(customerId);
			return customer;
		}
		return null;

	}

}
