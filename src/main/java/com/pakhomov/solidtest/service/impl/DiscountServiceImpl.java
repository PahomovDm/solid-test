package com.pakhomov.solidtest.service.impl;

import com.pakhomov.solidtest.model.Discount;
import com.pakhomov.solidtest.repository.DiscountRepository;
import com.pakhomov.solidtest.service.DiscountService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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

	public void createRandomDiscount() {
		discountRepository.createRandomDiscount();
	}

}
