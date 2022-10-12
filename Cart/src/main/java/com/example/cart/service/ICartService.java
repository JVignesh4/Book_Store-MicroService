package com.example.cart.service;

import com.example.cart.dto.CartDTO;
import com.example.cart.model.CartData;

import java.util.List;

public interface ICartService {
    CartData insert(CartDTO cartDTO);

    List<CartData> getAllCart();

    CartData getCartById(int id);

    CartData updateCartById(int id, CartDTO dto);

    CartData deleteCartData(int id);
}
