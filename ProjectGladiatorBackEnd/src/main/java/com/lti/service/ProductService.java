package com.lti.service;

import java.util.List;

import com.lti.dto.ProductDto;
import com.lti.model.Product;

public interface ProductService {

	List<Product> latestFiveProducts();

	List<Product> viewAllProducts();

	Product viewSpecificProduct(int productId);
	
	List<Product> viewProductByCategory(String productCategory);
}
