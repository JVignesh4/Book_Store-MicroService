package com.example.order.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "Order_details")
public class OrderData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderId;
    private LocalDate date = LocalDate.now();
    private int price;
    private int quantity;
    private String address;
    private int bookID;
    private int userID;

    private boolean cancel;

    public OrderData() {
    }

    public OrderData(int orderId, int price, int quantity, String address, int bookID, int userID, boolean cancel) {
        this.orderId = orderId;
        this.price = price;
        this.quantity = quantity;
        this.address = address;
        this.bookID = bookID;
        this.userID = userID;
        this.cancel = cancel;
    }

    public OrderData(int price, int quantity, String address, int bookId, int userId, boolean cancel) {

    }
}

