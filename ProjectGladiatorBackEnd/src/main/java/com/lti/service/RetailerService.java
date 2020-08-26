package com.lti.service;

import com.lti.model.Retailer;

public interface RetailerService {

	Retailer loginRetailer(String retailerEmail, String retailerPassword);

	void register(Retailer retailer);

	int updateRetailerPassword(int retailerId, String retailerPassword);

	Retailer displayRetailerDetails(int retailerId);
}
