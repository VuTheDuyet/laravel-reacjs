package com.vutheduyet.exercise03.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vutheduyet.exercise03.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, String> {

}