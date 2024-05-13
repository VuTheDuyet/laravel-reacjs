package com.vutheduyet.exercise03.service;

import java.util.List;

import com.vutheduyet.exercise03.entity.VariantOption;

public interface VariantOptionService {
    VariantOption createVariantOption(VariantOption variantOption);
    VariantOption getVariantOptionById(String variantOptionId);
    List<VariantOption> getAllVariantOptions();
    VariantOption updateVariantOption(VariantOption variantOption);
    void deleteVariantOption(String variantOptionId);
}