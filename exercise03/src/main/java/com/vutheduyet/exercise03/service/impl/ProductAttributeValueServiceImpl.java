package com.vutheduyet.exercise03.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.vutheduyet.exercise03.entity.ProductAttributeValue;
import com.vutheduyet.exercise03.repository.ProductAttributeValueRepository;
import com.vutheduyet.exercise03.service.ProductAttributeValueService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductAttributeValueServiceImpl implements ProductAttributeValueService {
    private ProductAttributeValueRepository productAttributeValueRepository;

    @Override
    public ProductAttributeValue createProductAttributeValue(ProductAttributeValue productAttributeValue) {
        return productAttributeValueRepository.save(productAttributeValue);
    }

    @Override
    public ProductAttributeValue getProductAttributeValueById(String productAttributeValueId) {
        Optional<ProductAttributeValue> optionalProductAttributeValue = productAttributeValueRepository.findById(productAttributeValueId);
        return optionalProductAttributeValue.orElse(null);
    }

    @Override
    public List<ProductAttributeValue> getAllProductAttributeValues() {
        return productAttributeValueRepository.findAll();
    }

    @Override
    public ProductAttributeValue updateProductAttributeValue(ProductAttributeValue productAttributeValue) {
        Optional<ProductAttributeValue> optionalExistingProductAttributeValue = productAttributeValueRepository.findById(productAttributeValue.getProductAttributeValueId());
        if (optionalExistingProductAttributeValue.isPresent()) {
            ProductAttributeValue existingProductAttributeValue = optionalExistingProductAttributeValue.get();
            existingProductAttributeValue.setProductAttribute(productAttributeValue.getProductAttribute());
            existingProductAttributeValue.setAttributeValue(productAttributeValue.getAttributeValue());

            ProductAttributeValue updatedProductAttributeValue = productAttributeValueRepository.save(existingProductAttributeValue);
            return updatedProductAttributeValue;
        }

        return null;
    }

    @Override
    public void deleteProductAttributeValue(String productAttributeValueId) {
        productAttributeValueRepository.deleteById(productAttributeValueId);
    }
}
