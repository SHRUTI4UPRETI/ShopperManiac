package com.lti.repository;

import java.util.List;

import com.lti.model.Cart;
import com.lti.model.Customer;
import com.lti.model.Items;
import com.lti.model.Order;

public interface CustomerRepository {

	public void addNewCustomer(Customer customer);

	int isValidCustomer(String customerEmail, String customerPassword);

	public int addCart(List<Cart> carts, int customerId);

	public Customer findCustomerbyCustomerId(int customerId);

	public int addItem(List<Items> items, int customerId, int productId);

	public String placeOrderforCustomer(Order order, int customerId);

	public List<Items> displayProductByOrderId(int orderId);

	public int changeItemQuantity(int itemQuantity, int productId, int customerId);

	boolean isCustomerPresent(String customerEmail);

	public int changeQuantityInCart(int customerId, int itemId, int itemQuantity);

	public List<Items> viewItemsInCart(int customerId);

	List<Order> displayOrderForCustomer(int customerId);

	int updateCustomerPassword(int customerId, String customerPassword);

	int removeItemFromCart(int customerId, int itemId);

	int isValidCustomerEmail(String customerEmail);

	int removeOrderOfCustomer(int orderId);

}
