package com.lti.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "tbl_retailer1")
public class Retailer {

	@Id
	@SequenceGenerator(name = "seq_retailer1", initialValue = 60001, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_retailer1")
	private int retailerId;

	@Column
	private String retailerName;
	
	@Column
	private String retailerEmail;
	
	@Column
	private String retailerPassword;
	
	@Column
	private int retailerMobile;

	@OneToMany(mappedBy = "retailer", cascade = CascadeType.ALL)
	private List<Product> products;

	public int getRetailerId() {
		return retailerId;
	}

	public void setRetailerId(int retailerId) {
		this.retailerId = retailerId;
	}

	public String getRetailerName() {
		return retailerName;
	}

	public void setRetailerName(String retailerName) {
		this.retailerName = retailerName;
	}

	public String getRetailerEmail() {
		return retailerEmail;
	}

	public void setRetailerEmail(String retailerEmail) {
		this.retailerEmail = retailerEmail;
	}

	public String getRetailerPassword() {
		return retailerPassword;
	}

	public void setRetailerPassword(String retailerPassword) {
		this.retailerPassword = retailerPassword;
	}

	public int getRetailerMobile() {
		return retailerMobile;
	}

	public void setRetailerMobile(int retailerMobile) {
		this.retailerMobile = retailerMobile;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "Retailer [retailerId=" + retailerId + ", retailerName=" + retailerName + ", retailerEmail="
				+ retailerEmail + ", retailerPassword=" + retailerPassword + ", retailerMobile=" + retailerMobile + "]";
	}

	
	
}
