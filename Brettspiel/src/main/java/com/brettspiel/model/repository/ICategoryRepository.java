package com.brettspiel.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brettspiel.model.Category;

public interface ICategoryRepository extends JpaRepository<Category, Integer> {
	public Optional<Category> findByName(String name);
}
