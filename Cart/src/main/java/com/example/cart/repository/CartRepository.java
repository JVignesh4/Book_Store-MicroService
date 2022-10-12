package com.example.cart.repository;

import com.example.cart.model.CartData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartData, Integer> {

}