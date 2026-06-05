package io.github.ccastril.ecommerce.template;

import java.math.BigDecimal;
import java.util.List;

import io.github.ccastril.ecommerce.entity.Cart;
import io.github.ccastril.ecommerce.entity.CartItem;
import io.github.ccastril.ecommerce.template.CartSummary;

public record CartSummary(
		Long cartId,
		int itemCount,
		BigDecimal subtotal,
		List<CartItemView> items,
		String anonKey) {

	public static CartSummary from(Cart cart) {
		var items = cart.getItems().stream()
				.map(item -> new CartItemView(
						item.getProduct().getId(),
						item.getProduct().getProductName(),
						item.getQuantity(),
						item.getUnitPriceAtAdd(),
						item.getUnitPriceAtAdd().multiply(BigDecimal.valueOf(item.getQuantity()))
						))
				.toList();

		int count = cart.getItems().stream().mapToInt(CartItem::getQuantity).sum();
		BigDecimal subtotal = items.stream()
				.map(CartItemView::lineTotal)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		return new CartSummary(cart.getId(), count, subtotal, items, cart.getAnonKey());
	}

}
