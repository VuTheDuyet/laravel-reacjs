package com.vutheduyet.exercise03.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vutheduyet.exercise03.entity.Variant;

public interface VariantRepository extends JpaRepository<Variant, String> {

}