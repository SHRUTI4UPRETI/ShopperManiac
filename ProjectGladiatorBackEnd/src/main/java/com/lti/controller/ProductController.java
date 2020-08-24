package com.lti.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.ItemIdDto;
import com.lti.dto.ItemQuantityDto;
import com.lti.dto.ProductCategoryDto;
import com.lti.dto.ProductDto;
import com.lti.dto.ProductIdDto;
import com.lti.dto.SpecificProductDto;
import com.lti.model.Product;
import com.lti.service.ProductService;

@RestController
@CrossOrigin
public class ProductController {

	@Autowired
	private ProductService productServ;

	// customer.setName(customerDto.getName())
	/*
	 * productD.setProductName(p.getProductName());
	 * productD.setProductBrandName(p.getProductBrandName());
	 * productD.setProductCategory(p.getProductCategory()); productD.set
	 */
	@GetMapping("/viewLatestArrivals")
	public List<ProductDto> latestFiveProducts() {
		List<Product> products = productServ.latestFiveProducts();
		List<ProductDto> productDto = new ArrayList<>();
		for (Product p : products) {
			ProductDto productD = new ProductDto();
			productD.setProductName(p.getProductName());
			productD.setProductId(p.getProductId());
			productD.setProductImagePath(p.getProductImagePath());
			productDto.add(productD);
		}
		return productDto;

	}

	@PostMapping("/viewSpecificProduct")
	public SpecificProductDto viewSpecificProduct(@RequestBody ProductIdDto productId) {

		Product product = productServ.viewSpecificProduct(productId.getProductId());

		SpecificProductDto specificProduct = new SpecificProductDto();

		specificProduct.setProductName(product.getProductName());
		specificProduct.setProductBrandName(product.getProductBrandName());
		specificProduct.setProductCategory(product.getProductCategory());
		specificProduct.setProductPrice(product.getProductPrice());
		specificProduct.setProductDescription(product.getProductDescription());
		specificProduct.setProductImagePath(product.getProductImagePath());
		specificProduct.setProductSubCategory(product.getProductSubCategory());
		specificProduct.setProductId(product.getProductId());
		return specificProduct;

	}

	@PostMapping("/viewProductCategory")
	public List<SpecificProductDto> viewProductByCategory(@RequestBody ProductCategoryDto productCategory) {

		List<Product> products = productServ.viewProductByCategory(productCategory.getProductCategory());

		List<SpecificProductDto> Specificproducts = new ArrayList<>();
		for (Product p : products) {
			SpecificProductDto specificProduct = new SpecificProductDto();
			specificProduct.setProductName(p.getProductName());
			specificProduct.setProductBrandName(p.getProductBrandName());
			specificProduct.setProductCategory(p.getProductCategory());
			specificProduct.setProductPrice(p.getProductPrice());
			specificProduct.setProductDescription(p.getProductDescription());
			specificProduct.setProductImagePath(p.getProductImagePath());
			specificProduct.setProductSubCategory(p.getProductSubCategory());
			specificProduct.setProductId(p.getProductId());
			Specificproducts.add(specificProduct);
		}

		return Specificproducts;
	}

	@PostMapping("/checkStock")
	public ItemQuantityDto checkStock(@RequestBody ItemIdDto itemId) {
		
		ItemQuantityDto stockQuantity = new ItemQuantityDto();
		
		stockQuantity.setItemQuantity(productServ.checkStockQuantity(itemId.getItemId()));
		
		return stockQuantity;
	}

}
