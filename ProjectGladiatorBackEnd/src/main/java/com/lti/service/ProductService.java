package com.lti.service;

import java.util.List;

import com.lti.dto.ProductDto;
import com.lti.model.Product;

public interface ProductService {

	List<Product> latestFiveProducts();

	List<Product> viewAllProducts();

	Product viewSpecificProduct(int productId);
	
	List<Product> viewProductByCategory(String productCategory);
	
	public int checkStockQuantity(int itemId);
	
	public List<Product> searchProduct(String searchValue);

	int addProduct(Product product, int retailerId);
	
    int updateProductImage(int productId, String imagePath);

	List<String> productSubCategoryByCategory(String productCategory);
	
    List<Product> viewProductBySubCategory(String productSubCategory);
    
    public List<Product> lowToHigh(String productCategory);
    
    public List<Product> highToLow(String productCategory);
}
