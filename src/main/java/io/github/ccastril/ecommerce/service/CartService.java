package io.github.ccastril.ecommerce.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import io.github.ccastril.ecommerce.dto.AddItemRequest;
import io.github.ccastril.ecommerce.dto.CartSummaryResponse;
import io.github.ccastril.ecommerce.entity.Account;
import io.github.ccastril.ecommerce.entity.Cart;
import io.github.ccastril.ecommerce.entity.CartStatus;
import io.github.ccastril.ecommerce.entity.Product;
import io.github.ccastril.ecommerce.repository.CartRepository;
import io.github.ccastril.ecommerce.viewmodel.CartViewModel;
import jakarta.annotation.Nullable;
import jakarta.transaction.Transactional;
@Service
@Transactional
public class CartService {

	@Autowired
	private CartRepository cartRepo;
	@Autowired
	private ProductService productService;

	public CartSummaryResponse addItem(@Nullable Account userAccount, @Nullable String anonKey, AddItemRequest req) throws Exception {
		Cart cart = getActiveCart(userAccount, anonKey);

		Product prod = productService.getProductById(req.productId()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
		cart.addOrIncrement(prod, req.quantity(), prod.getPrice());
		return CartSummaryResponse.from(cart);
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
	public CartViewModel getActiveCartView(@Nullable Account userAccount, @Nullable String anonKey) {
		Cart c = getActiveCart(userAccount, anonKey);
		CartViewModel cartViewModel = CartViewModel.from(c);
		System.out.println(cartViewModel);
		return cartViewModel;

	}
	public CartService() {
		// TODO Auto-generated constructor stub
	}

}