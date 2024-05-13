package com.vutheduyet.exercise03.service;

import java.util.List;

import com.vutheduyet.exercise03.entity.Coupon;

public interface CouponService {
    Coupon createCoupon(Coupon coupon);
    Coupon getCouponById(String couponId);
    List<Coupon> getAllCoupons();
    Coupon updateCoupon(Coupon coupon);
    void deleteCoupon(String couponId);
}