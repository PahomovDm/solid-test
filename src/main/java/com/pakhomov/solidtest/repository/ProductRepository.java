package com.pakhomov.solidtest.repository;

import com.pakhomov.solidtest.model.Product;

import java.util.List;

public interface ProductRepository {

	void saveProduct(Product product);

	void updateProduct(Product product);

	List<Product> getProductsOnPage(int pageIndex, int countOnPage);

	Long getCountOfProducts();
}

