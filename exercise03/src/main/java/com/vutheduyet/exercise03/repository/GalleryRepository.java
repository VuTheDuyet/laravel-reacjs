package com.vutheduyet.exercise03.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vutheduyet.exercise03.entity.Gallery;

@Repository
public interface GalleryRepository extends JpaRepository<Gallery, String> {
    List<Gallery> findByProductProductId(String productId);
}