package com.lti.repository;

import com.lti.model.Retailer;

public interface RetailerRepository {
	
	boolean isRetailerPresent(String retailerEmail);
	
	Retailer findRetailerById(int retailerId);
	
	int findRetailerbyEmailPassword(String retailerEmail, String retailerPassword);

}
