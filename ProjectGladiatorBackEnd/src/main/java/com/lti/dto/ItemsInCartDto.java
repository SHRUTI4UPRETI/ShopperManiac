package com.lti.dto;

public class ItemsInCartDto {
	private int itemId;

	private int itemQuantity;

	private int itemTotalPrice;

	private String itemName;

	private int itemPrice;

	private String itemImagePath;

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

	public String getItemImagePath() {
		return itemImagePath;
	}

	public void setItemImagePath(String itemImagePath) {
		this.itemImagePath = itemImagePath;
	}

}
