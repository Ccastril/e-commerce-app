package com.castrillon.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.castrillon.entity.Account;
public interface AccountRepository extends JpaRepository<Account, Long> {
	public boolean existsByName(String name);
	public Optional<Account> findByName(String name);

}
