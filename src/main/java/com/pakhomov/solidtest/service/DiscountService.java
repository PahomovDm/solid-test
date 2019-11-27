package com.pakhomov.solidtest.service;

import com.pakhomov.solidtest.model.entity.Discount;

import java.util.List;

public interface DiscountService {

	Discount getCurrentDiscount();

	void createRandomProductDiscountWithMinAndMaxPercent(int minDiscountPercent, int maxDiscountPercent);

	List<Discount> getDiscountsOnPage(int pageIndex, int countOnPage);

	Long getCountOfProducts();
}
