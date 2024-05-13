package com.vutheduyet.exercise03.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vutheduyet.exercise03.entity.ProductCoupon;
import com.vutheduyet.exercise03.service.ProductCouponService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/productCoupons")
public class ProductCouponController {

    private ProductCouponService productCouponService;

    // Create ProductCoupon REST API
    @PostMapping
    public ResponseEntity<ProductCoupon> createProductCoupon(@RequestBody ProductCoupon productCoupon) {
        ProductCoupon savedProductCoupon = productCouponService.createProductCoupon(productCoupon);
        return new ResponseEntity<>(savedProductCoupon, HttpStatus.CREATED);
    }

    // Get ProductCoupon by id REST API
    // http://localhost:8080/api/productCoupons/{id}
    @GetMapping("{id}")
    public ResponseEntity<ProductCoupon> getProductCouponById(@PathVariable("id") String productCouponId) {
        ProductCoupon productCoupon = productCouponService.getProductCouponById(productCouponId);
        return new ResponseEntity<>(productCoupon, HttpStatus.OK);
    }

    // Get All ProductCoupons REST API
    // http://localhost:8080/api/productCoupons
    @GetMapping
    public ResponseEntity<List<ProductCoupon>> getAllProductCoupons() {
        List<ProductCoupon> productCoupons = productCouponService.getAllProductCoupons();
        return new ResponseEntity<>(productCoupons, HttpStatus.OK);
    }

    // Update ProductCoupon REST API
    @PutMapping("{id}")
    // http://localhost:8080/api/productCoupons/{id}
    public ResponseEntity<ProductCoupon> updateProductCoupon(@PathVariable("id") String productCouponId,
                                                              @RequestBody ProductCoupon productCoupon) {
        productCoupon.setProductCouponId(productCouponId);
        ProductCoupon updatedProductCoupon = productCouponService.updateProductCoupon(productCoupon);
        if (updatedProductCoupon != null) {
            return new ResponseEntity<>(updatedProductCoupon, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete ProductCoupon REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProductCoupon(@PathVariable("id") String productCouponId) {
        productCouponService.deleteProductCoupon(productCouponId);
        return new ResponseEntity<>("ProductCoupon successfully deleted!", HttpStatus.OK);
    }
}
