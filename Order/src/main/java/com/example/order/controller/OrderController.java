package com.example.order.controller;

import com.example.order.dto.OrderDTO;
import com.example.order.dto.ResponseDTO;
import com.example.order.model.OrderData;
import com.example.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> insertOrder(@Valid @RequestBody OrderDTO orderdto) {
       OrderData orderData = orderService.insertOrder(orderdto);
        ResponseDTO responseDTO = new ResponseDTO("Order placed successfully !", orderData);
        return new ResponseEntity(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/getallorders")
    public ResponseEntity<ResponseDTO> getAllOrder() {
        List<OrderData> orderData = orderService.getAllOrder();
        ResponseDTO responseDTO = new ResponseDTO("All Records retrieved Successfully !", orderData);
        return new ResponseEntity(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseDTO> getOrderById(@PathVariable int id) {
        OrderData orderData = orderService.getOrderById(id);
        ResponseDTO responseDTO = new ResponseDTO("Get Call Successfully !", orderData);
        return new ResponseEntity(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/cancelOrder/{userId}")
    public ResponseEntity<ResponseDTO> cancelOrderById(@PathVariable OrderDTO orderDTO, @PathVariable int userId) {
        OrderData orderData = orderService.cancelOrderById(orderDTO, userId);
        ResponseDTO responseDTO = new ResponseDTO("Order Record Canceled Successfully !", orderData);
        return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
    }
}

