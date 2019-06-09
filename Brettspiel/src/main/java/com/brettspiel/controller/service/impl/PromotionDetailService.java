package com.brettspiel.controller.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brettspiel.controller.service.IPromotionDetailService;
import com.brettspiel.model.PromotionDetail;
import com.brettspiel.model.repository.IPromotionDetailRepository;
@Service
public class PromotionDetailService implements IPromotionDetailService {

	@Autowired
	IPromotionDetailRepository promotionDetailRepository;
	
	@Override
	public PromotionDetail insert(PromotionDetail t) {
		// TODO Auto-generated method stub
		return promotionDetailRepository.save(t);
	}

	@Override
	public PromotionDetail update(PromotionDetail t) {
		// TODO Auto-generated method stub
		return promotionDetailRepository.save(t);
	}

	@Override
	public void Delete(int id) {
		promotionDetailRepository.deleteById(id);
		
	}

	@Override
	public Optional<PromotionDetail> findById(int id) {
		// TODO Auto-generated method stub
		return promotionDetailRepository.findById(id);
	}

	@Override
	public List<PromotionDetail> findAll() {
		return promotionDetailRepository.findAll();
	}
	
}
