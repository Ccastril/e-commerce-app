package io.github.ccastril.ecommerce.service;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.ccastril.ecommerce.entity.Account;
import io.github.ccastril.ecommerce.repository.AccountRepository;
import io.github.ccastril.ecommerce.template.AccountTemplate;

@Service
public class AccountService {
	@Autowired
	private AccountRepository accountRepo;
	@Autowired
	private ModelMapper modelMapper;

	public AccountTemplate getAccountIdByUsername(String username) throws Exception {
		Account acc = accountRepo.findByName(username).orElseThrow(()-> new Exception("Account Not Found"));
		AccountTemplate accTemplate = modelMapper.map(acc, AccountTemplate.class);
		return accTemplate;

	}
	public AccountService() {
		// TODO Auto-generated constructor stub
	}

}
