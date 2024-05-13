package com.vutheduyet.exercise03.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vutheduyet.exercise03.entity.StaffAccount;

public interface StaffAccountRepository extends JpaRepository<StaffAccount, String> {

}