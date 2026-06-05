package io.github.ccastril.ecommerce.entity;

import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
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
@Table(name="orders")
@Getter @Setter @AllArgsConstructor @ToString
public class Order {
	
	@Id
	@GeneratedValue
	private Long id;
	@OneToMany(mappedBy="order", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<OrderItem> orderItems;
	@ManyToOne
	@JoinColumn(name="account_id", nullable=false)
	private Account account;
	private Double shippingTotal;
	private Double taxTotal;
	private Double grandTotal;
	private LocalDate createdAt;
	private int quantity;
	private String sellerName;
	private String displayName;
	

	public Order() {
		// TODO Auto-generated constructor stub
	}

}
