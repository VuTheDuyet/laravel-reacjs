package com.vutheduyet.exercise03.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vutheduyet.exercise03.entity.Attribute;

public interface AttributeRepository extends JpaRepository<Attribute, String> {

}