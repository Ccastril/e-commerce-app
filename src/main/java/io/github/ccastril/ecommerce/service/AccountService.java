package io.github.ccastril.ecommerce.service;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.ccastril.ecommerce.dto.AccountRegistrationResponse;
import io.github.ccastril.ecommerce.entity.Account;
import io.github.ccastril.ecommerce.repository.AccountRepository;

@Service
public class AccountService {
	@Autowired
	private AccountRepository accountRepo;
	@Autowired
	private ModelMapper modelMapper;

	public AccountRegistrationResponse getAccountIdByUsername(String username) throws Exception {
		Account acc = accountRepo.findByName(username).orElseThrow(()-> new Exception("Account Not Found"));
		AccountRegistrationResponse accTemplate = modelMapper.map(acc, AccountRegistrationResponse.class);
		return accTemplate;

	}
	public AccountService() {
		// TODO Auto-generated constructor stub
	}

}
