package com.lti.repository;

import java.util.List;

import com.lti.dto.ProductDto;
import com.lti.model.Product;

public interface ProductRepo {

	List<Product> latestFiveProducts();

	List<Product> viewAllProducts();
	
	Product viewSpecificProduct(int productId);
	
	List<Product> viewProductByCategory(String productCategory);
	
	int checkStockQuantity(int itemId);
	
	List<Product>searchProduct(String searchValue);

	List<String> viewProductSubCategoryByCategory(String productCategory);

}