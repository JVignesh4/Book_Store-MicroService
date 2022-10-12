package com.example.cart.service;

import com.example.cart.dto.CartDTO;
import com.example.cart.exception.BookStoreException;
import com.example.cart.model.CartData;
import com.example.cart.repository.CartRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CartService implements ICartService {

    @Autowired
    private CartRepository cartRepository;

    public CartData insert(CartDTO cartdto) {
        CartData newCart = new CartData(cartdto.getQuantity(), cartdto.getBookID(), cartdto.getUserID());
        cartRepository.save(newCart);
        log.info("Cart record inserted successfully");
        return newCart;
    }

    public List<CartData> getAllCart() {
        List<CartData> cartList = cartRepository.findAll();
        log.info("All cart records retrieved successfully");
        return cartList;
    }

    public CartData updateCartById(int id, CartDTO dto) {
        CartData newCart = new CartData(id, dto.getQuantity(), dto.getUserID(), dto.getBookID());
        cartRepository.save(newCart);
        log.info("Cart record updated successfully for id " + id);
        return newCart;
    }

    public CartData getCartById(int id) {
        Optional<CartData> cart = cartRepository.findById(id);
        if (cart.isEmpty()) {
            throw new BookStoreException("Cart Record doesn't exists");
        } else {
            return cart.get();
        }
    }

    public CartData deleteCartData(int id) {
        Optional<CartData> cart = cartRepository.findById(id);
        if (cart.isEmpty()) {
            throw new BookStoreException("Cart Record doesn't exists");
        } else {
            cartRepository.deleteById(id);
            log.info("Cart record deleted successfully for id " + id);
            return cart.get();
        }
    }
}
