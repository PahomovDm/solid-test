package com.pakhomov.solidtest.repository.impl;

import com.pakhomov.solidtest.model.entity.ShoppingCart;
import com.pakhomov.solidtest.repository.ShoppingCartRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ShoppingCartRepositoryImpl implements ShoppingCartRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public ShoppingCart getShoppingCartBySessionID(String sessionID) {
		return entityManager.find(ShoppingCart.class, sessionID);
	}

	@Override
	public void saveShoppingCart(ShoppingCart shoppingCart) {
		entityManager.persist(shoppingCart);
	}
}
