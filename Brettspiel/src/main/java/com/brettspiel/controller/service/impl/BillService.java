package com.brettspiel.controller.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brettspiel.controller.service.IBillService;
import com.brettspiel.model.Bill;
import com.brettspiel.model.repository.IBillRepository;
@Service
public class BillService implements IBillService {

	@Autowired
	private IBillRepository billRepository;
	
	@Override
	public Bill insert(Bill t) {
		// TODO Auto-generated method stub
		return billRepository.save(t);
	}

	@Override
	public Bill update(Bill t) {
		// TODO Auto-generated method stub
		return billRepository.save(t);
	}

	@Override
	public void Delete(int id) {
		billRepository.deleteById(id);
		
	}

	@Override
	public Optional<Bill> findById(int id) {
		// TODO Auto-generated method stub
		return billRepository.findById(id);
	}

	@Override
	public List<Bill> findAll() {
		return billRepository.findAll();
	}
	
}
