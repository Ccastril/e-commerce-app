package com.castrillon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.castrillon.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	
}