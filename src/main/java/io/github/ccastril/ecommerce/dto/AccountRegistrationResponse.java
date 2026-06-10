package io.github.ccastril.ecommerce.dto;

import java.util.HashSet;
import java.util.Set;

import io.github.ccastril.ecommerce.entity.Cart;
import io.github.ccastril.ecommerce.entity.WishList;
import io.github.ccastril.ecommerce.entity.Order;
import io.github.ccastril.ecommerce.validation.SameProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@SameProperties(property1 = "password", property2 = "confirmPassword", message="registration.user.password.match")
public record AccountRegistrationResponse(
		Long id,
		@NotBlank(message="registration.user.name.blank")
		@Pattern(regexp = "^[a-zA-Z]+$", message="registration.user.name.invalid")
		String name,
		@NotBlank(message="Password cannot be blank")
		@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[^A-Za-z0-9]).*$", message="Password must contain at least one upper-case, one lower-case and one special character")
		String password,
		@NotBlank(message="Confirm Password cannot be blank")
		String confirmPassword,
		@NotBlank(message="Email must not be blank")
		@Email	
		String email,
		Set<Cart> cart,
		Set<WishList> wishList,
		Set<Order> orders
		){
	public AccountRegistrationResponse(String name, String password, String confirmPassword, String email) {
		this(-1L,name, password, confirmPassword, email, new HashSet<Cart>(), new HashSet<WishList>(), new HashSet<Order>());

	}
}