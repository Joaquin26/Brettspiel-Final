package com.brettspiel.controller.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brettspiel.controller.service.IPlayListDetailService;
import com.brettspiel.model.PlayListDetail;
import com.brettspiel.model.repository.IPlayListDetailRepository;

@Service
public class PlayListDetailService implements IPlayListDetailService {

	@Autowired
	private IPlayListDetailRepository playListDetailRepository;

	@Override
	public PlayListDetail insert(PlayListDetail t) {
		return playListDetailRepository.save(t);
	}

	@Override
	public PlayListDetail update(PlayListDetail t) {
		return playListDetailRepository.save(t);
	}

	@Override
	public void Delete(int id) {
		playListDetailRepository.deleteById(id);	
	}

	@Override
	public Optional<PlayListDetail> findById(int id) {
		return playListDetailRepository.findById(id);
	}

	@Override
	public List<PlayListDetail> findAll() {
		return playListDetailRepository.findAll();
	}

	@Override
	public List<PlayListDetail> findByPlayListId(int id) {
		return playListDetailRepository.findByPlayListId(id);
	}

	
}
