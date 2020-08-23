package com.lti.dto;

public class ProductDto {

	private int productId;
	/*
	 * private String productName;
	 * 
	 * private String productBrandName;
	 * 
	 * private int productPrice;
	 * 
	 * private int productQuantity;
	 * 
	 * private String productCategory;
	 * 
	 * private String productSubCategory;
	 * 
	 * private String productDescription;
	 * 
	 * private String productImagePath;
	 * 
	 * private Boolean isProductApproved;
	 */
	private String productImagePath;

	private String productName;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	/*
	 * public String getProductBrandName() { return productBrandName; }
	 * 
	 * public void setProductBrandName(String productBrandName) {
	 * this.productBrandName = productBrandName; }
	 * 
	 * public int getProductPrice() { return productPrice; }
	 * 
	 * public void setProductPrice(int productPrice) { this.productPrice =
	 * productPrice; }
	 * 
	 * public int getProductQuantity() { return productQuantity; }
	 * 
	 * public void setProductQuantity(int productQuantity) { this.productQuantity =
	 * productQuantity; }
	 * 
	 * public String getProductCategory() { return productCategory; }
	 * 
	 * public void setProductCategory(String productCategory) { this.productCategory
	 * = productCategory; }
	 * 
	 * public String getProductSubCategory() { return productSubCategory; }
	 * 
	 * public void setProductSubCategory(String productSubCategory) {
	 * this.productSubCategory = productSubCategory; }
	 * 
	 * public String getProductDescription() { return productDescription; }
	 * 
	 * public void setProductDescription(String productDescription) {
	 * this.productDescription = productDescription; }
	 * 
	 */public String getProductImagePath() {
		return productImagePath;
	}

	public void setProductImagePath(String productImagePath) {
		this.productImagePath = productImagePath;
	}

	/*
	 * public Boolean getIsProductApproved() { return isProductApproved; }
	 * 
	 * public void setIsProductApproved(Boolean isProductApproved) {
	 * this.isProductApproved = isProductApproved; }
	 */

}
