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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="tbl_product")
public class Product {

	@Id
	@SequenceGenerator(name = "seq_product1", initialValue = 20200, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_product1")
	private int productId;
	
	@Column
	private String productName;
	@Column
	private String productBrandName;
	@Column
	private int productPrice;
	@Column
	private int productQuantity;
	@Column
	private String productCategory;
	@Column
	private String productSubCategory;
	@Column
	private String productDescription;
	@Column
	private String productImagePath;
	@Column
	private Boolean isProductApproved;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="retailerId")
	private Retailer retailer;
	
	@OneToMany(mappedBy="product", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Items> item;

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

	public String getProductBrandName() {
		return productBrandName;
	}

	public void setProductBrandName(String productBrandName) {
		this.productBrandName = productBrandName;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getProductSubCategory() {
		return productSubCategory;
	}

	public void setProductSubCategory(String productSubCategory) {
		this.productSubCategory = productSubCategory;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductImagePath() {
		return productImagePath;
	}

	public void setProductImagePath(String productImagePath) {
		this.productImagePath = productImagePath;
	}

	public Boolean isProductApproved() {
		return isProductApproved;
	}

	public void setProductApproved(Boolean isProductApproved) {
		this.isProductApproved = isProductApproved;
	}

	public Retailer getRetailer() {
		return retailer;
	}

	public void setRetailer(Retailer retailer) {
		this.retailer = retailer;
	}

	public List<Items> getItem() {
		return item;
	}

	public void setItem(List<Items> item) {
		this.item = item;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productBrandName="
				+ productBrandName + ", productPrice=" + productPrice + ", productQuantity=" + productQuantity
				+ ", productCategory=" + productCategory + ", productSubCategory=" + productSubCategory
				+ ", productDescription=" + productDescription + ", productImagePath=" + productImagePath
				+ ", isProductApproved=" + isProductApproved + "]";
	}
	
	
	
}
