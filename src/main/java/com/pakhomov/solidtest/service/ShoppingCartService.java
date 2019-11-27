package com.pakhomov.solidtest.service;

import com.pakhomov.solidtest.model.entity.Position;
import com.pakhomov.solidtest.model.entity.ShoppingCart;

public interface ShoppingCartService {

	ShoppingCart getShoppingCartBySessionID(String sessionID);

	void saveShoppingCart(ShoppingCart shoppingCart);

	void addPositionToShoppingCartBySessionID(String sessionID, Position position);

	void clearShoppingCartBySessionID(String sessionID);
}
