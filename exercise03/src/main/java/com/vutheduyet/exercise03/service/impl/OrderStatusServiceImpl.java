package com.vutheduyet.exercise03.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.vutheduyet.exercise03.entity.OrderStatus;
import com.vutheduyet.exercise03.repository.OrderStatusRepository;
import com.vutheduyet.exercise03.service.OrderStatusService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderStatusServiceImpl implements OrderStatusService {
    private OrderStatusRepository orderStatusRepository;

    @Override
    public OrderStatus createOrderStatus(OrderStatus orderStatus) {
        return orderStatusRepository.save(orderStatus);
    }

    @Override
    public OrderStatus getOrderStatusById(String orderStatusId) {
        Optional<OrderStatus> optionalOrderStatus = orderStatusRepository.findById(orderStatusId);
        return optionalOrderStatus.orElse(null);
    }

    @Override
    public List<OrderStatus> getAllOrderStatuses() {
        return orderStatusRepository.findAll();
    }

    @Override
    public OrderStatus updateOrderStatus(OrderStatus orderStatus) {
        Optional<OrderStatus> optionalExistingOrderStatus = orderStatusRepository.findById(orderStatus.getOrderStatusId());
        if (optionalExistingOrderStatus.isPresent()) {
            OrderStatus existingOrderStatus = optionalExistingOrderStatus.get();
            existingOrderStatus.setStatusName(orderStatus.getStatusName());
            existingOrderStatus.setColor(orderStatus.getColor());
            existingOrderStatus.setPrivacy(orderStatus.getPrivacy());
            existingOrderStatus.setUpdatedAt(LocalDateTime.now());

            OrderStatus updatedOrderStatus = orderStatusRepository.save(existingOrderStatus);
            return updatedOrderStatus;
        }

        return null;
    }

    @Override
    public void deleteOrderStatus(String orderStatusId) {
        orderStatusRepository.deleteById(orderStatusId);
    }
}
