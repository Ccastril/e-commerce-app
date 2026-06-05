package com.castrillon.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.castrillon.entity.Account;
import com.castrillon.entity.Cart;
import com.castrillon.entity.CartStatus;
import com.castrillon.entity.Product;
import com.castrillon.repository.CartRepository;
import com.castrillon.template.AddItemRequest;
import com.castrillon.template.CartSummary;
import com.castrillon.template.CartView;
import jakarta.annotation.Nullable;
import jakarta.transaction.Transactional;
@Service
@Transactional
public class CartService {

	@Autowired
	private CartRepository cartRepo;
	@Autowired
	private ProductService productService;

	public CartSummary addItem(@Nullable Account userAccount, @Nullable String anonKey, AddItemRequest req) throws Exception {
		Cart cart = getActiveCart(userAccount, anonKey);

		Product prod = productService.getProductById(req.productId()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
		cart.addOrIncrement(prod, req.quantity(), prod.getPrice());
		return CartSummary.from(cart);
	}
	public Cart getActiveCart(@Nullable Account userAccount,@Nullable String anonKey) {
		if(userAccount != null) {
			System.out.println("are win in userAccount not null block?");
			return cartRepo.findByAccountIdAndStatus(userAccount.getId(), CartStatus.ACTIVE)
					.orElseGet(() -> { Cart c = new Cart(); c.setAccount(userAccount); c.setStatus(CartStatus.ACTIVE); return cartRepo.save(c);});
		}else if (anonKey != null) {
			System.out.println("are we in anonKeyNull block? ");
			return cartRepo.findByAnonKeyAndStatus(anonKey, CartStatus.ACTIVE)
					.orElseGet(()-> { Cart c = new Cart(); c.setAnonKey(anonKey); c.setStatus(CartStatus.ACTIVE); return cartRepo.save(c); });
		}else {
			System.out.println("are we in the else block? ");
			Cart c = new Cart();
			c.setAnonKey(UUID.randomUUID().toString());
			return cartRepo.save(c);
		}

	}
	public CartView getActiveCartView(@Nullable Account userAccount, @Nullable String anonKey) {
		Cart c = getActiveCart(userAccount, anonKey);
		CartView cartView = CartView.from(c);
		System.out.println(cartView);
		return cartView;

	}
	public CartService() {
		// TODO Auto-generated constructor stub
	}

}