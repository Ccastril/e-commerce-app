package io.github.ccastril.ecommerce.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="cart")
@Getter @Setter @AllArgsConstructor @ToString
public class Cart {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@OneToMany(mappedBy="cart", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<CartItem> items = new HashSet<>();
	private String anonKey;
	private int quantity;
	private BigDecimal cartOfferPrice = BigDecimal.ZERO;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="account_id")
	private Account account;
	private CartStatus status;
	public Cart() {
		// TODO Auto-generated constructor stub
	}
	public void addOrIncrement(Product p, int qty, BigDecimal priceSnapshot) {
		CartItem existing = items.stream()
				.filter(cartItem -> cartItem.getProduct().getId().equals(p.getId()))
				.findFirst().orElse(null);
		if(existing != null)  existing.setQuantity(existing.getQuantity() + qty);
		else items.add(new CartItem(this, p, qty, priceSnapshot, "","", LocalDate.now()));
		this.setQuantity(items.size());
	}
	
	public void setCartOfferPrice() {
		BigDecimal newTotal = BigDecimal.ZERO;
		//BigDecimal returns a new object so the original object not changed. newTotal did not update because you were assuming the currentOfferPrice 
		//is mutated with each call to the add function, effectively missing the output by not placing it in a new variable evident by currentOfferPrice not changing from default value
		
		for(CartItem i : items) {
			BigDecimal line = i.getLineTotal();
			if(line != null) newTotal = newTotal.add(line);
		}
		this.cartOfferPrice = newTotal;

	}

}
