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

	List<Product> searchProduct(String searchValue);

	int addProduct(Product product, int retailerId);

	int updateProductImage(int productId, String imagePath);

	List<Product> productSubCategoryByCategory(String productCategory);
	
	 List<Product> viewProductBySubCategory(String productSubCategory);

}