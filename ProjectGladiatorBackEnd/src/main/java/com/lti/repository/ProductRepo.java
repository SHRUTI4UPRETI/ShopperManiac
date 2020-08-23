package com.lti.repository;

import java.util.List;

import com.lti.dto.ProductDto;
import com.lti.model.Product;

public interface ProductRepo {

	List<Product> latestFiveProducts();

	List<Product> viewAllProducts();

}