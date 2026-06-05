package com.castrillon.entity;

import java.util.HashSet;
import java.util.Set;

import com.castrillon.template.AccountTemplate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="accounts")
@Getter @Setter @AllArgsConstructor @ToString
public class Account {
	// TODO Auto-generated constructor stub
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="name")
	private String name;
	@Column(name="password")
	private String password;
	@Column(name="confirm_password")
	private String confirmPassword;
	@Column(name="email")
	private String email;
	@OneToMany(mappedBy="account")
	private Set<Cart> cart = new HashSet<>();
	@OneToMany(mappedBy="account")
	private Set<WishList> wishList = new HashSet<>();
	@OneToMany(mappedBy="account")
	private Set<Order> orders = new HashSet<>();

	public Account() {

	}
	public Account(AccountTemplate account) {
		this.name = account.name();
		this.password = account.password();
		this.confirmPassword = account.confirmPassword();
		this.email = account.email();
		this.wishList = new HashSet<>();
		this.orders = new HashSet<>();
	}

}
