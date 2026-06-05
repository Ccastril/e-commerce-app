package io.github.ccastril.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.github.ccastril.ecommerce.entity.Account;
import io.github.ccastril.ecommerce.exception.RegistrationException;
import io.github.ccastril.ecommerce.repository.AccountRepository;
import io.github.ccastril.ecommerce.template.AccountTemplate;


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
