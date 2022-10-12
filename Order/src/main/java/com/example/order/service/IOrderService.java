package com.example.order.service;


import com.example.order.dto.OrderDTO;
import com.example.order.model.OrderData;

import java.util.List;

public interface IOrderService {


    OrderData insertOrder(OrderDTO orderDTO);

    List<OrderData> getAllOrder();

    OrderData getOrderById(int id);

    OrderData cancelOrderById(OrderDTO orderDTO,int id);
}

