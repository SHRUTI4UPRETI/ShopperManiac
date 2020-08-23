package com.lti.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "tbl_item")
public class Items {
	
	@Id
	@SequenceGenerator(name = "seq_item1", initialValue = 30001, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_item1")
	private int itemId;
	
	@Column
	private int itemQuantity;
	@Column
	private int itemTotalPrice;
	@Column
	private String itemName;
	@Column 
	private int itemPrice;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="productId")
	private Product product;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="cartId")
	private Cart cart;

	
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public int getItemTotalPrice() {
		return itemTotalPrice;
	}

	public void setItemTotalPrice(int itemTotalPrice) {
		this.itemTotalPrice = itemTotalPrice;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	@Override
	public String toString() {
		return "Items [itemId=" + itemId + ", itemQuantity=" + itemQuantity + ", itemTotalPrice=" + itemTotalPrice
				+ ", itemName=" + itemName + ", itemPrice=" + itemPrice + "]";
	}
	
	
	

}
