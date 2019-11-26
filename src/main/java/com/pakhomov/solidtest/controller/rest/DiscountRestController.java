package com.pakhomov.solidtest.controller.rest;

import com.pakhomov.solidtest.model.Discount;
import com.pakhomov.solidtest.repository.DiscountRepository;
import com.pakhomov.solidtest.service.DiscountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/discounts")
public class DiscountRestController {

	private final DiscountService discountService;

	public DiscountRestController(DiscountService discountService) {
		this.discountService = discountService;
	}

	@PostMapping
	public ResponseEntity createNewDiscount() {
		discountService.createRandomProductDiscountWithMinAndMaxPercent(Discount.MIN_DISCOUNT_PERCENT, Discount.MAX_DISCOUNT_PERCENT);
		return ResponseEntity.ok().build();
	}

	@GetMapping
	public ResponseEntity<Discount> lastDiscount() {
		return ResponseEntity.ok(discountService.getLastDiscount());
	}

}
