package io.github.ccastril.ecommerce.dto;

import java.math.BigDecimal;

import io.github.ccastril.ecommerce.entity.CartItem;

public record CartItemResponse(
	Long productId,
	String productName,
	String productImage,
	int quantity,
	BigDecimal unitPrice,
	BigDecimal lineTotal
) {
	public static CartItemResponse from(CartItem cartItem) {
		return new CartItemResponse(
			cartItem.getProduct().getId(),
			cartItem.getProduct().getProductName(),
			cartItem.getProduct().getProductImage(),
			cartItem.getQuantity(),
			cartItem.getUnitPriceAtAdd(),
			cartItem.getLineTotal());
	}
	
}