package com.lti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.exception.CustomerServiceException;
import com.lti.exception.RetailerServiceException;
import com.lti.model.Retailer;

import com.lti.repository.RetailerRepository;

@Service
public class RetailerServiceImpl implements RetailerService {

	@Autowired
	private RetailerRepository retailerRepo;
	
	@Override
	public void register(Retailer retailer) {
		if (!retailerRepo.isRetailerPresent(retailer.getRetailerEmail()))
			retailerRepo.addNewRetailer(retailer);
		else
			throw new RetailerServiceException("Retailer Already Registered");


	}

}
