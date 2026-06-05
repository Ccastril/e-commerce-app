package com.castrillon.config;

import org.modelmapper.AbstractConverter;

import com.castrillon.entity.Cart;
import com.castrillon.template.CartTemplate;

public class CartConverter extends AbstractConverter<Cart, CartTemplate>{

	public CartConverter() {
		// TODO Auto-generated constructor stub
	}
	@Override
	protected CartTemplate convert(Cart source) {
		return new CartTemplate(source.getId(), source.getItems(), source.getQuantity(), source.getCartOfferPrice(), source.getAccount(), source.getStatus());
	}

}
