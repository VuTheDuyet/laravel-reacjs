package com.vutheduyet.exercise03.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vutheduyet.exercise03.entity.ProductAttribute;

public interface ProductAttributeRepository extends JpaRepository<ProductAttribute, String> {

}