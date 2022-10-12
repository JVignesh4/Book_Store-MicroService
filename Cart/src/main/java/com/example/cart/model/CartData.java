package com.example.cart.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Data
@Entity
public class CartData {

    @Id
    @GeneratedValue
    private Integer cartID;
    private Integer userID;

    private Integer bookID;
    private Integer quantity;

    public CartData(Integer cartID, Integer quantity, Integer bookID, Integer userID) {
        this.cartID = cartID;
        this.quantity = quantity;
        this.bookID = bookID;
        this.userID = userID;
    }

    public CartData(Integer quantity, Integer bookID, Integer userID) {
        this.quantity = quantity;
        this.bookID = bookID;
        this.userID = userID;
    }

    public CartData() {

    }
}