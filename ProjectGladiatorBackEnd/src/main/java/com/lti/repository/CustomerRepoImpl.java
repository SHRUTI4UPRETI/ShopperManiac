package com.lti.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.lti.model.Cart;
import com.lti.model.Customer;
import com.lti.model.Items;
import com.lti.model.Order;
import com.lti.model.Product;

@Repository
public class CustomerRepoImpl implements CustomerRepository {

	@PersistenceContext
	EntityManager em;

	@Override
	@Transactional
	public void addNewCustomer(Customer customer) {
		Customer customer1 = em.merge(customer);

	      int uId = customer1.getCustomerId(); 
	      List<Cart> carts = new ArrayList<Cart>();
		  
		  Cart cart = new Cart(); 
		  cart.setCartQuantity(0); 
		  cart.setCartStatus(true);
			carts.add(cart);
			addCart(carts, uId); 
			/* return uId; */
		 
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
	@Transactional
	public void addCart(List<Cart> carts, int customerId) {
		Customer customer = em.find(Customer.class, customerId);
		customer.setCart(carts);

		for (Cart c : carts) {
			c.setCustomer(customer);
		}

		em.merge(customer);
		
	}

	@Override
	@Transactional
	public int addItem(List<Items> items, int customerId, int productId) {

		String sql = "select c from Cart c where c.cartStatus=:status and c.customer.customerId=:custId";

		TypedQuery<Cart> query = em.createQuery(sql, Cart.class);
		query.setParameter("custId", customerId);
		query.setParameter("status", true);

		List<Cart> carts = query.getResultList();

		Cart cart = carts.get(0);

		// Cart cart = em.find(Cart.class, cartId);
		Items item = items.get(0);

		Product product = em.find(Product.class, productId);
		// System.out.println(product.getProductId());

		item.setItemName(product.getProductName());
		item.setItemPrice(product.getProductPrice());
		item.setItemImagePath(product.getProductImagePath());
		item.setItemTotalPrice(product.getProductPrice() * item.getItemQuantity());

		item.setProduct(product);

		item.setCart(cart);

		cart.setCartQuantity(cart.getCartQuantity() + item.getItemQuantity());
		cart.setItem(items);
		em.merge(cart);
		return 1;
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
		Customer customer = em.find(Customer.class, customerId);
		return customer;
	}

	@Override
	public boolean isCustomerPresent(String customerEmail) {
		return (Long) em.createQuery("select count(c.customerId) from Customer c where c.customerEmail =: email")
				.setParameter("email", customerEmail).getSingleResult() == 1 ? true : false;
	}

}
