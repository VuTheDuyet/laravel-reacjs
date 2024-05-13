package com.vutheduyet.exercise03.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.vutheduyet.exercise03.entity.Shipping;
import com.vutheduyet.exercise03.repository.ShippingRepository;
import com.vutheduyet.exercise03.service.ShippingService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ShippingServiceImpl implements ShippingService {
    private ShippingRepository shippingRepository;

    @Override
    public Shipping createShipping(Shipping shipping) {
        return shippingRepository.save(shipping);
    }

    @Override
    public Shipping getShippingById(Integer shippingId) {
        Optional<Shipping> optionalShipping = shippingRepository.findById(shippingId);
        return optionalShipping.orElse(null);
    }

    @Override
    public List<Shipping> getAllShippings() {
        return shippingRepository.findAll();
    }

    @Override
    public Shipping updateShipping(Shipping shipping) {
        Optional<Shipping> optionalExistingShipping = shippingRepository.findById(shipping.getShippingId());
        if (optionalExistingShipping.isPresent()) {
            Shipping existingShipping = optionalExistingShipping.get();
            existingShipping.setName(shipping.getName());
            existingShipping.setDisplayName(shipping.getDisplayName());
            existingShipping.setActive(shipping.isActive());
            existingShipping.setIconPath(shipping.getIconPath());
            existingShipping.setFreeShipping(shipping.isFreeShipping());
            existingShipping.setRateType(shipping.getRateType());
            existingShipping.setUpdatedAt(LocalDateTime.now());

            Shipping updatedShipping = shippingRepository.save(existingShipping);
            return updatedShipping;
        }

        return null; // Hoặc xử lý theo yêu cầu của bạn khi không tìm thấy vận chuyển tồn tại
    }

    @Override
    public void deleteShipping(Integer shippingId) {
        shippingRepository.deleteById(shippingId);
    }
}
