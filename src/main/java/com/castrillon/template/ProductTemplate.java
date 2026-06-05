package com.castrillon.template;

import java.math.BigDecimal;

public record ProductTemplate(
		Long id,
		String productName,
		String productImage,
		String description,
		int averageRating,
		String sellerName,
		BigDecimal price, 
		Double discount) {
	
	public ProductTemplate(Long id, String productName, String productImage, String description, int averageRating, String sellerName, BigDecimal price, Double discount) {
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

