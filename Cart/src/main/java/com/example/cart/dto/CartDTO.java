package com.example.cart.dto;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CartDTO {

    private Integer userID;
    private Integer bookID;
    @NotNull(message = "Book Quantity Should Not Be Null ")
    private Integer quantity;
}