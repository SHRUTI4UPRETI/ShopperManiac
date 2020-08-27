package com.lti.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.lti.model.Admin;
import com.lti.model.Product;
import com.lti.model.Retailer;

@Repository
public class AdminRepoImpl implements AdminRepo {

	@PersistenceContext
	EntityManager em;

	@Override
	public String isValidAdmin(String adminEmail, String adminPassword) {

		String sql = "select admin from Admin admin";
		Query qry = em.createQuery(sql);

		List<Admin> admin = qry.getResultList();

		for (Admin a : admin) {
			if (a.getAdminEmail().equals(adminEmail) && adminPassword.equals(a.getAdminPassword()))
				return a.getAdminEmail();
		}

		return null;
	}

	@Override
	public Admin findAdminByEmail(String adminEmail) {
		return em.find(Admin.class, adminEmail);
	}

	@Override
	public List<Product> showProductsToApprove() {
		String sql = "select product from Product product where product.isProductApproved=:status";
		TypedQuery<Product> query = em.createQuery(sql, Product.class);
		query.setParameter("status", false);

		List<Product> products = query.getResultList();
		return products;
	}

	@Override
	public List<Product> approvedProducts() {
		String sql = "select product from Product product where product.isProductApproved=:status";
		TypedQuery<Product> query = em.createQuery(sql, Product.class);
		query.setParameter("status", true);

		List<Product> products = query.getResultList();
		return products;
	}
	
	@Override
	@Transactional
	public int productApprove(int productId) {
		Product product = em.find(Product.class, productId);
		product.setProductApproved(true);
		em.merge(product);
		return 1;
	}
	
	@Override
	public List<Retailer> viewAllRetailers() {
		String sql = "select retailer from Retailer retailer";
		TypedQuery<Retailer> query = em.createQuery(sql, Retailer.class);
		List<Retailer> retailers = query.getResultList();
		return retailers;
		
	}
}
