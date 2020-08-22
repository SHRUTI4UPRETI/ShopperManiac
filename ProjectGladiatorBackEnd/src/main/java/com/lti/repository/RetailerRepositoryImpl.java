package com.lti.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.lti.model.Retailer;

@Repository
public class RetailerRepositoryImpl implements RetailerRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public boolean isRetailerPresent(String retailerEmail) {
		return (Long) em.createQuery("select count(r.retailerId) from Retailer r where r.retailerEmail=:email")
				.setParameter("email", retailerEmail).getSingleResult() == 1 ? true : false;
	}

	@Override
	public Retailer findRetailerById(int retailerId) {
		return em.find(Retailer.class, retailerId);
	}

	@Override
	public int findRetailerbyEmailPassword(String retailerEmail, String retailerPassword) {
		return (Integer) em.createQuery(
				"select r.retailerId from Retailer r where r.retailerEmail=:email and r.retailerPassword=:password")
				.setParameter("email", retailerEmail).setParameter("password", retailerPassword).getSingleResult();

	}

}
