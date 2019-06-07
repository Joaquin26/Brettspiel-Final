package com.brettspiel.controller.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brettspiel.controller.service.IWebCartDetailService;
import com.brettspiel.model.WebCartDetail;
import com.brettspiel.model.repository.IWebCartDetailRepository;

@Service
public class WebCartDetailService implements IWebCartDetailService {

	@Autowired
	private IWebCartDetailRepository webCartDetailRepository;
	
	@Override
	public WebCartDetail insert(WebCartDetail t) {
		return webCartDetailRepository.save(t);
	}

	@Override
	public WebCartDetail update(WebCartDetail t) {
		return webCartDetailRepository.save(t);
	}

	@Override
	public void Delete(int id) {
		webCartDetailRepository.deleteById(id);
	}

	@Override
	public Optional<WebCartDetail> findById(int id) {
		return webCartDetailRepository.findById(id);
	}

	@Override
	public List<WebCartDetail> findAll() {
		return webCartDetailRepository.findAll();
	}

}
