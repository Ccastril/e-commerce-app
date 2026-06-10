package io.github.ccastril.ecommerce.dto;


import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import io.github.ccastril.ecommerce.entity.Account;
import io.github.ccastril.ecommerce.entity.Cart;
import io.github.ccastril.ecommerce.dto.CartItemResponse;
import io.github.ccastril.ecommerce.entity.CartStatus;

public record CartResponse(
		Long id,
		List<CartItemResponse> items,
		int quantity,
		BigDecimal cartOfferPrice,
		CartStatus status) {
	public static CartResponse from(Cart cart) {
	  return new CartResponse(
	            cart.getId(),
	            cart.getItems()
                .stream()
                .map(CartItemResponse::from)
                .toList(),
	            cart.getQuantity(),
	            cart.getCartOfferPrice(),
	            cart.getStatus()
	        );
	  

	}


}
