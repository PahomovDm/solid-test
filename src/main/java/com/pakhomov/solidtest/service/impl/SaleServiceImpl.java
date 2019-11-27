package com.pakhomov.solidtest.service.impl;

import com.pakhomov.solidtest.exception.EmptyShoppingCartException;
import com.pakhomov.solidtest.model.entity.Sale;
import com.pakhomov.solidtest.model.entity.ShoppingCart;
import com.pakhomov.solidtest.repository.SaleRepository;
import com.pakhomov.solidtest.service.SaleService;
import com.pakhomov.solidtest.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class SaleServiceImpl implements SaleService {

	private final SaleRepository saleRepository;

	private final ShoppingCartService shoppingCartService;

	public SaleServiceImpl(SaleRepository saleRepository, ShoppingCartService shoppingCartService) {
		this.saleRepository = saleRepository;
		this.shoppingCartService = shoppingCartService;
	}

	@Override
	public void createSaleByShoppingCartSessionID(String sessionID) throws EmptyShoppingCartException {
		ShoppingCart shoppingCart = shoppingCartService.getShoppingCartBySessionID(sessionID);
		if (shoppingCart == null || shoppingCart.getPositionList().isEmpty()) {
			throw new EmptyShoppingCartException();
		}



		Sale sale = new Sale(shoppingCart.getPositionList(), LocalDateTime.now());
		shoppingCartService.clearShoppingCartBySessionID(sessionID);
		saleRepository.addSale(sale);
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
