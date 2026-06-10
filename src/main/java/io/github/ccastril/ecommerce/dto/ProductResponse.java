package io.github.ccastril.ecommerce.dto;

import java.math.BigDecimal;

public record ProductResponse(
		Long id,
		String productName,
		String productImage,
		String description,
		int averageRating,
		String sellerName,
		BigDecimal price, 
		Double discount) {
	
	public ProductResponse(Long id, String productName, String productImage, String description, int averageRating, String sellerName, BigDecimal price, Double discount) {
		this.id = id;
		this.productName = productName;
		this.productImage = productImage;
		this.description = description;
		this.averageRating = averageRating;
		this.sellerName = sellerName;
		this.price = price;
		this.discount = discount;
	}

}

