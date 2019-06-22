package com.brettspiel.controller.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brettspiel.controller.service.IPlayListService;
import com.brettspiel.model.PlayList;
import com.brettspiel.model.repository.IPlayListRepository;

@Service
public class PlayListService implements IPlayListService {

	@Autowired
	private IPlayListRepository playListRepository;
	
	@Override
	public PlayList insert(PlayList t) {
		return playListRepository.save(t);
	}

	@Override
	public PlayList update(PlayList t) {
		return playListRepository.save(t);
	}

	@Override
	public void Delete(int id) {
		playListRepository.deleteById(id);
	}

	@Override
	public Optional<PlayList> findById(int id) {
		return playListRepository.findById(id);
	}

	@Override
	public List<PlayList> findAll() {
		return playListRepository.findAll();
	}

	@Override
	public List<PlayList> findByUserId(int id) {
		// TODO Auto-generated method stub
		return playListRepository.findByUserId(id);
	}

}
