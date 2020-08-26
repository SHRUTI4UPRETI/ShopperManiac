package com.lti.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.lti.dto.ProductDto;
import com.lti.model.Customer;
import com.lti.model.Items;
import com.lti.model.Product;
import com.lti.model.Retailer;

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

	@Override
	public List<Product> searchProduct(String searchValue) {
		List<Product> products = new ArrayList<>();

		String sql = "select product from Product product where product.productName like :ch or product.productSubCategory like :ch";
		TypedQuery<Product> query = em.createQuery(sql, Product.class);
		query.setParameter("ch", "%" + searchValue + "%");
		products = query.getResultList();
		return products;
	}

	
	@Override
	@Transactional
	public int addProduct(Product product, int retailerId) {
		System.out.println(product);
		Retailer retailer =em.find(Retailer.class, retailerId);
		product.setRetailer(retailer);
		Product product1 = em.merge(product);
		return product1.getProductId();
		
	}
	
	@Override
	@Transactional
	public int updateProductImage(int productId, String imagePath) {
		Product product = em.find(Product.class, productId);
		String extention = imagePath.replace(".jpg", "");;
		product.setProductImagePath(extention);
		em.merge(product);
		return 1;
	}

}
