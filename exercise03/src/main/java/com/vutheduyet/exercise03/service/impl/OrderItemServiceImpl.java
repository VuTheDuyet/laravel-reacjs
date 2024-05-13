package com.vutheduyet.exercise03.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.vutheduyet.exercise03.entity.OrderItem;
import com.vutheduyet.exercise03.repository.OrderItemRepository;
import com.vutheduyet.exercise03.service.OrderItemService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private OrderItemRepository orderItemRepository;

    @Override
    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    @Override
    public OrderItem getOrderItemById(String orderItemId) {
        Optional<OrderItem> optionalOrderItem = orderItemRepository.findById(orderItemId);
        return optionalOrderItem.orElse(null);
    }

    @Override
    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    @Override
    public OrderItem updateOrderItem(OrderItem orderItem) {
        Optional<OrderItem> optionalExistingOrderItem = orderItemRepository.findById(orderItem.getOrderItemId());
        if (optionalExistingOrderItem.isPresent()) {
            OrderItem existingOrderItem = optionalExistingOrderItem.get();
            existingOrderItem.setPrice(orderItem.getPrice());
            existingOrderItem.setQuantity(orderItem.getQuantity());

            OrderItem updatedOrderItem = orderItemRepository.save(existingOrderItem);
            return updatedOrderItem;
        }

        return null;
    }

    @Override
    public void deleteOrderItem(String orderItemId) {
        orderItemRepository.deleteById(orderItemId);
    }
}
