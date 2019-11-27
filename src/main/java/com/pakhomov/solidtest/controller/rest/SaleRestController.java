package com.pakhomov.solidtest.repository.impl;

import com.pakhomov.solidtest.service.SaleService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaleRestController {

	private SaleService saleService;

	public SaleRestController(SaleService saleService) {
		this.saleService = saleService;
	}

	
}
