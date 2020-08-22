package com.lti.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lti.model.Retailer;

@Repository
public class RetailerRepositoryImpl implements RetailerRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional
	public void addNewRetailer(Retailer retailer) {
			em.merge(retailer);
		}
	
	@Override
	public boolean isRetailerPresent(String retailerEmail) {
		return (Long) em.createQuery("select count(r.retailerId) from Retailer r where r.retailerEmail =: e")
				.setParameter("e", retailerEmail).getSingleResult() ==1? true:false;
	}
	
	}

