package com.brettspiel.controller.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brettspiel.controller.service.IWebCartService;
import com.brettspiel.model.WebCart;
import com.brettspiel.model.repository.IWebCartRepository;

@Service
public class WebCartService implements IWebCartService {

	@Autowired
	private IWebCartRepository webCartRepository;

	@Override
	public WebCart insert(WebCart t) {
		return webCartRepository.save(t);
	}

	@Override
	public WebCart update(WebCart t) {
		return webCartRepository.save(t);
	}

	@Override
	public void Delete(int id) {
		webCartRepository.deleteById(id);	
	}

	@Override
	public Optional<WebCart> findById(int id) {
		return webCartRepository.findById(id);
	}

	@Override
	public List<WebCart> findAll() {
		return webCartRepository.findAll();
	}

	@Override
	public Optional<WebCart> findByUserId(int userId) {
		return webCartRepository.findByUserId(userId);
	}

	
}
