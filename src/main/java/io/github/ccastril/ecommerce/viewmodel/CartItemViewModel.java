package io.github.ccastril.ecommerce.viewmodel;


import java.math.BigDecimal;

public record CartItemViewModel(
		Long productId, 
		String name, 
		int quantity, 
		BigDecimal unitPrice, 
		BigDecimal lineTotal) {
}