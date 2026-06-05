package io.github.ccastril.ecommerce.template;


import java.math.BigDecimal;

public record CartItemView(
		Long productId, 
		String name, 
		int quantity, 
		BigDecimal unitPrice, 
		BigDecimal lineTotal) {
}