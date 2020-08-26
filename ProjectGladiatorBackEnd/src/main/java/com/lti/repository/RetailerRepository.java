package com.lti.repository;

import java.util.List;

import com.lti.model.Product;
import com.lti.model.Retailer;

public interface RetailerRepository {

	boolean isRetailerPresent(String retailerEmail);

	Retailer findRetailerById(int retailerId);

	int findRetailerbyEmailPassword(String retailerEmail, String retailerPassword);

	void addNewRetailer(Retailer retailer);

	int updateRetailerPassword(int retailerId, String retailerPassword);

	List<Product> viewProductsOfRetailer(int retailerId);

	int changeProductStockInInventory(int productId, int productQuantity);
	

}
