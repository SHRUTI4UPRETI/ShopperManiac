package com.lti.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lti.dto.ProductDto;
import com.lti.model.Product;

@Repository
public class ProductRepoImpl implements ProductRepo {

	@PersistenceContext
	EntityManager em;
	
	@Override
	public List<Product> latestFiveProducts() {
		String sql = "select pd from Product pd where rownum<=5 and pd.isProductApproved=:status order by pd.productId desc";
		Query qry = em.createQuery(sql);
		qry.setParameter("status", true);

		List<Product> products = qry.getResultList();
        
		return products;
	}
	
	@Override
	public List<Product> viewAllProducts() {
		String sql = "select prod from Product prod";
		Query qry = em.createQuery(sql);

		List<Product> products = qry.getResultList();

		return products;
	}

}
