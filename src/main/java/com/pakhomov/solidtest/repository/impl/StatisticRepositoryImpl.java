package com.pakhomov.solidtest.repository.impl;

import com.pakhomov.solidtest.model.dto.StatisticInformationDto;
import com.pakhomov.solidtest.model.entity.Statistic;
import com.pakhomov.solidtest.repository.StatisticRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class StatisticRepositoryImpl implements StatisticRepository {

	private final Logger logger = Logger.getLogger("com.pakhomov.solidtest.repository.impl.StatisticRepositoryImpl");

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void saveStatistic(Statistic statistic) {
		entityManager.persist(statistic);
	}

	@Override
	public void updateStatistic(Statistic statistic) {
		entityManager.merge(statistic);
	}

	@Override
	public List<StatisticInformationDto> getStatisticsOnPage(int pageIndex, int countOnPage) {
		Query query = entityManager.createQuery(
				"select new com.pakhomov.solidtest.model.dto.StatisticInformationDto(" +
						"		st.startingTime, count(s.id), sum(s.amount), sum (s.amountWithDiscount)) from Statistic st " +
						"	left join st.sales s group by st.id", StatisticInformationDto.class);
		query.setMaxResults(countOnPage);
		query.setFirstResult((pageIndex - 1) * countOnPage);
		return query.getResultList();
	}

	@Override
	public Long getCountOfStatistics() {
		return entityManager.createQuery("select count(*) from Statistic", Long.class).getSingleResult();
	}

	@Override
	public Statistic getCurrentHourStatistic() {
		Statistic statistic = null;
		Query query = entityManager.createQuery("from Statistic where startingTime = " +
				"(select max(startingTime) from Statistic)", Statistic.class);
		try {
			statistic = (Statistic) query.getSingleResult();
		} catch (NoResultException ex) {
			logger.info(ex.getMessage());
		}
		return statistic;
	}


}
