package io.github.ccastril.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.ccastril.ecommerce.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	@Override
	public Optional<Product> findById(Long id);
}