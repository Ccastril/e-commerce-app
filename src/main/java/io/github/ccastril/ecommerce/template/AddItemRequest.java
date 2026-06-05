package io.github.ccastril.ecommerce.template;

public record AddItemRequest(
		Long productId,
		int quantity) {

}
