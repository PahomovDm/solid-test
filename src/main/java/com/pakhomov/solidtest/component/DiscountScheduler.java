package com.pakhomov.solidtest.component;

import com.pakhomov.solidtest.model.entity.Discount;
import com.pakhomov.solidtest.model.entity.Statistic;
import com.pakhomov.solidtest.service.DiscountService;
import com.pakhomov.solidtest.service.StatisticService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@EnableScheduling
public class DiscountScheduler {

	private final DiscountService discountService;

	private final StatisticService statisticService;

	public DiscountScheduler(DiscountService discountService, StatisticService statisticService) {
		this.discountService = discountService;
		this.statisticService = statisticService;
	}

	@Scheduled(cron = "0 */60 * * * *")
	private void createNewDiscount() {
		discountService.createRandomProductDiscountWithMinAndMaxPercent(Discount.MIN_DISCOUNT_PERCENT, Discount.MAX_DISCOUNT_PERCENT);
	}

	@Scheduled(cron = "0 */60 * * * *")
	private void createNewCurrentHourStatistic() {
		statisticService.saveStatistic(new Statistic(LocalDateTime.now()));
	}
}
