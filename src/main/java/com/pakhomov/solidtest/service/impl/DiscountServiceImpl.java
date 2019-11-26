package com.pakhomov.solidtest.service.impl;

import com.pakhomov.solidtest.model.Discount;
import com.pakhomov.solidtest.repository.DiscountRepository;
import com.pakhomov.solidtest.service.DiscountService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DiscountServiceImpl implements DiscountService {

	private final DiscountRepository discountRepository;

	public DiscountServiceImpl(DiscountRepository discountRepository) {
		this.discountRepository = discountRepository;
	}

	@Override
	public Discount getLastDiscount() {
		return discountRepository.getLastDiscount();
	}

	public void createRandomProductDiscountWithMinAndMaxPercent(int minDiscountPercent, int maxDiscountPercent) {
		discountRepository.createRandomProductDiscountWithMinAndMaxPercent(minDiscountPercent, maxDiscountPercent);
	}

	@Override
	public Long getCountOfProducts() {
		return discountRepository.getCountOfDiscounts();
	}

	@Override
	public List<Discount> getDiscountsOnPage(int pageIndex, int countOnPage) {
		return discountRepository.getDiscountOnPage(pageIndex, countOnPage);
	}

}
