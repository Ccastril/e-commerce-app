package io.github.ccastril.ecommerce.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import io.github.ccastril.ecommerce.entity.Account;

@SuppressWarnings("serial")
public class AccountDetails extends User {

	private Long userId;
	private final Account account;

	public AccountDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, Long userId, Account account) {
		// TODO Auto-generated constructor stub
		super(username, password, authorities);
		this.userId = userId;
		this.account = account;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Account getAccount() {
		return account;
	}

}
