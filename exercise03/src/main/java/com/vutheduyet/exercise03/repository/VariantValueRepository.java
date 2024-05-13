package com.vutheduyet.exercise03.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vutheduyet.exercise03.entity.VariantValue;

public interface VariantValueRepository extends JpaRepository<VariantValue, String> {

}