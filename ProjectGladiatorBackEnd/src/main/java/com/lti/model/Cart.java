package com.lti.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_cart1")
public class Cart {
	
	@Id
	@SequenceGenerator(name = "seq_cart1", initialValue = 40001, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cart1")
	private int cartId;
	
	@Column
	private int cartQuantity;   //item quantity total
	
	@Column
	private boolean cartStatus;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="customerId")
	private Customer customer;
	
	@OneToMany(mappedBy="cart", cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Items> item;
	
	@OneToOne(mappedBy="cart", cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	private Order order;

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getCartQuantity() {
		return cartQuantity;
	}

	public void setCartQuantity(int cartQuantity) {
		this.cartQuantity = cartQuantity;
	}

	public boolean isCartStatus() {
		return cartStatus;
	}

	public void setCartStatus(boolean cartStatus) {
		this.cartStatus = cartStatus;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Items> getItem() {
		return item;
	}

	public void setItem(List<Items> item) {
		this.item = item;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	 

}
