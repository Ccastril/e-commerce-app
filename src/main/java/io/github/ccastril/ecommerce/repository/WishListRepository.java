package io.github.ccastril.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.ccastril.ecommerce.entity.WishList;

public interface WishListRepository extends JpaRepository<WishList, Long> {

}