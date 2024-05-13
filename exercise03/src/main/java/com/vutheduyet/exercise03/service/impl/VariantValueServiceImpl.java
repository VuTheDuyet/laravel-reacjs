package com.vutheduyet.exercise03.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.vutheduyet.exercise03.entity.VariantValue;
import com.vutheduyet.exercise03.repository.VariantValueRepository;
import com.vutheduyet.exercise03.service.VariantValueService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VariantValueServiceImpl implements VariantValueService {
    private VariantValueRepository variantValueRepository;

    @Override
    public VariantValue createVariantValue(VariantValue variantValue) {
        return variantValueRepository.save(variantValue);
    }

    @Override
    public VariantValue getVariantValueById(String variantValueId) {
        Optional<VariantValue> optionalVariantValue = variantValueRepository.findById(variantValueId);
        return optionalVariantValue.orElse(null);
    }

    @Override
    public List<VariantValue> getAllVariantValues() {
        return variantValueRepository.findAll();
    }

    @Override
    public VariantValue updateVariantValue(VariantValue variantValue) {
        Optional<VariantValue> optionalExistingVariantValue = variantValueRepository.findById(variantValue.getVariantValueId());
        if (optionalExistingVariantValue.isPresent()) {
            VariantValue existingVariantValue = optionalExistingVariantValue.get();
            existingVariantValue.setVariant(variantValue.getVariant());
            existingVariantValue.setProductAttributeValue(variantValue.getProductAttributeValue());

            VariantValue updatedVariantValue = variantValueRepository.save(existingVariantValue);
            return updatedVariantValue;
        }

        return null; // Hoặc xử lý theo yêu cầu của bạn khi không tìm thấy giá trị biến thể tồn tại
    }

    @Override
    public void deleteVariantValue(String variantValueId) {
        variantValueRepository.deleteById(variantValueId);
    }
}
