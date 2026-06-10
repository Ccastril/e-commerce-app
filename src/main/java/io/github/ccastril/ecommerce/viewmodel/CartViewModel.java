package io.github.ccastril.ecommerce.viewmodel;


import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

import io.github.ccastril.ecommerce.entity.Cart;

public record CartViewModel(
		Long cartId,
		Set<CartItemViewModel> items,
		BigDecimal subtotal
		) {
	public static CartViewModel from(Cart cart) {
		var items = cart.getItems().stream().map(item -> new CartItemViewModel(item.getId(), item.getProduct().getProductName(), item.getQuantity(), item.getUnitPriceAtAdd(), item.getLineTotal())).collect(Collectors.toSet());
		BigDecimal subtotal = items.stream()
				.map(CartItemViewModel::lineTotal)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		return new CartViewModel(cart.getId(), items, subtotal);

	}
}
