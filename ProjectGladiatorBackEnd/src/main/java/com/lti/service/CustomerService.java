package com.lti.service;

import java.util.List;

import com.lti.exception.CustomerServiceException;
import com.lti.model.Customer;
import com.lti.model.Items;

public interface CustomerService {

	Customer loginCustomer(String email, String password);

	void register(Customer customer);
	
	public int addItem(List<Items> items, int customerId, int productId);
	public int changeQuantityInCart(int customerId, int itemId, int itemQuantity);

}
