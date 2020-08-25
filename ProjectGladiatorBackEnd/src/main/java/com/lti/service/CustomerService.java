package com.lti.service;

import java.util.List;

import com.lti.exception.CustomerServiceException;
import com.lti.model.Customer;
import com.lti.model.Items;
import com.lti.model.Order;

public interface CustomerService {

	Customer loginCustomer(String email, String password);

	void register(Customer customer);
	
	public int addItem(List<Items> items, int customerId, int productId);
	public int changeQuantityInCart(int customerId, int itemId, int itemQuantity);

	public String placeOrderforCustomer(Order order, int customerId);

	public List<Items> viewItemsInCart(int customerId);

	List<Order> displayOrderForCustomer(int customerId);

	List<Items> displayProductByOrderId(int orderId);
	

	Customer displayCustomerDetails(int customerId);

	int updateCustomerPassword(int customerId, String customerPassword);

	
}
