package com.vutheduyet.exercise03.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.vutheduyet.exercise03.entity.ProductSupply;
import com.vutheduyet.exercise03.repository.ProductSupplyRepository;
import com.vutheduyet.exercise03.service.ProductSupplyService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductSupplyServiceImpl implements ProductSupplyService {
    private ProductSupplyRepository productSupplyRepository;

    @Override
    public ProductSupply createProductSupply(ProductSupply productSupply) {
        return productSupplyRepository.save(productSupply);
    }

    @Override
    public ProductSupply getProductSupplyById(String productId) {
        Optional<ProductSupply> optionalProductSupply = productSupplyRepository.findById(productId);
        return optionalProductSupply.orElse(null);
    }

    @Override
    public List<ProductSupply> getAllProductSupplys() {
        return productSupplyRepository.findAll();
    }

    @Override
    public ProductSupply updateProductSupply(ProductSupply productSupply) {
        Optional<ProductSupply> optionalExistingProductSupply = productSupplyRepository.findById(productSupply.getProductSupplyId());
        if (optionalExistingProductSupply.isPresent()) {
            ProductSupply existingProductSupply = optionalExistingProductSupply.get();
            existingProductSupply.setProduct(productSupply.getProduct());
            existingProductSupply.setSupply(productSupply.getSupply());
            

            ProductSupply updatedProductSupply = productSupplyRepository.save(existingProductSupply);
            return updatedProductSupply;
        }

        return null;
    }

    @Override
    public void deleteProductSupply(String productId) {
        productSupplyRepository.deleteById(productId);
    }
}
