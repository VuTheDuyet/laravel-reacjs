package com.vutheduyet.exercise03.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vutheduyet.exercise03.entity.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, String> {
    List<ProductCategory> findByProductProductId(String productId);
}