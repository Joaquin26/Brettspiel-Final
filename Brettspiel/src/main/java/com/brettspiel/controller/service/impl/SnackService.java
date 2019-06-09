package com.brettspiel.controller.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brettspiel.controller.service.ISnackService;
import com.brettspiel.model.Snack;
import com.brettspiel.model.repository.ISnackRepository;

@Service
public class SnackService implements ISnackService {

	@Autowired
	private ISnackRepository snackRepository;
	
	@Override
	public Snack insert(Snack t) {
		return snackRepository.save(t);
	}

	@Override
	public Snack update(Snack t) {
		return snackRepository.save(t);
	}

	@Override
	public void Delete(int id) {
		snackRepository.deleteById(id);
	}

	@Override
	public Optional<Snack> findById(int id) {
		return snackRepository.findById(id);
	}

	@Override
	public List<Snack> findAll() {
		return snackRepository.findAll();
	}

}
