package com.vutheduyet.exercise03.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vutheduyet.exercise03.entity.ProductCoupon;

public interface ProductCouponRepository extends JpaRepository<ProductCoupon, String> {

}