package com.vutheduyet.exercise03.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.vutheduyet.exercise03.entity.Variant;
import com.vutheduyet.exercise03.repository.VariantRepository;
import com.vutheduyet.exercise03.service.VariantService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VariantServiceImpl implements VariantService {
    private VariantRepository variantRepository;

    @Override
    public Variant createVariant(Variant variant) {
        return variantRepository.save(variant);
    }

    @Override
    public Variant getVariantById(String variantId) {
        Optional<Variant> optionalVariant = variantRepository.findById(variantId);
        return optionalVariant.orElse(null);
    }

    @Override
    public List<Variant> getAllVariants() {
        return variantRepository.findAll();
    }

    @Override
    public Variant updateVariant(Variant variant) {
        Optional<Variant> optionalExistingVariant = variantRepository.findById(variant.getVariantId());
        if (optionalExistingVariant.isPresent()) {
            Variant existingVariant = optionalExistingVariant.get();
            existingVariant.setVariantOption(variant.getVariantOption());

            Variant updatedVariant = variantRepository.save(existingVariant);
            return updatedVariant;
        }

        return null; // Hoặc xử lý theo yêu cầu của bạn khi không tìm thấy biến thể tồn tại
    }

    @Override
    public void deleteVariant(String variantId) {
        variantRepository.deleteById(variantId);
    }
}
