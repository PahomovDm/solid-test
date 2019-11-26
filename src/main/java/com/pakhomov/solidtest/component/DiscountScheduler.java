package com.pakhomov.solidtest.component;

import com.pakhomov.solidtest.model.Discount;
import com.pakhomov.solidtest.service.DiscountService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Configuration
@EnableScheduling
public class DiscountScheduler {

	private final DiscountService discountService;

	public DiscountScheduler(DiscountService discountService) {
		this.discountService = discountService;
	}

	@Scheduled(cron = "0 */60 * * * *")
	private void createNewDiscount() {
		System.out.println("Шедулер");
		discountService.createRandomProductDiscountWithMinAndMaxPercent(Discount.MIN_DISCOUNT_PERCENT, Discount.MAX_DISCOUNT_PERCENT);
	}
}
