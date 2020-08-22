package com.lti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.lti.exception.RetailerServiceException;
import com.lti.model.Retailer;
import com.lti.repository.RetailerRepository;

@Service
public class RetailerServiceImpl implements RetailerService {

	@Autowired
	RetailerRepository retailerRepository;

	@Override
	public Retailer loginRetailer(String retailerEmail, String retailerPassword) {
		try {
			if (!retailerRepository.isRetailerPresent(retailerEmail)) {
				throw new RetailerServiceException("Your Email is not registered with us please register");
			}

			int retailerId = retailerRepository.findRetailerbyEmailPassword(retailerEmail, retailerPassword);
			Retailer retailer = retailerRepository.findRetailerById(retailerId);
			return retailer;
		} catch (EmptyResultDataAccessException e) {
			throw new RetailerServiceException("Invaild Credendials");
		}
	}

}
