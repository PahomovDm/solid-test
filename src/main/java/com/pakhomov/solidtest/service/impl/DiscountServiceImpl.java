package com.pakhomov.solidtest.service.impl;

import com.pakhomov.solidtest.model.entity.Discount;
import com.pakhomov.solidtest.repository.DiscountRepository;
import com.pakhomov.solidtest.service.DiscountService;
import com.pakhomov.solidtest.service.ProductService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@Service
@Transactional
public class DiscountServiceImpl implements DiscountService {

	private final DiscountRepository discountRepository;

	private final ProductService productService;

	public DiscountServiceImpl(DiscountRepository discountRepository, ProductService productService) {
		this.discountRepository = discountRepository;
		this.productService = productService;
	}

	@EventListener(ApplicationReadyEvent.class)
	@Override
	public Discount getCurrentDiscount() {
		Discount discount = discountRepository.getCurrentHourDiscount();
		if (discount == null && productService.getCountOfProducts() != 0) {
			createRandomProductDiscountWithMinAndMaxPercent(Discount.MIN_DISCOUNT_PERCENT, Discount.MAX_DISCOUNT_PERCENT);
			return discountRepository.getCurrentHourDiscount();
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
