package com.vutheduyet.exercise03.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vutheduyet.exercise03.entity.OrderStatus;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, String> {

}