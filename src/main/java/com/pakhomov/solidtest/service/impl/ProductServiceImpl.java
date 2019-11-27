package com.pakhomov.solidtest.service.impl;

import com.pakhomov.solidtest.model.entity.Product;
import com.pakhomov.solidtest.repository.ProductRepository;
import com.pakhomov.solidtest.service.ProductService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;

	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public void saveProduct(Product product) {
		productRepository.saveProduct(product);
	}

	@Override
	public void updateProduct(Product product) {
		productRepository.updateProduct(product);
	}

	@Override
	public List<Product> getProductsOnPage(int pageIndex, int countOnPage) {
		return productRepository.getProductsOnPage(pageIndex, countOnPage);
	}

	@Override
	public Long getCountOfProducts() {
		return productRepository.getCountOfProducts();
	}
}
