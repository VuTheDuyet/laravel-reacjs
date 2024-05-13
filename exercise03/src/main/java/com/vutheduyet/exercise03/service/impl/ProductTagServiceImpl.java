package com.vutheduyet.exercise03.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.vutheduyet.exercise03.entity.ProductTag;
import com.vutheduyet.exercise03.repository.ProductTagRepository;
import com.vutheduyet.exercise03.service.ProductTagService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductTagServiceImpl implements ProductTagService {
    private ProductTagRepository productTagRepository;

    @Override
    public ProductTag createProductTag(ProductTag productTag) {
        return productTagRepository.save(productTag);
    }

    @Override
    public ProductTag getProductTagById(String productTagId) {
        Optional<ProductTag> optionalProductTag = productTagRepository.findById(productTagId);
        return optionalProductTag.orElse(null);
    }

    @Override
    public List<ProductTag> getAllProductTags() {
        return productTagRepository.findAll();
    }

    @Override
    public ProductTag updateProductTag(ProductTag productTag) {
        Optional<ProductTag> optionalExistingProductTag = productTagRepository.findById(productTag.getProductTagId());
        if (optionalExistingProductTag.isPresent()) {
            ProductTag existingProductTag = optionalExistingProductTag.get();
            existingProductTag.setTag(productTag.getTag()); // Sửa dòng này để cập nhật thẻ của ProductTag
            existingProductTag.setProduct(productTag.getProduct()); // Sửa dòng này để cập nhật sản phẩm của ProductTag

            ProductTag updatedProductTag = productTagRepository.save(existingProductTag);
            return updatedProductTag;
        }

        return null; // Hoặc xử lý theo yêu cầu của bạn khi không tìm thấy ProductTag tồn tại
    }

    @Override
    public void deleteProductTagsByProductId(String productId) {
        List<ProductTag> productTags = productTagRepository.findByProductProductId(productId);
        productTagRepository.deleteAll(productTags);
    }

    @Override
    public void deleteProductTag(String productTagId) {
        productTagRepository.deleteById(productTagId);
    }

    @Override
    public void deleteProductTagsByTagId(String tagId) {
        List<ProductTag> productTags = productTagRepository.findByTagTagId(tagId);
        productTagRepository.deleteAll(productTags);
    }

    @Override
    public List<ProductTag> getProductTagsByProductId(String productId) {
        return productTagRepository.findByProductProductId(productId);
    }

    @Override
    public void deleteProductTagsByTagIdFromProduct(String productId, String tagId) {
        List<ProductTag> productTags = productTagRepository.findByProductProductId(productId);
        for (ProductTag productTag : productTags) {
            if (productTag.getTag().getTagId().equals(tagId)) {
                productTagRepository.delete(productTag);
            }
        }
    }
}
