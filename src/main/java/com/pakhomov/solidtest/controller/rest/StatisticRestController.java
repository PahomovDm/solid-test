package com.pakhomov.solidtest.controller.rest;

import com.pakhomov.solidtest.model.entity.Statistic;
import com.pakhomov.solidtest.service.StatisticService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/statistics")
public class StatisticRestController {

	private final static Integer COUNT_ON_PAGE = 10;

	private final StatisticService statisticService;

	public StatisticRestController(StatisticService statisticService) {
		this.statisticService = statisticService;
	}

	@GetMapping
	private ResponseEntity<List<Statistic>> getListProduct(@RequestParam(name = "page", required = false) Integer page) {
		if (page == null) {
			return ResponseEntity.ok(statisticService.getStatisticsOnPage(1, COUNT_ON_PAGE));
		}
		return ResponseEntity.ok(statisticService.getStatisticsOnPage(page, COUNT_ON_PAGE));
	}

	@GetMapping("/pagination")
	private ResponseEntity<Long> getPagesNumber() {
		Long countOfProducts = statisticService.getCountOfStatistics();
		if (countOfProducts == 0) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(Double.valueOf(Math.ceil(countOfProducts.doubleValue() / COUNT_ON_PAGE)).longValue());
		}
	}
}
