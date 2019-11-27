package com.pakhomov.solidtest.repository;

import com.pakhomov.solidtest.model.entity.Sale;

import java.util.List;

public interface SaleRepository {

	void addSale(Sale sale);

	List<Sale> getSalesOnPage(int pageIndex, int countOnPage);

	Long getCountOfSales();
}
