package com.lti.repository;

import com.lti.model.Retailer;

public interface RetailerRepository {
 
	void addNewRetailer(Retailer retailer);
	boolean isRetailerPresent(String retailerEmail);
}
