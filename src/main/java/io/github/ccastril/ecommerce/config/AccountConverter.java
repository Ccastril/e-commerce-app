package io.github.ccastril.ecommerce.config;

import org.modelmapper.AbstractConverter;

import io.github.ccastril.ecommerce.dto.AccountRegistrationResponse;
import io.github.ccastril.ecommerce.entity.Account;
public class AccountConverter extends AbstractConverter<Account, AccountRegistrationResponse> {

	@Override
	protected AccountRegistrationResponse convert(Account source) {
		return new AccountRegistrationResponse(source.getId(), source.getName(), source.getPassword(), source.getConfirmPassword(), source.getEmail(), source.getCart(), source.getWishList(), source.getOrders());
	}

}
