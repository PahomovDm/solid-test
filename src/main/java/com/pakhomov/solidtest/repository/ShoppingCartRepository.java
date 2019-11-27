package com.pakhomov.solidtest.repository;

import com.pakhomov.solidtest.model.entity.ShoppingCart;

public interface ShoppingCartRepository {

	ShoppingCart getShoppingCartBySessionID(String sessionID);

	void saveShoppingCart(ShoppingCart shoppingCart);
}
