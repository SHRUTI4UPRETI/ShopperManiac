package com.lti.repository;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.lti.model.Cart;
import com.lti.model.Customer;
import com.lti.model.Items;
import com.lti.model.Order;

@Repository
public class CustomerRepoImpl implements CustomerRepository {

	@PersistenceContext
	EntityManager em;


	@Override
	public int addNewCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int isValidCustomer(String customerEmail, String customerPassword) {
		String sql = "select cust from Customer cust";
		Query qry = em.createQuery(sql);

		List<Customer> users = qry.getResultList();

		for (Customer u : users) {
			if (u.getCustomerEmail().equals(customerEmail) && customerPassword.equals(u.getCustomerPassword()))
				return u.getCustomerId();
		}
		return 0;
	}

	@Override
	public int addCart(List<Cart> carts, int customerId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addItem(List<Items> items, int customerId, int productId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int placeOrderforCustomer(Order order, int customerId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Items> displayProductByOrderId(int orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int changeItemQuantity(int itemQuantity, int productId, int customerId) {
		// TODO Auto-generated method stub
		return 0;
	}

	

	@Override
	public Customer findCustomerbyCustomerId(int customerId) {
		Customer customer=em.find(Customer.class, customerId);
		return customer;
	}

}
