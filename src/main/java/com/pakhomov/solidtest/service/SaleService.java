package com.pakhomov.solidtest.service;

import com.pakhomov.solidtest.exception.EmptyShoppingCartException;
import com.pakhomov.solidtest.model.entity.Sale;

import java.util.List;

public interface SaleService {

	void createSaleByShoppingCartSessionID(String sessionID) throws EmptyShoppingCartException;

	List<Sale> getSalesOnPage(int pageIndex, int countOnPage);

	Long getCountOfSales();
}
