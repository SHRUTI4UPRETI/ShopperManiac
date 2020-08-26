package com.lti.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.AdminProductsDto;
import com.lti.dto.LoginDto;
import com.lti.dto.LoginStatusAdmin;
import com.lti.dto.ProductDto;
import com.lti.dto.ProductIdDto;
import com.lti.dto.Status;
import com.lti.dto.Status.StatusType;
import com.lti.dto.loginStatus;
import com.lti.model.Admin;
import com.lti.model.Product;
import com.lti.service.AdminService;

@RestController
@CrossOrigin
public class AdminController {

	@Autowired
	private AdminService adminServ;

	@PostMapping("/adminLogin")
	public LoginStatusAdmin loginAdmin(@RequestBody LoginDto loginDto) {
		LoginStatusAdmin loginStatusAdmin = new LoginStatusAdmin();

		Admin admin = adminServ.loginAdmin(loginDto.getEmail(), loginDto.getPassword());

		if (admin != null) {

			loginStatusAdmin.setAdminEmail(admin.getAdminEmail());
			loginStatusAdmin.setMessage("Admin Login Successful");
			loginStatusAdmin.setStatus(StatusType.SUCCESS);
		}

		else {
			loginStatusAdmin.setMessage("Error Logging in Admin");
			loginStatusAdmin.setStatus(StatusType.FAILURE);
		}
		return loginStatusAdmin;
	}

	@GetMapping("/showProductsToApprove")
	public List<AdminProductsDto> showProductsToApprove() {

		List<Product> products = adminServ.showProductsToApprove();
		List<AdminProductsDto> adminDto = new ArrayList<>();
		for (Product p : products) {
			AdminProductsDto adminD = new AdminProductsDto();
			adminD.setProductName(p.getProductName());
			adminD.setProductId(p.getProductId());
			adminD.setProductImagePath(p.getProductImagePath());
			adminD.setProductBrandName(p.getProductBrandName());
			adminDto.add(adminD);
		}
		return adminDto;

	}

	@PostMapping("/approveProduct")
	public Status approveProduct(@RequestBody ProductIdDto productIdDto) {
		Status status = new Status();

		int i = adminServ.productApprove(productIdDto.getProductId());

		if (i > 0) {
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Product Approved");
		}

		else {
			status.setStatus(StatusType.FAILURE);
			status.setMessage("Product Not Approved");
		}
		return status;
	}

	@GetMapping("/showApprovedProducts")
	public List<AdminProductsDto> approvedProducts() {

		List<Product> products = adminServ.approvedProducts();
		List<AdminProductsDto> adminDto = new ArrayList<>();
		for (Product p : products) {
			AdminProductsDto adminD = new AdminProductsDto();
			adminD.setProductName(p.getProductName());
			adminD.setProductId(p.getProductId());
			adminD.setProductImagePath(p.getProductImagePath());
			adminD.setProductBrandName(p.getProductBrandName());
			adminDto.add(adminD);
		}
		return adminDto;

	}

}
