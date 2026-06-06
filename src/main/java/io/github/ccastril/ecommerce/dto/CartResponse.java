package io.github.ccastril.ecommerce.dto;


import java.math.BigDecimal;
import java.util.Set;

import io.github.ccastril.ecommerce.entity.Account;
import io.github.ccastril.ecommerce.entity.CartItem;
import io.github.ccastril.ecommerce.entity.CartStatus;

public record CartResponse(
		Long id,
		Set<CartItem> items,
		int quantity,
		BigDecimal cartOfferPrice,
		Account account,
		CartStatus status) {


}
