package com.lti.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.model.Admin;
import com.lti.model.Product;
import com.lti.repository.AdminRepo;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepo adminRepo;

	@Override
	public Admin loginAdmin(String email, String password) {

		String adminEmail = adminRepo.isValidAdmin(email, password);
		if (adminEmail != null) {
			return adminRepo.findAdminByEmail(adminEmail);
		}

		return null;
	}

	@Override
	public List<Product> showProductsToApprove() {

		List<Product> products = new ArrayList<>();
		products = adminRepo.showProductsToApprove();
		for (Product p : products) {
			String imageName = p.getProductImagePath();
			imageName = "assets/" + imageName + ".jpg";
			p.setProductImagePath(imageName);
		}
		return products;
	}

	@Override
	public List<Product> approvedProducts() {

		List<Product> products = new ArrayList<>();
		products = adminRepo.approvedProducts();
		for (Product p : products) {
			String imageName = p.getProductImagePath();
			imageName = "assets/" + imageName + ".jpg";
			p.setProductImagePath(imageName);
		}
		return products;

	}

	@Override
	public int productApprove(int productId) {
		return adminRepo.productApprove(productId);

	}
}
