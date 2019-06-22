package com.brettspiel.controller.service;

import java.util.Optional;

import com.brettspiel.model.Category;

public interface ICategoryService extends ICrudService<Category> {
	public Optional<Category> findByName(String name);
}
