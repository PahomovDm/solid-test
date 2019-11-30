package com.pakhomov.solidtest.service.impl;

import com.pakhomov.solidtest.model.dto.StatisticInformationDto;
import com.pakhomov.solidtest.model.entity.Sale;
import com.pakhomov.solidtest.model.entity.Statistic;
import com.pakhomov.solidtest.repository.StatisticRepository;
import com.pakhomov.solidtest.service.StatisticService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class StatisticServiceImpl implements StatisticService {

	private final StatisticRepository statisticRepository;

	public StatisticServiceImpl(StatisticRepository statisticRepository) {
		this.statisticRepository = statisticRepository;
	}

	@EventListener(ApplicationReadyEvent.class)
	@Override
	public Statistic getCurrentStatistic() {
		Statistic statistic = statisticRepository.getCurrentHourStatistic();
		if (statistic == null) {
			statistic = new Statistic(LocalDateTime.now());
			statisticRepository.saveStatistic(statistic);
		}
		return statistic;
	}

	@Override
	public void saveStatistic(Statistic statistic) {
		statisticRepository.saveStatistic(statistic);
	}

	@Override
	public void updateStatistic(Statistic statistic) {
		statisticRepository.updateStatistic(statistic);
	}

	@Override
	public List<StatisticInformationDto> getStatisticsOnPage(int pageIndex, int countOnPage) {
		return statisticRepository.getStatisticsOnPage(pageIndex, countOnPage);
	}

	@Override
	public Long getCountOfStatistics() {
		return statisticRepository.getCountOfStatistics();
	}

	@Override
	public void addSaleToStatistic(Sale sale) {
		Statistic statistic = this.getCurrentStatistic();
		List<Sale> sales = statistic.getSales();

		if (sales == null) {
			sales = new ArrayList<>();
		}

		sales.add(sale);
		statistic.setSales(sales);
		this.updateStatistic(statistic);
	}
}
