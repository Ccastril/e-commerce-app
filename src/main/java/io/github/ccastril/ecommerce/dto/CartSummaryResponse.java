package io.github.ccastril.ecommerce.dto;

import java.math.BigDecimal;
import java.util.List;

import io.github.ccastril.ecommerce.dto.CartSummaryResponse;
import io.github.ccastril.ecommerce.entity.Cart;
import io.github.ccastril.ecommerce.entity.CartItem;
import io.github.ccastril.ecommerce.viewmodel.CartItemViewModel;

public record CartSummaryResponse(
		Long cartId,
		int itemCount,
		BigDecimal subtotal,
		List<CartItemViewModel> items,
		String anonKey) {

	public static CartSummaryResponse from(Cart cart) {
		var items = cart.getItems().stream()
				.map(item -> new CartItemViewModel(
						item.getProduct().getId(),
						item.getProduct().getProductName(),
						item.getQuantity(),
						item.getUnitPriceAtAdd(),
						item.getUnitPriceAtAdd().multiply(BigDecimal.valueOf(item.getQuantity()))
						))
				.toList();

		int count = cart.getItems().stream().mapToInt(CartItem::getQuantity).sum();
		BigDecimal subtotal = items.stream()
				.map(CartItemViewModel::lineTotal)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		return new CartSummaryResponse(cart.getId(), count, subtotal, items, cart.getAnonKey());
	}

}
