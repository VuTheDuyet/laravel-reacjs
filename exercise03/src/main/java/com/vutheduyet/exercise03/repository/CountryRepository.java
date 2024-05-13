package com.vutheduyet.exercise03.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vutheduyet.exercise03.entity.Country;

public interface CountryRepository extends JpaRepository<Country, Integer> {

}