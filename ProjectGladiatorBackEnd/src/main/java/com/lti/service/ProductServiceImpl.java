package com.lti.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.lti.dto.ProductDto;
import com.lti.model.Product;
import com.lti.repository.ProductRepo;

@Service

public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepo productRepo;

	@Override
	public List<Product> latestFiveProducts() {

		List<Product> products = new ArrayList<>();
		products = productRepo.latestFiveProducts();
		for (Product p : products) {
			String imageName = p.getProductImagePath();
			imageName = "assets/" + imageName + ".jpg";
			p.setProductImagePath(imageName);
		}

		return products;

	}

	@Override
	public List<Product> viewAllProducts() {

		return productRepo.viewAllProducts();

	}

	@Override
	public Product viewSpecificProduct(int productId) {
		Product p=productRepo.viewSpecificProduct(productId);
		String imageName = p.getProductImagePath();
		imageName = "assets/" + imageName + ".jpg";
		p.setProductImagePath(imageName);
		return p;

	}

	@Override
	public List<Product> viewProductByCategory(String productCategory) {

		List<Product> products = new ArrayList<>();
		products = productRepo.viewProductByCategory(productCategory);
		for (Product p : products) {
			String imageName = p.getProductImagePath();
			imageName = "assets/" + imageName + ".jpg";
			p.setProductImagePath(imageName);
		}

		return products;
	}
}
