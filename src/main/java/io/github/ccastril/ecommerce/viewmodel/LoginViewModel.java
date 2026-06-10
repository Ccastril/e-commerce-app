package io.github.ccastril.ecommerce.viewmodel;
import jakarta.validation.constraints.NotBlank;

public record LoginViewModel(
		@NotBlank(message="Username cannot be blank")
		String username,
		@NotBlank(message="Password cannot be blank")
		String password
		){
}
