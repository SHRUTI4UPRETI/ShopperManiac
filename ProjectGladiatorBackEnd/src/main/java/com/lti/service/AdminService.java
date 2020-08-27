package com.lti.service;

import java.util.List;

import com.lti.model.Admin;
import com.lti.model.Product;
import com.lti.model.Retailer;

public interface AdminService {

	Admin loginAdmin(String email, String password);

	List<Product> showProductsToApprove();

	int productApprove(int productId);

	List<Product> approvedProducts();

	List<Retailer> viewAllRetailers();
}
