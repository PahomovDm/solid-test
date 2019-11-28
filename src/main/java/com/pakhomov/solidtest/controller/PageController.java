package com.pakhomov.solidtest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

	@GetMapping("/")
	public String productPage() {
		return "products";
	}

	@GetMapping("/discounts")
	public String discountPage() {
		return "discounts";
	}

	@GetMapping("/sales")
	public String salePage() {
		return "sales";
	}

	@GetMapping("/statistics")
	public String statisticPage() {
		return "statistics";
	}
}
