package com.pakhomov.solidtest.controller.rest;

import com.pakhomov.solidtest.model.Discount;
import com.pakhomov.solidtest.repository.DiscountRepository;
import com.pakhomov.solidtest.service.DiscountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/discounts")
public class DiscountRestController {

	public final static int COUNT_ON_PAGE = 10;

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
	public ResponseEntity<List<Discount>> lastDiscount(
			@RequestParam(name = "page", required = false) Integer page) {
		return ResponseEntity.ok(discountService.getDiscountsOnPage(page, COUNT_ON_PAGE));
	}

	@GetMapping("/pagination")
	public ResponseEntity<Long> getPagesNumber() {
		Long countOfProducts = discountService.getCountOfProducts();
		if (countOfProducts == 0) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(Double.valueOf(Math.ceil(countOfProducts.doubleValue() / COUNT_ON_PAGE)).longValue());
		}
	}

}
