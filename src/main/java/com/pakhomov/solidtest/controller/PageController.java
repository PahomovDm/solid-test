package com.pakhomov.solidtest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

	@GetMapping("/")
	public String productPage() {
		return "products";
	}

	public String purchasePage() {
		return "purchases";
	}

	@GetMapping("/discounts")
	public String discountPage() {
		return "discounts";
	}
}
