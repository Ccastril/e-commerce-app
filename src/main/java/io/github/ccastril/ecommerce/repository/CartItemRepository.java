package io.github.ccastril.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.ccastril.ecommerce.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}
