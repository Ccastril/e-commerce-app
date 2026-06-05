package io.github.ccastril.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.ccastril.ecommerce.entity.Cart;
import io.github.ccastril.ecommerce.entity.CartStatus;

public interface CartRepository extends JpaRepository<Cart, Long> {

	public Optional<Cart> findByAccountIdAndStatus(Long id, CartStatus status);
	public Optional<Cart> findByAnonKeyAndStatus(String anonKey, CartStatus status);
}