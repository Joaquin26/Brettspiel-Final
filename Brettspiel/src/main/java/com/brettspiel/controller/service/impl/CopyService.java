package com.brettspiel.controller.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brettspiel.controller.service.ICopyService;
import com.brettspiel.model.Copy;
import com.brettspiel.model.repository.ICopyRepository;

@Service
public class CopyService implements ICopyService {

	@Autowired
	private ICopyRepository copyRepository;
	
	@Override
	public Copy insert(Copy t) {
		return copyRepository.save(t);
	}

	@Override
	public Copy update(Copy t) {
		return copyRepository.save(t);
	}

	@Override
	public void Delete(int id) {
		copyRepository.deleteById(id);
	}

	@Override
	public Optional<Copy> findById(int id) {
		return copyRepository.findById(id);
	}

	@Override
	public List<Copy> findAll() {
		return copyRepository.findAll();
	}
	

}
