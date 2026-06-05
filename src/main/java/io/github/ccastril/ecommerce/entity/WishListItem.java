package io.github.ccastril.ecommerce.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="wish_list_items")
@Getter @Setter @AllArgsConstructor
public class WishListItem {
	
	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="wish_list_id")
	private WishList wishList;
	
	
	public WishListItem() {
		// TODO Auto-generated constructor stub
	}

}
