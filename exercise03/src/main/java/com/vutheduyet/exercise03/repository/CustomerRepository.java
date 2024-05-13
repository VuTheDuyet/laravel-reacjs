package com.vutheduyet.exercise03.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vutheduyet.exercise03.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {

}