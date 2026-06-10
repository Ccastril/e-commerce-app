package io.github.ccastril.ecommerce.config;

import org.modelmapper.AbstractConverter;

import io.github.ccastril.ecommerce.dto.CartItemResponse;
import io.github.ccastril.ecommerce.dto.CartResponse;
import io.github.ccastril.ecommerce.entity.Cart;

public class CartConverter extends AbstractConverter<Cart, CartResponse>{
	//should not return an account?
	public CartConverter() {
		// TODO Auto-generated constructor stub
	}
	@Override
	protected CartResponse convert(Cart source) {
		return new CartResponse(source.getId(),  source.getItems().stream()
                .map(CartItemResponse::from)
                .toList(), source.getQuantity(), source.getCartOfferPrice(), source.getStatus());
	}

}
