package com.example.cart.controller;

import com.example.cart.dto.CartDTO;
import com.example.cart.dto.ResponseDTO;
import com.example.cart.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/cartDetails")
public class CartController {

    @Autowired
    ICartService cartService;

    @PostMapping("/insert")
    public ResponseEntity<ResponseDTO> insertCart(@Valid @RequestBody CartDTO cartdto) {
        ResponseDTO dto = new ResponseDTO("Book Added To Cart successfully !", cartService.insert(cartdto));
        return new ResponseEntity(dto, HttpStatus.CREATED);
    }

    @GetMapping("/getallcart")
    public ResponseEntity<ResponseDTO> getAllCartRecords() {
        ResponseDTO dto = new ResponseDTO("All records retrieved successfully !", cartService.getAllCart());
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    @PutMapping("/updateCart/{id}")
    public ResponseEntity<ResponseDTO> updateCartRecord(@PathVariable int id, @Valid @RequestBody CartDTO cartdto) {
        ResponseDTO dto = new ResponseDTO("Record updated successfully !", cartService.updateCartById(id, cartdto));
        return new ResponseEntity(dto, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deleteCart/{id}")
    public ResponseEntity<ResponseDTO> deleteCartRecord(@PathVariable int id) {
        ResponseDTO dto = new ResponseDTO("Record deleted successfully !", cartService.deleteCartData(id));
        return new ResponseEntity(dto, HttpStatus.ACCEPTED);
    }

    @GetMapping("/getcart/{id}")
    public ResponseEntity<ResponseDTO> getCartRecord(@PathVariable int id) {
        ResponseDTO dto = new ResponseDTO("Record retrieved successfully !", cartService.getCartById(id));
        return new ResponseEntity(dto, HttpStatus.OK);
    }
}
