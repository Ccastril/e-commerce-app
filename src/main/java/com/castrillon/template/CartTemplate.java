package com.castrillon.template;


import java.math.BigDecimal;
import java.util.Set;

import com.castrillon.entity.Account;
import com.castrillon.entity.CartItem;
import com.castrillon.entity.CartStatus;

public record CartTemplate(
		Long id,
		Set<CartItem> items,
		int quantity,
		BigDecimal cartOfferPrice,
		Account account,
		CartStatus status) {


}
