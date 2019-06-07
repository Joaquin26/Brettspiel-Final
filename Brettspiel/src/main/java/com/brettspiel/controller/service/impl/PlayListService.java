package com.brettspiel.controller.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brettspiel.controller.service.IPlayListService;
import com.brettspiel.model.Playlist;
import com.brettspiel.model.repository.IPlayListRepository;

@Service
public class PlayListService implements IPlayListService {

	@Autowired
	private IPlayListRepository playListRepository;
	
	@Override
	public Playlist insert(Playlist t) {
		return playListRepository.save(t);
	}

	@Override
	public Playlist update(Playlist t) {
		return playListRepository.save(t);
	}

	@Override
	public void Delete(int id) {
		playListRepository.deleteById(id);
	}

	@Override
	public Optional<Playlist> findById(int id) {
		return playListRepository.findById(id);
	}

	@Override
	public List<Playlist> findAll() {
		return playListRepository.findAll();
	}

}
