package com.pakhomov.solidtest.service;

import com.pakhomov.solidtest.model.entity.Sale;
import com.pakhomov.solidtest.model.entity.Statistic;

import java.util.List;

public interface StatisticService {

	void saveStatistic(Statistic statistic);

	void updateStatistic(Statistic statistic);

	List<Statistic> getStatisticsOnPage(int pageIndex, int countOnPage);

	Long getCountOfStatistics();

	Statistic getCurrentStatistic();

	void addSaleToStatistic(Sale sale);
}
