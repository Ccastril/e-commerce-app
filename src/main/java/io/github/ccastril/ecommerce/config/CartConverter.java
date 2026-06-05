package io.github.ccastril.ecommerce.config;

import org.modelmapper.AbstractConverter;

import io.github.ccastril.ecommerce.entity.Cart;
import io.github.ccastril.ecommerce.template.CartTemplate;

public class CartConverter extends AbstractConverter<Cart, CartTemplate>{

	public CartConverter() {
		// TODO Auto-generated constructor stub
	}
	@Override
	protected CartTemplate convert(Cart source) {
		return new CartTemplate(source.getId(), source.getItems(), source.getQuantity(), source.getCartOfferPrice(), source.getAccount(), source.getStatus());
	}

}
