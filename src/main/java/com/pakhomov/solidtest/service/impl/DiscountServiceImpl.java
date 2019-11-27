package com.pakhomov.solidtest.service.impl;

import com.pakhomov.solidtest.model.entity.Discount;
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
	public Discount getCurrentDiscount() {
		Discount discount = discountRepository.getLastDiscount();
		if (discount == null) {
			discountRepository.createRandomProductDiscountWithMinAndMaxPercent(Discount.MIN_DISCOUNT_PERCENT, Discount.MAX_DISCOUNT_PERCENT);
			return discountRepository.getLastDiscount();
		}
		return discount;
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
