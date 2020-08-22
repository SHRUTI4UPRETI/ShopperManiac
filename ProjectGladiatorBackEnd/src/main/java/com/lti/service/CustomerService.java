package com.lti.service;

import com.lti.exception.CustomerServiceException;
import com.lti.model.Customer;

public interface CustomerService {

	Customer loginCustomer(String email, String password);

	void register(Customer customer);

}
