package com.brettspiel.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brettspiel.model.CreditCard;

public interface ICreditCartRepository extends JpaRepository<CreditCard, Integer> {
	public Optional<CreditCard> findByNumber(Integer number);
}
