package io.github.ccastril.ecommerce.api;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.ccastril.ecommerce.entity.Account;
import io.github.ccastril.ecommerce.service.CartService;
import io.github.ccastril.ecommerce.template.AddItemRequest;
import io.github.ccastril.ecommerce.template.CartSummary;
import io.github.ccastril.ecommerce.template.CartView;

@RestController
@RequestMapping("/api/cart")
public class CartApi {
	@Autowired
	private CartService cartService;
	@PostMapping("/items")
	public ResponseEntity<CartSummary> addToCart(@AuthenticationPrincipal(expression="account") Account account, @CookieValue(value="cart", required=false) String anonKey, @RequestBody AddItemRequest req) throws Exception {
		CartSummary summary = cartService.addItem(account, anonKey, req);

		if(account == null && anonKey == null && summary.anonKey() != null) {
			ResponseCookie cookie = ResponseCookie.from("cart", summary.anonKey())
					.httpOnly(true)
					.secure(true)
					.path("/")
					.maxAge(Duration.ofDays(1))
					.sameSite("Lax")
					.build();
			return ResponseEntity
					.ok()
					.header(HttpHeaders.SET_COOKIE, cookie.toString())
					.body(summary);
		}
		return ResponseEntity.ok(summary);
	}
	@GetMapping("/items")
	public ResponseEntity<CartView> getActiveCart(@AuthenticationPrincipal(expression="account") Account account, @CookieValue(value="cart", required=false) String anonKey) {
		CartView  cartView = cartService.getActiveCartView(account, anonKey);

		return ResponseEntity.ok(cartView);

	}

	public CartApi() {
		// TODO Auto-generated constructor stub
	}

}
