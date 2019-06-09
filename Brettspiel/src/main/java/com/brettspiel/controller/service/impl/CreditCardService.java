package com.brettspiel.controller.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brettspiel.controller.service.ICreditCardService;
import com.brettspiel.model.CreditCard;
import com.brettspiel.model.repository.ICreditCartRepository;

@Service
public class CreditCardService implements ICreditCardService {

	@Autowired
	private ICreditCartRepository creditCartRepository;
	
	@Override
	public CreditCard insert(CreditCard t) {
		return creditCartRepository.save(t);
	}

	@Override
	public CreditCard update(CreditCard t) {
		return creditCartRepository.save(t);
	}

	@Override
	public void Delete(int id) {
		creditCartRepository.deleteById(id);
	}

	@Override
	public Optional<CreditCard> findById(int id) {
		return creditCartRepository.findById(id);
	}

	@Override
	public List<CreditCard> findAll() {
		return creditCartRepository.findAll();
	}
	

}
