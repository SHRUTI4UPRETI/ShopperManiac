package com.lti.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.ProductDto;
import com.lti.model.Product;
import com.lti.service.ProductService;

@RestController
@CrossOrigin
public class ProductController {

	@Autowired
	private ProductService productServ;
	//customer.setName(customerDto.getName())
	/*
	 * productD.setProductName(p.getProductName());
	 * productD.setProductBrandName(p.getProductBrandName());
	 * productD.setProductCategory(p.getProductCategory()); productD.set
	 */
	@GetMapping("/viewLatestArrivals")
	public List<ProductDto> latestFiveProducts(){
		List<Product> products =  productServ.latestFiveProducts();
		List<ProductDto> productDto = new ArrayList<>();
		for(Product p: products) {
			ProductDto productD = new ProductDto();
			productD.setProductName(p.getProductName());
			productD.setProductImagePath(p.getProductImagePath());
			productDto.add(productD);
			
		}
		return productDto;
	
		}
}
