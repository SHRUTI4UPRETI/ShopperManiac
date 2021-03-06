package com.lti.service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.lti.exception.RetailerServiceException;
import com.lti.model.Product;
import com.lti.model.Retailer;
import com.lti.repository.RetailerRepository;

@Service
public class RetailerServiceImpl implements RetailerService {

	@Autowired
	private RetailerRepository retailerRepo;

	@Override
	public void register(Retailer retailer) {
		if (!retailerRepo.isRetailerPresent(retailer.getRetailerEmail())) {
			String pass = retailer.getRetailerPassword();
			String encodedPassword = Base64.getEncoder().encodeToString(pass.getBytes());
			retailer.setRetailerPassword(encodedPassword);
			retailerRepo.addNewRetailer(retailer);
		}

		else
			throw new RetailerServiceException("Retailer Already Registered");

	}

	@Override
	public Retailer loginRetailer(String retailerEmail, String retailerPassword) {
		try {
			if (!retailerRepo.isRetailerPresent(retailerEmail)) {
				throw new RetailerServiceException("Your Email is not registered with us please register");
			}
			// add encoder code
			String encodedPassword = Base64.getEncoder().encodeToString(retailerPassword.getBytes());
			int retailerId = retailerRepo.findRetailerbyEmailPassword(retailerEmail, encodedPassword);
			Retailer retailer = retailerRepo.findRetailerById(retailerId);
			return retailer;
		} catch (EmptyResultDataAccessException e) {
			throw new RetailerServiceException("Invaild Credendials");
		}

	}

	@Override
	public int updateRetailerPassword(int retailerId, String retailerPassword) {
		String encodedPassword = Base64.getEncoder().encodeToString(retailerPassword.getBytes());

		return retailerRepo.updateRetailerPassword(retailerId, encodedPassword);	
		}
	
	@Override

	public Retailer displayRetailerDetails(int retailerId) {
		return retailerRepo.findRetailerById(retailerId);
     }
	
	@Override
	public List<Product> viewProductsOfRetailer(int retailerId) {
		List<Product> products = new ArrayList<>();
		products = retailerRepo.viewProductsOfRetailer(retailerId);
		for (Product p : products) {
			String imageName = p.getProductImagePath();
			imageName = "assets/" + imageName + ".jpg";
			p.setProductImagePath(imageName);
		}
		return products;
		
	}
	
	@Override
	public int changeProductStockInInventory(int productId, int productQuantity) {
		return retailerRepo.changeProductStockInInventory(productId, productQuantity);
	}

}
