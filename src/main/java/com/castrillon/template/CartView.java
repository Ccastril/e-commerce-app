package com.castrillon.template;


import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

import com.castrillon.entity.Cart;

public record CartView(
		Long cartId,
		Set<CartItemView> items,
		BigDecimal subtotal
		) {
	public static CartView from(Cart cart) {
		var items = cart.getItems().stream().map(item -> new CartItemView(item.getId(), item.getProduct().getProductName(), item.getQuantity(), item.getUnitPriceAtAdd(), item.getLineTotal())).collect(Collectors.toSet());
		BigDecimal subtotal = items.stream()
				.map(CartItemView::lineTotal)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		return new CartView(cart.getId(), items, subtotal);

	}
}
