package com.vutheduyet.exercise03.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vutheduyet.exercise03.entity.ShippingCountry;

public interface ShippingCountryRepository extends JpaRepository<ShippingCountry, String> {

}