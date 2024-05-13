package com.vutheduyet.exercise03.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vutheduyet.exercise03.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    @Query("SELECT p FROM Product p JOIN p.productTags pt WHERE pt.tag.tagId = :tagId")
    List<Product> findProductsByTagId(String tagId);

    @Query("SELECT p FROM Product p WHERE p.productName LIKE :productName%")
    List<Product> searchProductsByName(@Param("productName") String productName);

    @Query("SELECT p FROM Product p ORDER BY p.createdAt DESC, p.updatedAt DESC")
    List<Product> findAllOrderByCreatedAtAndUpdatedAtDesc();
}