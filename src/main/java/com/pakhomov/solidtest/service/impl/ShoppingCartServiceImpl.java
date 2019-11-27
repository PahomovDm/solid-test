package com.pakhomov.solidtest.service.impl;

import com.pakhomov.solidtest.model.entity.Position;
import com.pakhomov.solidtest.model.entity.ShoppingCart;
import com.pakhomov.solidtest.repository.ShoppingCartRepository;
import com.pakhomov.solidtest.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService {

	private final ShoppingCartRepository shoppingCartRepository;

	public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository) {
		this.shoppingCartRepository = shoppingCartRepository;
	}

	@Override
	public ShoppingCart getShoppingCartBySessionID(String sessionID) {
		return shoppingCartRepository.getShoppingCartBySessionID(sessionID);
	}

	@Override
	public void saveShoppingCart(ShoppingCart shoppingCart) {
		shoppingCartRepository.saveShoppingCart(shoppingCart);
	}

	@Override
	public void addPositionToShoppingCartBySessionID(String sessionID, Position position) {
		ShoppingCart shoppingCart = getShoppingCartBySessionID(sessionID);
		if (shoppingCart == null) {
			shoppingCart = new ShoppingCart(sessionID, Collections.singletonList(position));
			shoppingCartRepository.saveShoppingCart(shoppingCart);
			return;
		}
		List<Position> positions = shoppingCart.getPositionList();
		if (positions.contains(position)) {
			int index = positions.indexOf(position);
			Position existPosition = positions.get(index);
			existPosition.setNumber(existPosition.getNumber() + position.getNumber());
		} else {
			positions.add(position);
		}
		shoppingCart.setPositionList(positions);
	}

	@Override
	public void clearShoppingCartBySessionID(String sessionID) {
		ShoppingCart shoppingCart = getShoppingCartBySessionID(sessionID);
		shoppingCart.setPositionList(Collections.EMPTY_LIST);
	}
}
