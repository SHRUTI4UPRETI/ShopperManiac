package com.lti.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.ItemIdDto;
import com.lti.dto.ItemQuantityDto;
import com.lti.dto.ProductAddDto;
import com.lti.dto.ProductCategoryDto;
import com.lti.dto.ProductDto;
import com.lti.dto.ProductIdDto;
import com.lti.dto.ProductImageUploadDto;
import com.lti.dto.ProductSubCategories;
import com.lti.dto.SearchProductDto;
import com.lti.dto.SpecificProductDto;

import com.lti.dto.SubCategoryDto;

import com.lti.dto.Status;
import com.lti.dto.Status.StatusType;
import com.lti.model.Customer;

import com.lti.model.Product;
import com.lti.model.Retailer;
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

	@PostMapping("/viewProductSubCategory")
	public List<ProductSubCategories> viewProductSubCategories(@RequestBody ProductCategoryDto productCategory) {

		List<String> subCat = productServ.productSubCategoryByCategory(productCategory.getProductCategory());

		List<ProductSubCategories> returnList = new ArrayList<ProductSubCategories>();

		for (String s : subCat) {
			ProductSubCategories productSubCategories = new ProductSubCategories();
			productSubCategories.setProductSubCategories(s);
			returnList.add(productSubCategories);
		}

		return returnList;
	}

	@PostMapping("/checkStock")
	public ItemQuantityDto checkStock(@RequestBody ItemIdDto itemId) {

		ItemQuantityDto stockQuantity = new ItemQuantityDto();

		stockQuantity.setItemQuantity(productServ.checkStockQuantity(itemId.getItemId()));

		return stockQuantity;
	}

	@PostMapping("/searchProduct")
	public List<ProductDto> searchProduct(@RequestBody SearchProductDto searchValue) {
		List<Product> products = productServ.searchProduct(searchValue.getSearchValue());

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
	 

	@PostMapping("/addProduct")
	public ProductIdDto addProduct(@RequestBody ProductAddDto productAddDto) {
		Product product = new Product();
		ProductIdDto productIdDto = new ProductIdDto();
		product.setProductName(productAddDto.getProductName());
		product.setProductBrandName(productAddDto.getProductBrandName());
		product.setProductCategory(productAddDto.getProductCategory());
		product.setProductPrice(productAddDto.getProductPrice());
		product.setProductDescription(productAddDto.getProductDescription());
		product.setProductSubCategory(productAddDto.getProductSubCategory());
		product.setProductQuantity(productAddDto.getProductQuantity());
		product.setProductApproved(true); // by default false
		int pid = productServ.addProduct(product, productAddDto.getRetailerId());
		productIdDto.setProductId(pid);
		return productIdDto;
	}

	@PostMapping("/productImageUpload")
	public Status upload(ProductImageUploadDto productImageUploadDto) {
		// String imageUploadLocation = "D:/My Study Material/LTI/Virtual
		// Training/Project Gladiator/Online Shopping Web App/Front end
		// Angular/JVSD-OnlineShopping-Angular/src/assets/"; //jai path
		String imageUploadLocation = "D:/Docs/LTI/VT/Project Gladiator/JVSD-OnlineShopping-Angular/OnlineShopping/src/assets/"; // Vishal
																																// Path
		// String
		// imageUploadLocation="D:/angular846/JVSD-OnlineShopping-Angular/OnlineShopping/src/assets/"
		// //divyansh path
		// String
		// imageUploadLocation="E:/ProjectGladiator/JVSD-OnlineShopping-Angular/OnlineShopping/src/assets/"
		// //Shruti path
		String fileName = productImageUploadDto.getProductImage().getOriginalFilename();
		String targetFile = imageUploadLocation + fileName;
		try {
			FileCopyUtils.copy(productImageUploadDto.getProductImage().getInputStream(),
					new FileOutputStream(targetFile));
		} catch (IOException e) {
			e.printStackTrace();
			Status status = new Status();
			status.setStatus(StatusType.FAILURE);
			status.setMessage(e.getMessage());
			return status;
		}

		int i = productServ.updateProductImage(productImageUploadDto.getProductId(), fileName);
		Status status = new Status();
		if (i > 0) {

			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Uploaded!");

		} else {
			status.setStatus(StatusType.FAILURE);
			status.setMessage("Not Uploaded");
		}
		return status;

	}

}
