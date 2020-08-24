package com.lti.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.lti.dto.ProductDto;
import com.lti.model.Items;
import com.lti.model.Product;

@Repository
public class ProductRepoImpl implements ProductRepo {

	@PersistenceContext
	EntityManager em;

	@Override
	public List<Product> latestFiveProducts() {
		String sql = "select pd from Product pd where rownum<=4 and pd.isProductApproved=:status order by pd.productId desc";
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

	@Override
	public Product viewSpecificProduct(int productId) {
		Product product = em.find(Product.class, productId);
		return product;
	}

	@Override
	public List<Product> viewProductByCategory(String productCategory) {
		String sql = "select product from Product product where product.productCategory=:ct and product.isProductApproved= :status";
		TypedQuery<Product> qry = em.createQuery(sql, Product.class);
		qry.setParameter("ct", productCategory);
		qry.setParameter("status", true);
		List<Product> products = qry.getResultList();

		return products;
	}

	@Override
	public int checkStockQuantity(int itemId) {

		Items item = em.find(Items.class, itemId);

		int qty = item.getProduct().getProductQuantity();

		return qty;
	}

}
