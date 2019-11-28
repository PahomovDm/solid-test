package com.pakhomov.solidtest.repository.impl;

import com.pakhomov.solidtest.model.dto.ProductInformationDto;
import com.pakhomov.solidtest.model.entity.Product;
import com.pakhomov.solidtest.repository.ProductRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

	private final Logger logger = Logger.getLogger("com.pakhomov.solidtest.repository.impl.ProductRepositoryImpl");

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void saveProduct(Product product) {
		entityManager.persist(product);
	}

	@Override
	public void updateProduct(Product product) {
		entityManager.merge(product);
	}

	@Override
	public List<Product> getProductsOnPage(int pageIndex, int countOnPage) {
		Query query =  entityManager.createQuery("from Product order by id", Product.class);
		query.setMaxResults(countOnPage);
		query.setFirstResult((pageIndex-1) * countOnPage);
		return query.getResultList();
	}

	@Override
	public Long getCountOfProducts() {
		return entityManager.createQuery("select count (*) from Product", Long.class).getSingleResult();
	}

	@Override
	public ProductInformationDto getProductInformationByProductId(Long productId) {
		ProductInformationDto productInformationDto = null;
		Query query = entityManager.createQuery(
				"select new com.pakhomov.solidtest.model.dto.ProductInformationDto(pr.id, sum(p.number), max(s.date)) " +
						"from Sale s left join s.positions p left Product pr on pr.id = p.product " +
						"where pr.id = :productId " +
						"group by pr.id", ProductInformationDto.class).setParameter("productId", productId);
		try {
			productInformationDto = (ProductInformationDto) query.getSingleResult();
		} catch (NoResultException ex) {
			logger.info(ex.getMessage());
		}
		return productInformationDto;
	}
}
