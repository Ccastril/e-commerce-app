package io.github.ccastril.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.github.ccastril.ecommerce.dto.AccountRegistrationResponse;
import io.github.ccastril.ecommerce.entity.Account;
import io.github.ccastril.ecommerce.exception.RegistrationException;
import io.github.ccastril.ecommerce.repository.AccountRepository;


@Service
public class RegistrationService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public Account registerAccount(AccountRegistrationResponse accountRegistrationResponse) throws RegistrationException {
		boolean alreadyRegistered = accountRepository.existsByName(accountRegistrationResponse.name());

		if(alreadyRegistered) {

			throw new RegistrationException("registration.user.account.exists");
		}
		else {
			Account newAccount = registerNewUser(accountRegistrationResponse);
			accountRepository.save(newAccount);

			return newAccount;
		}
	}

	public Account registerNewUser(AccountRegistrationResponse account) {
		String encodedPassword = passwordEncoder.encode(account.password());
		Account newAccount = new Account(account);
		newAccount.setPassword(encodedPassword);
		newAccount.setConfirmPassword(encodedPassword);
		return newAccount;
	}
}
