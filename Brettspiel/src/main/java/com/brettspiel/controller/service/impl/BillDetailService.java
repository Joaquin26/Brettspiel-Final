package com.brettspiel.controller.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brettspiel.controller.service.IBillDetailService;
import com.brettspiel.model.BillDetail;
import com.brettspiel.model.repository.IBillDetailRepository;
@Service
public class BillDetailService implements IBillDetailService {

	@Autowired
	IBillDetailRepository billDetailRepository;
	
	@Override
	public BillDetail insert(BillDetail t) {
		// TODO Auto-generated method stub
		return billDetailRepository.save(t);
	}

	@Override
	public BillDetail update(BillDetail t) {
		// TODO Auto-generated method stub
		return billDetailRepository.save(t);
	}

	@Override
	public void Delete(int id) {
		billDetailRepository.deleteById(id);
		
	}

	@Override
	public Optional<BillDetail> findById(int id) {
		// TODO Auto-generated method stub
		return billDetailRepository.findById(id);
	}

	@Override
	public List<BillDetail> findAll() {
		return billDetailRepository.findAll();
	}
	
}
