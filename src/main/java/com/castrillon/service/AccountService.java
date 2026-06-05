package com.castrillon.service;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.castrillon.entity.Account;
import com.castrillon.repository.AccountRepository;
import com.castrillon.template.AccountTemplate;

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
