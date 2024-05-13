package com.vutheduyet.exercise03.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vutheduyet.exercise03.entity.Order;
import com.vutheduyet.exercise03.service.OrderService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/orders")
public class OrderController {

    private OrderService orderService;

    // Create Order REST API
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order savedOrder = orderService.createOrder(order);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    // Get Order by id REST API
    // http://localhost:8080/api/orders/{id}
    @GetMapping("{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable("id") String orderId) {
        Order order = orderService.getOrderById(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    // Get All Orders REST API
    // http://localhost:8080/api/orders
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    // Update Order REST API
    @PutMapping("{id}")
    // http://localhost:8080/api/orders/{id}
    public ResponseEntity<Order> updateOrder(@PathVariable("id") String orderId,
                                              @RequestBody Order order) {
        order.setOrderId(orderId);
        Order updatedOrder = orderService.updateOrder(order);
        if (updatedOrder != null) {
            return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete Order REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable("id") String orderId) {
        orderService.deleteOrder(orderId);
        return new ResponseEntity<>("Order successfully deleted!", HttpStatus.OK);
    }
}
