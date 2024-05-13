package com.vutheduyet.exercise03.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.vutheduyet.exercise03.entity.Coupon;
import com.vutheduyet.exercise03.repository.CouponRepository;
import com.vutheduyet.exercise03.service.CouponService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CouponServiceImpl implements CouponService {
    private CouponRepository couponRepository;

    @Override
    public Coupon createCoupon(Coupon coupon) {
        return couponRepository.save(coupon);
    }

    @Override
    public Coupon getCouponById(String couponId) {
        Optional<Coupon> optionalCoupon = couponRepository.findById(couponId);
        return optionalCoupon.orElse(null);
    }

    @Override
    public List<Coupon> getAllCoupons() {
        return couponRepository.findAll();
    }

    @Override
    public Coupon updateCoupon(Coupon coupon) {
        Optional<Coupon> optionalExistingCoupon = couponRepository.findById(coupon.getCouponId());
        if (optionalExistingCoupon.isPresent()) {
            Coupon existingCoupon = optionalExistingCoupon.get();
            existingCoupon.setCode(coupon.getCode());
            existingCoupon.setCouponDescription(coupon.getCouponDescription());
            existingCoupon.setDiscountValue(coupon.getDiscountValue());
            existingCoupon.setDiscountType(coupon.getDiscountType());
            existingCoupon.setTimesUsed(coupon.getTimesUsed());
            existingCoupon.setMaxUsage(coupon.getMaxUsage());
            existingCoupon.setOrderAmountLimit(coupon.getOrderAmountLimit());
            existingCoupon.setCouponStartDate(coupon.getCouponStartDate());
            existingCoupon.setCouponEndDate(coupon.getCouponEndDate());
            existingCoupon.setUpdatedAt(LocalDateTime.now());
            existingCoupon.setUpdatedBy(coupon.getUpdatedBy());

            Coupon updatedCoupon = couponRepository.save(existingCoupon);
            return updatedCoupon;
        }

        return null;
    }

    @Override
    public void deleteCoupon(String couponId) {
        couponRepository.deleteById(couponId);
    }
}
