package com.lti.repository;

import java.util.List;

import com.lti.model.Cart;
import com.lti.model.Customer;
import com.lti.model.Items;
import com.lti.model.Order;

public interface CustomerRepository {

	public void addNewCustomer(Customer customer);

	boolean updateCustomer(Customer customer);
		
	int isValidCustomer(String customerEmail, String customerPassword);

	public void addCart(List<Cart> carts, int customerId);
	
	public Customer findCustomerbyCustomerId(int customerId);

	public int addItem(List<Items> items, int customerId, int productId);

	public int placeOrderforCustomer(Order order,int customerId);

	public List<Items> displayProductByOrderId(int orderId);

	public int changeItemQuantity(int itemQuantity, int productId, int customerId);

	boolean isCustomerPresent(String customerEmail);
	
	
	
}
