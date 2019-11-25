package com.pakhomov.solidtest.repository.impl;

import com.pakhomov.solidtest.model.Product;
import com.pakhomov.solidtest.repository.ProductRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void saveProduct(Product product) {
		entityManager.persist(product);
	}

	@Override
	public void updateProduct(Product product) {
		entityManager.merge(product);
	}

	@Override
	public List<Product> getProductsOnPage(int pageIndex, int countOnPage) {
		Query query =  entityManager.createQuery("from Product order by id", Product.class);
		query.setMaxResults(countOnPage);
		query.setFirstResult((pageIndex-1) * countOnPage);
		return query.getResultList();
	}

	@Override
	public Long getCountOfProducts() {
		return entityManager.createQuery("select count(*) from Product", Long.class).getSingleResult();
	}
}
