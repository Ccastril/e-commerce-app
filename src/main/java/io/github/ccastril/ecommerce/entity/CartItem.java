package io.github.ccastril.ecommerce.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="cart_items")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class CartItem {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="cart_id", nullable=false)
	private Cart cart;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="product_id", nullable=false)
	private Product product;
	private int quantity;
	private BigDecimal unitPriceAtAdd;
	private String variant;
	private String appliedPromoCode;
	private LocalDate addedAt;
	
	public CartItem(Cart cart, Product p, int quantity, BigDecimal price, String variant, String appliedPromoCode, LocalDate addedAt) {
		this.cart = cart;
		this.product = p;
		this.quantity = quantity;
		this.unitPriceAtAdd = price;
		this.variant = variant;
		this.appliedPromoCode = appliedPromoCode;
		this.addedAt = addedAt;
	}
	
	
	@Transient
	public BigDecimal getLineTotal() {
		BigDecimal lineTotal = unitPriceAtAdd.multiply(BigDecimal.valueOf(quantity));
		System.out.println("This is the Line total in cart item " + lineTotal);
		return lineTotal;
	}
	

}
