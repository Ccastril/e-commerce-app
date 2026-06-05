package com.castrillon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.castrillon.entity.WishList;

public interface WishListRepository extends JpaRepository<WishList, Long> {

}