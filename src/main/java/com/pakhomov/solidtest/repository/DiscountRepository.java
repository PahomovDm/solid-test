package com.pakhomov.solidtest.repository;

import com.pakhomov.solidtest.model.entity.Discount;

import java.util.List;

public interface DiscountRepository {

	Discount getLastDiscount();

	void createRandomProductDiscountWithMinAndMaxPercent(int minDiscountPercent, int maxDiscountPercent);

	List<Discount> getDiscountOnPage(int pageIndex, int countOnPage);

	Long getCountOfDiscounts();
}
