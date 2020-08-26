package com.lti.dto;

import javax.persistence.Column;

public class DisplayRetailerDto {


	private String retailerName;

	private String retailerEmail;
	
	private String retailerMobile;

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

	public String getRetailerMobile() {
		return retailerMobile;
	}

	public void setRetailerMobile(String retailerMobile) {
		this.retailerMobile = retailerMobile;
	}
	
	
}
