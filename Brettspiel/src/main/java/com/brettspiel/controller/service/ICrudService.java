package com.brettspiel.controller.service;

import java.util.List;
import java.util.Optional;

public interface ICrudService<T> {
	T insert(T t);
	T update(T t);
	void Delete(int id);
	Optional<T> findById(int id);
	List<T> findAll();
}
