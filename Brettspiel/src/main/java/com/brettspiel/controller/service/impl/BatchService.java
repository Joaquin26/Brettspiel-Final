package com.brettspiel.controller.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brettspiel.controller.service.IBatchService;
import com.brettspiel.model.Batch;
import com.brettspiel.model.repository.IBatchRepository;
@Service
public class BatchService implements IBatchService {

	@Autowired
	IBatchRepository batchRepository;
	
	@Override
	public Batch insert(Batch t) {
		// TODO Auto-generated method stub
		return batchRepository.save(t);
	}

	@Override
	public Batch update(Batch t) {
		// TODO Auto-generated method stub
		return batchRepository.save(t);
	}

	@Override
	public void Delete(int id) {
		batchRepository.deleteById(id);
		
	}

	@Override
	public Optional<Batch> findById(int id) {
		// TODO Auto-generated method stub
		return batchRepository.findById(id);
	}

	@Override
	public List<Batch> findAll() {
		return batchRepository.findAll();
	}

	@Override
	public Optional<Batch> selectBySnackId(Integer id) {
		return batchRepository.selectBySnackId(id);
	}
}
