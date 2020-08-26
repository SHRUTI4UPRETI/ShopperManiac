package com.lti.service;

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

		return adminRepo.showProductsToApprove();
	}
	
	@Override
	public List<Product> approvedProducts() {

		return adminRepo.approvedProducts();
	}

	@Override
	public int productApprove(int productId) {
		return adminRepo.productApprove(productId);
	}
}
