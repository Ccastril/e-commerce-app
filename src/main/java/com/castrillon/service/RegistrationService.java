package com.castrillon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.castrillon.entity.Account;
import com.castrillon.exception.RegistrationException;
import com.castrillon.repository.AccountRepository;
import com.castrillon.template.AccountTemplate;


@Service
public class RegistrationService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public Account registerAccount(AccountTemplate accountTemplate) throws RegistrationException {
		boolean alreadyRegistered = accountRepository.existsByName(accountTemplate.name());

		if(alreadyRegistered) {

			throw new RegistrationException("registration.user.account.exists");
		}
		else {
			Account newAccount = registerNewUser(accountTemplate);
			accountRepository.save(newAccount);

			return newAccount;
		}
	}

	public Account registerNewUser(AccountTemplate account) {
		String encodedPassword = passwordEncoder.encode(account.password());
		Account newAccount = new Account(account);
		newAccount.setPassword(encodedPassword);
		newAccount.setConfirmPassword(encodedPassword);
		return newAccount;
	}
}
