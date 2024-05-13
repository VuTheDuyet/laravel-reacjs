package com.vutheduyet.exercise03.service;

import java.util.List;

import com.vutheduyet.exercise03.entity.ProductAttributeValue;

public interface ProductAttributeValueService {
    ProductAttributeValue createProductAttributeValue(ProductAttributeValue productAttributeValue);
    ProductAttributeValue getProductAttributeValueById(String productAttributeValueId);
    List<ProductAttributeValue> getAllProductAttributeValues();
    ProductAttributeValue updateProductAttributeValue(ProductAttributeValue productAttributeValue);
    void deleteProductAttributeValue(String productAttributeValueId);
}