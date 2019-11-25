package com.pakhomov.solidtest.component;

import com.pakhomov.solidtest.service.DiscountService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DiscountScheduler {

	private final DiscountService discountService;

	public DiscountScheduler(DiscountService discountService) {
		this.discountService = discountService;
	}

	@Scheduled(cron = "0 * * * * *")
	private void createNewDiscount() {
		discountService.createRandomDiscount();
	}
}
