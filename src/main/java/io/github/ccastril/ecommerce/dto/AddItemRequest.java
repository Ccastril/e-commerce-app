package io.github.ccastril.ecommerce.dto;

public record AddItemRequest(
		Long productId,
		int quantity) {

}
