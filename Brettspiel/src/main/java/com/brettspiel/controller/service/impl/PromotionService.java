package com.brettspiel.controller.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brettspiel.controller.service.IPromotionService;
import com.brettspiel.model.Promotion;
import com.brettspiel.model.repository.IPromotionRepository;
@Service
public class PromotionService implements IPromotionService {

	@Autowired
	IPromotionRepository promotionRepository;
	
	@Override
	public Promotion insert(Promotion t) {
		// TODO Auto-generated method stub
		return promotionRepository.save(t);
	}

	@Override
	public Promotion update(Promotion t) {
		// TODO Auto-generated method stub
		return promotionRepository.save(t);
	}

	@Override
	public void Delete(int id) {
		promotionRepository.deleteById(id);
		
	}

	@Override
	public Optional<Promotion> findById(int id) {
		// TODO Auto-generated method stub
		return promotionRepository.findById(id);
	}

	@Override
	public List<Promotion> findAll() {
		return promotionRepository.findAll();
	}
	
}
