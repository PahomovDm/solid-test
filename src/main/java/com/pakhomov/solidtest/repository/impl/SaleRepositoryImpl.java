package com.pakhomov.solidtest.repository.impl;

import com.pakhomov.solidtest.model.entity.Sale;
import com.pakhomov.solidtest.repository.SaleRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class SaleRepositoryImpl implements SaleRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void addSale(Sale sale) {
		entityManager.persist(sale);
	}

	@Override
	public List<Sale> getSalesOnPage(int pageIndex, int countOnPage) {
		Query query = entityManager.createQuery("from Sale order by id", Sale.class);
		query.setMaxResults(countOnPage);
		query.setFirstResult((pageIndex - 1) * countOnPage);
		return query.getResultList();
	}

	@Override
	public Long getCountOfSales() {
		return entityManager.createQuery("select count(*) from Sale", Long.class).getSingleResult();
	}
}
