package com.castrillon.security;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.castrillon.entity.Account;
import com.castrillon.repository.AccountRepository;

@Service
public class AuthService implements UserDetailsService {

	@Autowired
	private AccountRepository accountRepository;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account a = accountRepository.findByName(username).orElseThrow(()-> new UsernameNotFoundException("User not found" + username));
		return new AccountDetails(a.getName(), a.getPassword(),Collections.emptyList(), a.getId(), a);
		//		return User.withUsername(a.getName()).password(a.getPassword()).roles().build();
	}

	public AuthService() {
		// TODO Auto-generated constructor stub
	}

	public String getCurrentUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null && authentication.isAuthenticated()) {
			Object principal = authentication.getPrincipal();
			if(principal instanceof UserDetails) {
				return ((UserDetails) principal).getUsername();
			} else {
				return principal.toString();
			}

		}
		return null;
	}

}
