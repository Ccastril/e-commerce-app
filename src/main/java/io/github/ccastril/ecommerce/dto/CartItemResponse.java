package io.github.ccastril.ecommerce.dto;

import java.math.BigDecimal;

public record CartItemResponse(
		Long productId, 
		String name, 
		int quantity, 
		BigDecimal unitPrice, 
		BigDecimal lineTotal) {
}