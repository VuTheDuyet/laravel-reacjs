package com.vutheduyet.exercise03.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vutheduyet.exercise03.entity.Coupon;
import com.vutheduyet.exercise03.service.CouponService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/coupons")
public class CouponController {

    private CouponService couponService;

    // Create Coupon REST API
    @PostMapping
    public ResponseEntity<Coupon> createCoupon(@RequestBody Coupon coupon) {
        Coupon savedCoupon = couponService.createCoupon(coupon);
        return new ResponseEntity<>(savedCoupon, HttpStatus.CREATED);
    }

    // Get Coupon by id REST API
    // http://localhost:8080/api/coupons/{id}
    @GetMapping("{id}")
    public ResponseEntity<Coupon> getCouponById(@PathVariable("id") String couponId) {
        Coupon coupon = couponService.getCouponById(couponId);
        return new ResponseEntity<>(coupon, HttpStatus.OK);
    }

    // Get All Coupons REST API
    // http://localhost:8080/api/coupons
    @GetMapping
    public ResponseEntity<List<Coupon>> getAllCoupons() {
        List<Coupon> coupons = couponService.getAllCoupons();
        return new ResponseEntity<>(coupons, HttpStatus.OK);
    }

    // Update Coupon REST API
    @PutMapping("{id}")
    // http://localhost:8080/api/coupons/{id}
    public ResponseEntity<Coupon> updateCoupon(@PathVariable("id") String couponId,
                                                @RequestBody Coupon coupon) {
        coupon.setCouponId(couponId);
        Coupon updatedCoupon = couponService.updateCoupon(coupon);
        if (updatedCoupon != null) {
            return new ResponseEntity<>(updatedCoupon, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete Coupon REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCoupon(@PathVariable("id") String couponId) {
        couponService.deleteCoupon(couponId);
        return new ResponseEntity<>("Coupon successfully deleted!", HttpStatus.OK);
    }
}
