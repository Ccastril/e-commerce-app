package com.castrillon.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="products")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class Product {
	@Id
	@GeneratedValue
	private Long id;
	private String productName;
	private String productImage;
	private String description;
	private int averageRating;
	private String sellerName;
	private BigDecimal price;
	private Double discount;
	
	public Product(String productName, String productImage, String description, int averageRating, String sellerName, BigDecimal price, Double discount) {
		this.productName = productName;
		this.productImage = productImage;
		this.description = description;
		this.averageRating = averageRating;
		this.sellerName = sellerName;
		this.price = price;
		this.discount = discount;
	}
	
}
