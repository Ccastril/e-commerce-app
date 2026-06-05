package io.github.ccastril.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.ccastril.ecommerce.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	
}