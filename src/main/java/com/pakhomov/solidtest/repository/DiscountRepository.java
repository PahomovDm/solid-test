package com.pakhomov.solidtest.repository;

import com.pakhomov.solidtest.model.Discount;

import java.util.List;

public interface DiscountRepository {

	Discount getLastDiscount();

	void createRandomDiscount();

	List<Discount> getDiscountOnPage(int pageIndex, int countOnPage);
}
