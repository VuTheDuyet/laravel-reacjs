package com.vutheduyet.exercise03.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vutheduyet.exercise03.entity.Card;

public interface CardRepository extends JpaRepository<Card, String> {

}
