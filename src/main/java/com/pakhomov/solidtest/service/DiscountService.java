package com.pakhomov.solidtest.service;

import com.pakhomov.solidtest.model.Discount;
import com.pakhomov.solidtest.model.Product;

import java.util.List;

public interface DiscountService {

	Discount getLastDiscount();

	void createRandomProductDiscountWithMinAndMaxPercent(int minDiscountPercent, int maxDiscountPercent);

	List<Discount> getDiscountsOnPage(int pageIndex, int countOnPage);

	Long getCountOfProducts();
}
