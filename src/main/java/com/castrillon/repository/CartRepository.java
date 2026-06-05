package com.castrillon.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.castrillon.entity.Cart;
import com.castrillon.entity.CartStatus;

public interface CartRepository extends JpaRepository<Cart, Long> {

	public Optional<Cart> findByAccountIdAndStatus(Long id, CartStatus status);
	public Optional<Cart> findByAnonKeyAndStatus(String anonKey, CartStatus status);
}