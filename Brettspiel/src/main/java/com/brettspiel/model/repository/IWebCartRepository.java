package com.brettspiel.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brettspiel.model.WebCart;
public interface IWebCartRepository extends JpaRepository<WebCart, Integer> {
	public Optional<WebCart> findByUserId(int userId);
}
