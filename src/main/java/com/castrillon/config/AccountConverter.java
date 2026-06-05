package com.castrillon.config;

import org.modelmapper.AbstractConverter;

import com.castrillon.entity.Account;
import com.castrillon.template.AccountTemplate;
public class AccountConverter extends AbstractConverter<Account, AccountTemplate> {

	@Override
	protected AccountTemplate convert(Account source) {
		return new AccountTemplate(source.getId(), source.getName(), source.getPassword(), source.getConfirmPassword(), source.getEmail(), source.getCart(), source.getWishList(), source.getOrders());
	}

}
