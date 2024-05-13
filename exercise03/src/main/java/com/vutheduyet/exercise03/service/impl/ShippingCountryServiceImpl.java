package com.vutheduyet.exercise03.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.vutheduyet.exercise03.entity.ShippingCountry;
import com.vutheduyet.exercise03.repository.ShippingCountryRepository;
import com.vutheduyet.exercise03.service.ShippingCountryService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ShippingCountryServiceImpl implements ShippingCountryService {
    private ShippingCountryRepository shippingCountryRepository;

    @Override
    public ShippingCountry createShippingCountry(ShippingCountry shippingCountry) {
        return shippingCountryRepository.save(shippingCountry);
    }

    @Override
    public ShippingCountry getShippingCountryById(String shippingCountryId) {
        Optional<ShippingCountry> optionalShippingCountry = shippingCountryRepository.findById(shippingCountryId);
        return optionalShippingCountry.orElse(null);
    }

    @Override
    public List<ShippingCountry> getAllShippingCountrys() {
        return shippingCountryRepository.findAll();
    }

    @Override
    public ShippingCountry updateShippingCountry(ShippingCountry shippingCountry) {
        Optional<ShippingCountry> optionalExistingShippingCountry = shippingCountryRepository.findById(shippingCountry.getShippingCountryId());
        if (optionalExistingShippingCountry.isPresent()) {
            ShippingCountry existingShippingCountry = optionalExistingShippingCountry.get();
            // Update fields accordingly based on your ShippingCountry entity

            ShippingCountry updatedShippingCountry = shippingCountryRepository.save(existingShippingCountry);
            return updatedShippingCountry;
        }

        return null;
    }

    @Override
    public void deleteShippingCountry(String shippingCountryId) {
        shippingCountryRepository.deleteById(shippingCountryId);
    }
}
