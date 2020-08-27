package com.lti.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.lti.model.Customer;
import com.lti.model.Product;
import com.lti.model.Retailer;

import org.springframework.transaction.annotation.Transactional;

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
				.setParameter("e", retailerEmail).getSingleResult() == 1 ? true : false;
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
	
	@Override
	@Transactional
	public int updateRetailerPassword(int retailerId, String retailerPassword) {
		
		Retailer retailer = em.find(Retailer.class, retailerId);
		retailer.setRetailerPassword(retailerPassword);
		em.merge(retailer);
		return 1;
	}
	
	@Override
	public List<Product> viewProductsOfRetailer(int retailerId) {
		
		String sql="select pd from Product pd where pd.retailer.retailerId=:rId";
		TypedQuery<Product> query = em.createQuery(sql, Product.class);
		query.setParameter("rId", retailerId);
		List<Product> products = query.getResultList();
		return products;
	}
	
	@Override
	@Transactional
	public int changeProductStockInInventory(int productId, int productQuantity) {
		
		Product product = em.find(Product.class, productId);
		product.setProductQuantity(productQuantity);
		product.setProductApproved(false);
		em.merge(product);
		return 1;
	}
}
