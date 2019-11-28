package com.pakhomov.solidtest.service.impl;

import com.pakhomov.solidtest.exception.EmptyShoppingCartException;
import com.pakhomov.solidtest.model.entity.*;
import com.pakhomov.solidtest.repository.SaleRepository;
import com.pakhomov.solidtest.service.DiscountService;
import com.pakhomov.solidtest.service.SaleService;
import com.pakhomov.solidtest.service.ShoppingCartService;
import com.pakhomov.solidtest.service.StatisticService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class SaleServiceImpl implements SaleService {

	private final SaleRepository saleRepository;

	private final ShoppingCartService shoppingCartService;

	private final DiscountService discountService;

	private final StatisticService statisticService;

	public SaleServiceImpl(SaleRepository saleRepository, ShoppingCartService shoppingCartService,
						   DiscountService discountService, StatisticService statisticService) {
		this.saleRepository = saleRepository;
		this.shoppingCartService = shoppingCartService;
		this.discountService = discountService;
		this.statisticService = statisticService;
	}

	@Override
	public void createSaleByShoppingCartSessionID(String sessionID) throws EmptyShoppingCartException {
		ShoppingCart shoppingCart = shoppingCartService.getShoppingCartBySessionID(sessionID);
		if (shoppingCart == null || shoppingCart.getPositionList().isEmpty()) {
			throw new EmptyShoppingCartException();
		}

		Discount currentDiscount = discountService.getCurrentDiscount();
		double saleAmount = 0;
		double discountAmount = 0;

		for (Position position : shoppingCart.getPositionList()) {
			if (position.getProduct().getId().equals(currentDiscount.getProduct().getId())) {
				saleAmount += (100 - currentDiscount.getSize()) / 100.0 * position.getProduct().getPrice() * position.getNumber();
				discountAmount += position.getProduct().getPrice() / 100 * currentDiscount.getSize() * position.getNumber();
			} else {
				saleAmount += position.getProduct().getPrice() * position.getNumber();
			}
		}

		Sale sale = new Sale(shoppingCart.getPositionList(), saleAmount, discountAmount, LocalDateTime.now());
		shoppingCartService.clearShoppingCartBySessionID(sessionID);
		saleRepository.addSale(sale);
		statisticService.addSaleToStatistic(sale);
	}

	@Override
	public List<Sale> getSalesOnPage(int pageIndex, int countOnPage) {
		return saleRepository.getSalesOnPage(pageIndex, countOnPage);
	}

	@Override
	public Long getCountOfSales() {
		return saleRepository.getCountOfSales();
	}
}
