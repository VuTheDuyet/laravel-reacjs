package com.vutheduyet.exercise03.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vutheduyet.exercise03.entity.ShippingRate;

public interface ShippingRateRepository extends JpaRepository<ShippingRate, String> {

}