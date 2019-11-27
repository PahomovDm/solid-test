package com.pakhomov.solidtest.controller.rest;

import com.pakhomov.solidtest.model.entity.Sale;
import com.pakhomov.solidtest.service.SaleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SaleRestController {

	private final static int COUNT_ON_PAGE = 10;

	private SaleService saleService;

	public SaleRestController(SaleService saleService) {
		this.saleService = saleService;
	}

	@GetMapping
	private ResponseEntity<List<Sale>> getSalesList(
			@RequestParam(name = "page", required = false) Integer page) {
		if (page == null) {
			page = 1;
		}
		return ResponseEntity.ok(saleService.getSalesOnPage(page, COUNT_ON_PAGE));
	}

	@GetMapping("/pagination")
	private ResponseEntity<Long> getPagesNumber() {
		Long countOfProducts = saleService.getCountOfSales();
		if (countOfProducts == 0) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(Double.valueOf(Math.ceil(countOfProducts.doubleValue() / COUNT_ON_PAGE)).longValue());
		}
	}

}
