package io.github.ccastril.ecommerce.config;

import org.modelmapper.AbstractConverter;

import io.github.ccastril.ecommerce.entity.Account;
import io.github.ccastril.ecommerce.template.AccountTemplate;
public class AccountConverter extends AbstractConverter<Account, AccountTemplate> {

	@Override
	protected AccountTemplate convert(Account source) {
		return new AccountTemplate(source.getId(), source.getName(), source.getPassword(), source.getConfirmPassword(), source.getEmail(), source.getCart(), source.getWishList(), source.getOrders());
	}

}
