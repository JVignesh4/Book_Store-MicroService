package com.example.order.service;
import com.example.order.dto.OrderDTO;
import com.example.order.exception.BookException;
import com.example.order.model.OrderData;
import com.example.order.repository.OrderRepository;
import com.example.order.util.EmailSenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private EmailSenderService mailService;

    /*@Autowired
    RestTemplate restTemplate;
*/
    //Object user = restTemplate.getForObject("http://localhost:9004/user/findById/" + OrderDTO.getUserId(), Object.class);
    @Override
    public OrderData insertOrder(OrderDTO orderDTO) {
        OrderData newOrder = new OrderData(orderDTO.getPrice(), orderDTO.getQuantity(), orderDTO.getAddress(), orderDTO.getBookId(), orderDTO.getUserId(), orderDTO.isCancel());
        orderRepository.save(newOrder);
        log.info("Order record inserted successfully");
        mailService.sendEmail(orderDTO.getEmail(), "Your Order Placed successfully", "Hello, Your order for " + newOrder + "  is placed successfully on " + newOrder.getDate() + " and will be delivered to you shortly.");
        return newOrder;
    }


    @Override
    public List<OrderData> getAllOrder() {
        List<OrderData> orderList = orderRepository.findAll();
        log.info("ALL order records retrieved successfully");
        return orderList;
    }

    @Override
    public OrderData getOrderById(int id) {
        Optional<OrderData> orderData = orderRepository.findById(id);
        if (orderData.isPresent()) {
            log.info("Order Record Retrieved Successfully For id " + id);
            return orderData.get();
        } else {
            throw new BookException("Order Doesn't Exists");
        }
    }


    @Override
    public OrderData cancelOrderById(OrderDTO orderDTO, int id) {
        Optional<OrderData> order = orderRepository.findById(id);
        order.get().setCancel(true);
        orderRepository.save(order.get());
        mailService.sendEmail(orderDTO.getEmail(), "Order Cancelled Successfully", "Your order has been cancelled Successfully");
        return order.get();
    }
}

