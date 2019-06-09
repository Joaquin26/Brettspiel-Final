package com.brettspiel.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brettspiel.model.CreditCard;

public interface ICreditCartRepository extends JpaRepository<CreditCard, Integer> {

}
