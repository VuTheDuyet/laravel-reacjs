package com.vutheduyet.exercise03.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.vutheduyet.exercise03.entity.VariantOption;
import com.vutheduyet.exercise03.repository.VariantOptionRepository;
import com.vutheduyet.exercise03.service.VariantOptionService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VariantOptionServiceImpl implements VariantOptionService {
    private VariantOptionRepository variantOptionRepository;

    @Override
    public VariantOption createVariantOption(VariantOption variantOption) {
        return variantOptionRepository.save(variantOption);
    }

    @Override
    public VariantOption getVariantOptionById(String variantOptionId) {
        Optional<VariantOption> optionalVariantOption = variantOptionRepository.findById(variantOptionId);
        return optionalVariantOption.orElse(null);
    }

    @Override
    public List<VariantOption> getAllVariantOptions() {
        return variantOptionRepository.findAll();
    }

    @Override
    public VariantOption updateVariantOption(VariantOption variantOption) {
        Optional<VariantOption> optionalExistingVariantOption = variantOptionRepository.findById(variantOption.getVariantOptionId());
        if (optionalExistingVariantOption.isPresent()) {
            VariantOption existingVariantOption = optionalExistingVariantOption.get();
            existingVariantOption.setTitle(variantOption.getTitle());
            existingVariantOption.setSalePrice(variantOption.getSalePrice());
            existingVariantOption.setComparePrice(variantOption.getComparePrice());
            existingVariantOption.setBuyingPrice(variantOption.getBuyingPrice());
            existingVariantOption.setQuantity(variantOption.getQuantity());
            existingVariantOption.setSku(variantOption.getSku());
            existingVariantOption.setActive(variantOption.getActive());

            VariantOption updatedVariantOption = variantOptionRepository.save(existingVariantOption);
            return updatedVariantOption;
        }

        return null; // Hoặc xử lý theo yêu cầu của bạn khi không tìm thấy tùy chọn biến thể tồn tại
    }

    @Override
    public void deleteVariantOption(String variantOptionId) {
        variantOptionRepository.deleteById(variantOptionId);
    }
}
