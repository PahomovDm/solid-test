package com.pakhomov.solidtest.repository.impl;

import com.pakhomov.solidtest.model.Discount;
import com.pakhomov.solidtest.repository.DiscountRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class DiscountRepositoryImpl implements DiscountRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Discount getLastDiscount() {
		return entityManager.createQuery("from Discount where startTime = (select max(startTime) from Discount)", Discount.class).getSingleResult();
	}

	@Override
	public void createRandomDiscount() {
		entityManager.createNativeQuery("insert into discounts(size, start_time, product_id) " +
				"values ((select floor(random() * (10 - 5 + 1)) + 5), current_timestamp, (select id " +
				"                                                                         from products " +
				"                                                                         order by random() " +
				"                                                                         LIMIT 1))").executeUpdate();
	}



	@Override
	public List<Discount> getDiscountOnPage(int pageIndex, int countOnPage) {
		Query query =  entityManager.createQuery("from Discount order by id", Discount.class);
		query.setMaxResults(countOnPage);
		query.setFirstResult((pageIndex-1) * countOnPage);
		return query.getResultList();
	}
}
