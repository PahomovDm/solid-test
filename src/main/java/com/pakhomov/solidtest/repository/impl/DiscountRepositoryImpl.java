package com.pakhomov.solidtest.repository.impl;

import com.pakhomov.solidtest.model.entity.Discount;
import com.pakhomov.solidtest.repository.DiscountRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class DiscountRepositoryImpl implements DiscountRepository {

	private final Logger logger = Logger.getLogger("com.pakhomov.solidtest.repository.impl.DiscountRepositoryImpl");

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Long getCountOfDiscounts() {
		return entityManager.createQuery("select count(*) from Discount", Long.class).getSingleResult();
	}

	@Override
	public Discount getCurrentHourDiscount() {
		Discount discount = null;
		try {
			discount = entityManager.createQuery("from Discount where startTime = (select max(startTime) from Discount)", Discount.class).getSingleResult();
		} catch (NoResultException ex) {
			logger.info(ex.getMessage());
		}
		return discount;
	}

	@Override
	public void createRandomProductDiscountWithMinAndMaxPercent(int minDiscountPercent, int maxDiscountPercent) {
		entityManager.createNativeQuery("insert into discounts(size, start_time, product_id) " +
				"values ((select floor(random() * (:max - :min + 1)) + :min), current_timestamp, (select id " +
				"                                                                         from products " +
				"                                                                         order by random() " +
				"                                                                         LIMIT 1))")
				.setParameter("min", minDiscountPercent).setParameter("max", maxDiscountPercent).executeUpdate();
	}


	@Override
	public List<Discount> getDiscountOnPage(int pageIndex, int countOnPage) {
		Query query = entityManager.createQuery("from Discount order by id", Discount.class);
		query.setMaxResults(countOnPage);
		query.setFirstResult((pageIndex - 1) * countOnPage);
		return query.getResultList();
	}
}
