package com.vutheduyet.exercise03.service;

import java.util.List;

import com.vutheduyet.exercise03.entity.ProductTag;

public interface ProductTagService {
    ProductTag createProductTag(ProductTag productTag);
    ProductTag getProductTagById(String productTagId);
    List<ProductTag> getAllProductTags();
    List<ProductTag> getProductTagsByProductId(String productId);
    ProductTag updateProductTag(ProductTag productTag);
    void deleteProductTag(String productTagId);
    void deleteProductTagsByProductId(String productId);
    void deleteProductTagsByTagId(String tagId);
    void deleteProductTagsByTagIdFromProduct(String productId, String tagId); 
}