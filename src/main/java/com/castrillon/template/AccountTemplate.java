package com.castrillon.template;

import java.util.HashSet;
import java.util.Set;

import com.castrillon.entity.Cart;
import com.castrillon.entity.WishList;
import com.castrillon.entity.Order;
import com.castrillon.validation.SameProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@SameProperties(property1 = "password", property2 = "confirmPassword", message="registration.user.password.match")
public record AccountTemplate(
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
	public AccountTemplate(String name, String password, String confirmPassword, String email) {
		this(-1L,name, password, confirmPassword, email, new HashSet<Cart>(), new HashSet<WishList>(), new HashSet<Order>());

	}
}