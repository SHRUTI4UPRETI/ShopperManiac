package com.lti.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.lti.dto.ProductDto;
import com.lti.dto.SubCategoryDto;
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
		Product p = productRepo.viewSpecificProduct(productId);
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

	@Override
	public int checkStockQuantity(int itemId) {
		return productRepo.checkStockQuantity(itemId);
	}

	@Override
	public List<Product> searchProduct(String searchValue) {

		List<Product> products = new ArrayList<>();
		products = productRepo.searchProduct(searchValue);
		for (Product p : products) {
			String imageName = p.getProductImagePath();
			imageName = "assets/" + imageName + ".jpg";
			p.setProductImagePath(imageName);
		}

		return products;
	}

	@Override
	public int addProduct(Product product, int retailerId) {
		return productRepo.addProduct(product, retailerId);

	}

	@Override
	public int updateProductImage(int productId, String imagePath) {
		return productRepo.updateProductImage(productId, imagePath);
	}

	@Override
	public List<String> productSubCategoryByCategory(String productCategory) {
		List<Product> products = productRepo.productSubCategoryByCategory(productCategory);

		Set<String> productCatSet = new HashSet<>();

		for (Product p : products) {
			productCatSet.add(p.getProductSubCategory());
		}
		List<String> productSubCategories = new ArrayList<String>();

		for (String s : productCatSet) {
			productSubCategories.add(s);
		}
		return productSubCategories;
	}

	@Override
	public List<Product> viewProductBySubCategory(String productSubCategory) {

		List<Product> products = new ArrayList<>();
		products = productRepo.viewProductBySubCategory(productSubCategory);
		for (Product p : products) {
			String imageName = p.getProductImagePath();
			imageName = "assets/" + imageName + ".jpg";
			p.setProductImagePath(imageName);
		}
		//System.out.println(productSubCategory);
		return products;
	}

	@Override
	public List<Product> lowToHigh(String productCategory) {
		
		List<Product> products = new ArrayList<>();
		products = productRepo.lowToHigh(productCategory);
		for (Product p : products) {
			String imageName = p.getProductImagePath();
			imageName = "assets/" + imageName + ".jpg";
			p.setProductImagePath(imageName);
		}
		//System.out.println(productSubCategory);
		return products;
	}

	@Override
	public List<Product> highToLow(String productCategory) {
		List<Product> products = new ArrayList<>();
		products = productRepo.highToLow(productCategory);
		for (Product p : products) {
			String imageName = p.getProductImagePath();
			imageName = "assets/" + imageName + ".jpg";
			p.setProductImagePath(imageName);
		}
		//System.out.println(productSubCategory);
		return products;
	}

}
