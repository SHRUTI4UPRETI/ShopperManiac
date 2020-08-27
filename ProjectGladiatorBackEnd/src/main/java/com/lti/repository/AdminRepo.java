package com.lti.repository;

import java.util.List;

import com.lti.model.Admin;
import com.lti.model.Product;
import com.lti.model.Retailer;

public interface AdminRepo {

	String isValidAdmin(String adminEmail, String adminPassword);

	Admin findAdminByEmail(String adminEmail);

	List<Product> showProductsToApprove();

	int productApprove(int productId);

	List<Product> approvedProducts();

	List<Retailer> viewAllRetailers();
}
