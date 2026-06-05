package io.github.ccastril.ecommerce.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="wish_list")
@Getter @Setter @AllArgsConstructor
public class WishList {
	@Id
	@GeneratedValue
	private Long id;
	@OneToMany(mappedBy="wishList", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<WishListItem> items;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="account_id", nullable=false)
	private Account account;
	public WishList() {
		// TODO Auto-generated constructor stub
	}

}
