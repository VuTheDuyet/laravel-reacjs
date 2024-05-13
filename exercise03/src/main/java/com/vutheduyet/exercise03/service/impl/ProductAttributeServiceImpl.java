package com.vutheduyet.exercise03.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.vutheduyet.exercise03.entity.ProductAttribute;
import com.vutheduyet.exercise03.repository.ProductAttributeRepository;
import com.vutheduyet.exercise03.service.ProductAttributeService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductAttributeServiceImpl implements ProductAttributeService {
    private ProductAttributeRepository productAttributeRepository;

    @Override
    public ProductAttribute createProductAttribute(ProductAttribute productAttribute) {
        return productAttributeRepository.save(productAttribute);
    }

    @Override
    public ProductAttribute getProductAttributeById(String productAttributeId) {
        Optional<ProductAttribute> optionalProductAttribute = productAttributeRepository.findById(productAttributeId);
        return optionalProductAttribute.orElse(null);
    }

    @Override
    public List<ProductAttribute> getAllProductAttributes() {
        return productAttributeRepository.findAll();
    }

    @Override
    public ProductAttribute updateProductAttribute(ProductAttribute productAttribute) {
        Optional<ProductAttribute> optionalExistingProductAttribute = productAttributeRepository.findById(productAttribute.getProductAttributeId());
        if (optionalExistingProductAttribute.isPresent()) {
            ProductAttribute existingProductAttribute = optionalExistingProductAttribute.get();
            existingProductAttribute.setProduct(productAttribute.getProduct());
            existingProductAttribute.setAttribute(productAttribute.getAttribute());

            ProductAttribute updatedProductAttribute = productAttributeRepository.save(existingProductAttribute);
            return updatedProductAttribute;
        }

        return null;
    }

    @Override
    public void deleteProductAttribute(String productAttributeId) {
        productAttributeRepository.deleteById(productAttributeId);
    }
}
