package com.vutheduyet.exercise03.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.vutheduyet.exercise03.entity.ProductShippingInfo;
import com.vutheduyet.exercise03.repository.ProductShippingInfoRepository;
import com.vutheduyet.exercise03.service.ProductShippingInfoService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductShippingInfoServiceImpl implements ProductShippingInfoService {
    private ProductShippingInfoRepository productShippingInfoRepository;

    @Override
    public ProductShippingInfo createProductShippingInfo(ProductShippingInfo productShippingInfo) {
        return productShippingInfoRepository.save(productShippingInfo);
    }

    @Override
    public ProductShippingInfo getProductShippingInfoById(String productShippingInfoId) {
        Optional<ProductShippingInfo> optionalProductShippingInfo = productShippingInfoRepository.findById(productShippingInfoId);
        return optionalProductShippingInfo.orElse(null);
    }

    @Override
    public List<ProductShippingInfo> getAllProductShippingInfos() {
        return productShippingInfoRepository.findAll();
    }

    @Override
    public ProductShippingInfo updateProductShippingInfo(ProductShippingInfo productShippingInfo) {
        Optional<ProductShippingInfo> optionalExistingProductShippingInfo = productShippingInfoRepository.findById(productShippingInfo.getProductShippingInfoId());
        if (optionalExistingProductShippingInfo.isPresent()) {
            ProductShippingInfo existingProductShippingInfo = optionalExistingProductShippingInfo.get();
            existingProductShippingInfo.setProduct(productShippingInfo.getProduct());
            existingProductShippingInfo.setWeight(productShippingInfo.getWeight());
            existingProductShippingInfo.setWeightUnit(productShippingInfo.getWeightUnit());
            existingProductShippingInfo.setVolume(productShippingInfo.getVolume());
            existingProductShippingInfo.setVolumeUnit(productShippingInfo.getVolumeUnit());
            existingProductShippingInfo.setDimensionWidth(productShippingInfo.getDimensionWidth());
            existingProductShippingInfo.setDimensionHeight(productShippingInfo.getDimensionHeight());
            existingProductShippingInfo.setDimensionDepth(productShippingInfo.getDimensionDepth());
            existingProductShippingInfo.setDimensionUnit(productShippingInfo.getDimensionUnit());

            ProductShippingInfo updatedProductShippingInfo = productShippingInfoRepository.save(existingProductShippingInfo);
            return updatedProductShippingInfo;
        }

        return null;
    }

    @Override
    public void deleteProductShippingInfo(String productShippingInfoId) {
        productShippingInfoRepository.deleteById(productShippingInfoId);
    }
}
