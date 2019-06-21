package com.brettspiel.controller.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brettspiel.controller.service.ICategoryService;
import com.brettspiel.model.Category;
import com.brettspiel.model.repository.ICategoryRepository;

@Service
public class CategoryService implements ICategoryService {

	@Autowired
	private ICategoryRepository categoryRepository;
	
	@Override
	public Category insert(Category t) {
		return categoryRepository.save(t);
	}

	@Override
	public Category update(Category t) {
		return categoryRepository.save(t);
	}

	@Override
	public void Delete(int id) {
		categoryRepository.deleteById(id);
	}

	@Override
	public Optional<Category> findById(int id) {
		return categoryRepository.findById(id);
	}

	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	public Optional<Category> findByName(String name) {
		return categoryRepository.findByName(name);
	}

}
