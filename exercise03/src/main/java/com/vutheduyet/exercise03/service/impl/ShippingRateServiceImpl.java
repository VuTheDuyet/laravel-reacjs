package com.vutheduyet.exercise03.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.vutheduyet.exercise03.entity.ShippingRate;
import com.vutheduyet.exercise03.repository.ShippingRateRepository;
import com.vutheduyet.exercise03.service.ShippingRateService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ShippingRateServiceImpl implements ShippingRateService {
    private ShippingRateRepository shippingRateRepository;

    @Override
    public ShippingRate createShippingRate(ShippingRate shippingRate) {
        return shippingRateRepository.save(shippingRate);
    }

    @Override
    public ShippingRate getShippingRateById(String shippingRateId) {
        Optional<ShippingRate> optionalShippingRate = shippingRateRepository.findById(shippingRateId);
        return optionalShippingRate.orElse(null);
    }

    @Override
    public List<ShippingRate> getAllShippingRates() {
        return shippingRateRepository.findAll();
    }

    @Override
    public ShippingRate updateShippingRate(ShippingRate shippingRate) {
        Optional<ShippingRate> optionalExistingShippingRate = shippingRateRepository.findById(shippingRate.getShippingRateId());
        if (optionalExistingShippingRate.isPresent()) {
            ShippingRate existingShippingRate = optionalExistingShippingRate.get();
            existingShippingRate.setWeightUnit(shippingRate.getWeightUnit());
            existingShippingRate.setMinValue(shippingRate.getMinValue());
            existingShippingRate.setMaxValue(shippingRate.getMaxValue());
            existingShippingRate.setNoMax(shippingRate.isNoMax());
            existingShippingRate.setPrice(shippingRate.getPrice());

            ShippingRate updatedShippingRate = shippingRateRepository.save(existingShippingRate);
            return updatedShippingRate;
        }

        return null; // Hoặc xử lý theo yêu cầu của bạn khi không tìm thấy tỉ lệ vận chuyển tồn tại
    }

    @Override
    public void deleteShippingRate(String shippingRateId) {
        shippingRateRepository.deleteById(shippingRateId);
    }
}
