package com.lti.service;

import java.util.List;

import com.lti.model.Product;
import com.lti.model.Retailer;

public interface RetailerService {

	Retailer loginRetailer(String retailerEmail, String retailerPassword);

	void register(Retailer retailer);

	int updateRetailerPassword(int retailerId, String retailerPassword);

	Retailer displayRetailerDetails(int retailerId);


}
