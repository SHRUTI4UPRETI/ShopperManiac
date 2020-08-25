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
	@Transactional
	public int updateCustomerPassword(int customerId, String customerPassword) {
		
		Customer customer = em.find(Customer.class, customerId);
		customer.setCustomerPassword(customerPassword);
		em.merge(customer);
		return 1;
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
	public int addCart(List<Cart> carts, int customerId) {
		Customer customer = em.find(Customer.class, customerId);
		customer.setCart(carts);

		for (Cart c : carts) {
			c.setCustomer(customer);
		}

		em.merge(customer);
		return 1;
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
		item.setItemStock(product.getProductQuantity());
		item.setItemTotalPrice(product.getProductPrice() * item.getItemQuantity());

		item.setProduct(product);

		item.setCart(cart);

		cart.setCartQuantity(cart.getCartQuantity() + item.getItemQuantity());
		cart.setItem(items);
		em.merge(cart);
		return 1;
	}

	@Transactional
	public int setCartStatusInactive(int cartId) {
		Cart cart = em.find(Cart.class, cartId);

		cart.setCartStatus(false);
		em.merge(cart);

		return 1;
	}

	@Override
	@Transactional
	public String placeOrderforCustomer(Order order, int customerId) {
		Customer customer = em.find(Customer.class, customerId);
		
		String sql1 = "select c from Cart c where c.cartStatus=:status and c.customer.customerId=:custId";
        
		TypedQuery<Cart> query = em.createQuery(sql1, Cart.class);
		query.setParameter("custId", customerId);
		query.setParameter("status", true);

		List<Cart> carts1 = query.getResultList();

		Cart cart = carts1.get(0);
		
		int cartId = cart.getCartId();

		String sql = "select it from Items it where it.cart.cartId=:cartId";

		Query qry = em.createQuery(sql);
		qry.setParameter("cartId", cartId);

		List<Items> items = qry.getResultList();

		order.setOrderTotalPrice(0);

		for (Items i : items) {
			order.setOrderTotalPrice(order.getOrderTotalPrice() + i.getItemTotalPrice());
			int productId = i.getProduct().getProductId();
			Product product = em.find(Product.class, productId);
			int qty = product.getProductQuantity();
			System.out.println(qty);
			System.out.println(i.getItemQuantity());
			product.setProductQuantity(qty - i.getItemQuantity());
			em.merge(product);
		}

		int cid = cart.getCustomer().getCustomerId();
		// cart.setOrder(order);
		order.setCart(cart);
		order.setCustomer(customer);
		
		Order od = em.merge(order);

		int status = setCartStatusInactive(cartId);
		if (status > 0)
			System.out.println("Cart status set inactive");

		List<Cart> carts = new ArrayList<Cart>();

		Cart cartNew = new Cart();
		cartNew.setCartQuantity(0);
		cartNew.setCartStatus(true);
		carts.add(cartNew);
		int newCart = addCart(carts, cid);

		if (newCart > 0)
			System.out.println("New Active cart created");

		return customer.getCustomerEmail();
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

	@Override
	@Transactional
	public int changeQuantityInCart(int customerId, int itemId, int itemQuantity) {
		System.out.println("hello2");
		String sql = "select c from Cart c where c.customer.customerId=:custId and c.cartStatus=1";
		TypedQuery<Cart> query = em.createQuery(sql, Cart.class);
		query.setParameter("custId", customerId);
		Cart cart = query.getSingleResult();
		int cartId = cart.getCartId();

		List<Items> items = new ArrayList<Items>();

		/*
		 * String sql1 =
		 * "select i from Items i where i.cart.cartId=:cartId and i.product.productId=:pId"
		 * ; TypedQuery<Items> query1 = em.createQuery(sql1, Items.class);
		 * query1.setParameter("cartId", cartId); query1.setParameter("pId", productId);
		 */
		Items item = em.find(Items.class, itemId);
		cart.setCartQuantity(cart.getCartQuantity() - item.getItemQuantity() + itemQuantity);
		item.setItemQuantity(itemQuantity);
		item.setItemTotalPrice(item.getItemPrice() * itemQuantity);
		Items item1 = em.merge(item);

		items.add(item1);

		cart.setItem(items);
		em.merge(cart);

		System.out.println(item1);
		return 1;
	}

	@Override
	public List<Items> viewItemsInCart(int customerId) {

		String sql = "select c from Cart c where c.customer.customerId=:custId and c.cartStatus=1";
		TypedQuery<Cart> query = em.createQuery(sql, Cart.class);
		query.setParameter("custId", customerId);
		Cart cart = query.getSingleResult();
		int cartId = cart.getCartId();

		String sql1 = "select items from Items items where items.cart.cartId=:cartId";
		TypedQuery<Items> qry = em.createQuery(sql1, Items.class);
		qry.setParameter("cartId", cartId);

		List<Items> itemList = qry.getResultList();
		return itemList;
	}
	
	@Override
	public List<Order> displayOrderForCustomer(int customerId){
		String sql="select od from Order od where od.customer.customerId=:custId";
		TypedQuery<Order> query = em.createQuery(sql, Order.class);
		query.setParameter("custId", customerId);
		List<Order> orders = query.getResultList();
		
		return orders;
	}
	
	@Override
	public List<Items> displayProductByOrderId(int orderId) {
		// String sql = "select ti from Items ti where ti.cart.cartId=(select
		// tc.cartId from Cart tc where tc.customer.customerId=:cid)";
		// String sql="select it from Items it where it.cart.cartId=(select
		// od.cart.cartId from Order od where od.orderId=:id)";
		// String sql="select od,od.customer.customerId from Order od where
		// od.orderId=:id";
		/*
		 * String
		 * sql="select od, ct, it, pd from Order od join Cart ct on od.cart.cartId=ct.cartId"
		 * +
		 * " join Items it on ct.cartId=it.cart.cartId join Product pd on it.product.productId=pd.productId "
		 * + "where orderId=:id";
		 */
		// sql = "select ti, ti.product from Items ti join ti.product p on
		// ti.product.productId=20202 where ti.cart.cartId=(select tc.cartId
		// from Cart tc where tc.customer.customerId=:cid)";

		Order order = em.find(Order.class, orderId);
		int cartId = order.getCart().getCartId();

		String sql = "select it from Items it where it.cart.cartId=:cartId";
		TypedQuery<Items> query = em.createQuery(sql, Items.class);
		query.setParameter("cartId", cartId);

		List<Items> resultList = query.getResultList();
		return resultList;
	}

}
