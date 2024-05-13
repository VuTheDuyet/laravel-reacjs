package com.vutheduyet.exercise03.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vutheduyet.exercise03.entity.OrderStatus;
import com.vutheduyet.exercise03.service.OrderStatusService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/orderStatuses")
public class OrderStatusController {

    private OrderStatusService orderStatusService;

    // Create OrderStatus REST API
    @PostMapping
    public ResponseEntity<OrderStatus> createOrderStatus(@RequestBody OrderStatus orderStatus) {
        OrderStatus savedOrderStatus = orderStatusService.createOrderStatus(orderStatus);
        return new ResponseEntity<>(savedOrderStatus, HttpStatus.CREATED);
    }

    // Get OrderStatus by id REST API
    // http://localhost:8080/api/orderStatuses/{id}
    @GetMapping("{id}")
    public ResponseEntity<OrderStatus> getOrderStatusById(@PathVariable("id") String orderStatusId) {
        OrderStatus orderStatus = orderStatusService.getOrderStatusById(orderStatusId);
        return new ResponseEntity<>(orderStatus, HttpStatus.OK);
    }

    // Get All OrderStatuses REST API
    // http://localhost:8080/api/orderStatuses
    @GetMapping
    public ResponseEntity<List<OrderStatus>> getAllOrderStatuses() {
        List<OrderStatus> orderStatuses = orderStatusService.getAllOrderStatuses();
        return new ResponseEntity<>(orderStatuses, HttpStatus.OK);
    }

    // Update OrderStatus REST API
    @PutMapping("{id}")
    // http://localhost:8080/api/orderStatuses/{id}
    public ResponseEntity<OrderStatus> updateOrderStatus(@PathVariable("id") String orderStatusId,
                                                          @RequestBody OrderStatus orderStatus) {
        orderStatus.setOrderStatusId(orderStatusId);
        OrderStatus updatedOrderStatus = orderStatusService.updateOrderStatus(orderStatus);
        if (updatedOrderStatus != null) {
            return new ResponseEntity<>(updatedOrderStatus, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete OrderStatus REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteOrderStatus(@PathVariable("id") String orderStatusId) {
        orderStatusService.deleteOrderStatus(orderStatusId);
        return new ResponseEntity<>("OrderStatus successfully deleted!", HttpStatus.OK);
    }
}
