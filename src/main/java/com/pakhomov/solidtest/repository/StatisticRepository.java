package com.pakhomov.solidtest.repository;

import com.pakhomov.solidtest.model.dto.StatisticInformationDto;
import com.pakhomov.solidtest.model.entity.Statistic;

import java.util.List;

public interface StatisticRepository {

	void saveStatistic(Statistic statistic);

	void updateStatistic(Statistic statistic);

	List<StatisticInformationDto> getStatisticsOnPage(int pageIndex, int countOnPage);

	Long getCountOfStatistics();

	Statistic getCurrentHourStatistic();
}
