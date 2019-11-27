package com.pakhomov.solidtest.controller.rest;

import com.pakhomov.solidtest.exception.EmptyShoppingCartException;
import com.pakhomov.solidtest.model.entity.Position;
import com.pakhomov.solidtest.model.entity.ShoppingCart;
import com.pakhomov.solidtest.service.SaleService;
import com.pakhomov.solidtest.service.ShoppingCartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/shoppingCart")
public class ShoppingCartRestController {

	private final ShoppingCartService shoppingCartService;

	private final SaleService saleService;

	public ShoppingCartRestController(ShoppingCartService shoppingCartService, SaleService saleService) {
		this.shoppingCartService = shoppingCartService;
		this.saleService = saleService;
	}

	@GetMapping
	private ResponseEntity<ShoppingCart> getShoppingCart(HttpSession session) {
		ShoppingCart shoppingCart = shoppingCartService.getShoppingCartBySessionID(session.getId());
		if (shoppingCart == null) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(shoppingCart);
		}
	}

	@PostMapping
	private ResponseEntity<ShoppingCart> addPositionToShoppingCart(@RequestBody Position position, HttpSession session) {
		shoppingCartService.addPositionToShoppingCartBySessionID(session.getId(), position);
		ShoppingCart shoppingCart = shoppingCartService.getShoppingCartBySessionID(session.getId());
		return ResponseEntity.ok(shoppingCart);
	}

	@PostMapping("/buy")
	private ResponseEntity buyAllPositionInShoppingCart(HttpSession session) {
		try {
			saleService.createSaleByShoppingCartSessionID(session.getId());
			return ResponseEntity.ok().build();
		} catch (EmptyShoppingCartException e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
