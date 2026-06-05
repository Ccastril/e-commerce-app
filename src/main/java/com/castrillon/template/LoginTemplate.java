package com.castrillon.template;
import jakarta.validation.constraints.NotBlank;

public record LoginTemplate(
		@NotBlank(message="Username cannot be blank")
		String username,
		@NotBlank(message="Password cannot be blank")
		String password
		){
}
