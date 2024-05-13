package com.vutheduyet.exercise03.service;

import java.util.List;

import com.vutheduyet.exercise03.entity.ProductAttribute;

public interface ProductAttributeService {
    ProductAttribute createProductAttribute(ProductAttribute productAttribute);
    ProductAttribute getProductAttributeById(String productAttributeId);
    List<ProductAttribute> getAllProductAttributes();
    ProductAttribute updateProductAttribute(ProductAttribute productAttribute);
    void deleteProductAttribute(String productAttributeId);
}