package com.pakhomov.solidtest.service;

import com.pakhomov.solidtest.model.Discount;

public interface DiscountService {

	Discount getLastDiscount();

	void createRandomDiscount();
}
