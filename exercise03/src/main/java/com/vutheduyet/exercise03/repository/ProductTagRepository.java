package com.vutheduyet.exercise03.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vutheduyet.exercise03.entity.ProductTag;

public interface ProductTagRepository extends JpaRepository<ProductTag, String> {
    List<ProductTag> findByProductProductId(String productId);
    List<ProductTag> findByTagTagId(String tagId);
}