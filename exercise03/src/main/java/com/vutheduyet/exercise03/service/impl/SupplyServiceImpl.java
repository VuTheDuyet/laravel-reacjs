package com.vutheduyet.exercise03.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.vutheduyet.exercise03.entity.Supply;
import com.vutheduyet.exercise03.repository.SupplyRepository;
import com.vutheduyet.exercise03.service.SupplyService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SupplyServiceImpl implements SupplyService {
    private SupplyRepository supplyRepository;

    @Override
    public Supply createSupply(Supply supply) {
        return supplyRepository.save(supply);
    }

    @Override
    public Supply getSupplyById(String supplyId) {
        Optional<Supply> optionalSupply = supplyRepository.findById(supplyId);
        return optionalSupply.orElse(null);
    }

    @Override
    public List<Supply> getAllSupplys() {
        return supplyRepository.findAll();
    }

    @Override
    public Supply updateSupply(Supply supply) {
        Optional<Supply> optionalExistingSupply = supplyRepository.findById(supply.getSupplyId());
        if (optionalExistingSupply.isPresent()) {
            Supply existingSupply = optionalExistingSupply.get();
            // Update fields accordingly based on your Supply entity

            Supply updatedSupply = supplyRepository.save(existingSupply);
            return updatedSupply;
        }

        return null;
    }

    @Override
    public void deleteSupply(String supplyId) {
        supplyRepository.deleteById(supplyId);
    }
}
