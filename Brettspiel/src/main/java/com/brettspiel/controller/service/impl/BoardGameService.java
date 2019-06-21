package com.brettspiel.controller.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brettspiel.controller.service.IBoardGameService;
import com.brettspiel.model.BoardGame;
import com.brettspiel.model.repository.IBoardGameRepository;

@Service
public class BoardGameService implements IBoardGameService {

	@Autowired
	private IBoardGameRepository boardGameRepository;
	
	@Override
	public BoardGame insert(BoardGame t) {
		return boardGameRepository.save(t);
	}

	@Override
	public BoardGame update(BoardGame t) {
		return boardGameRepository.save(t);
	}

	@Override
	public void Delete(int id) {
		boardGameRepository.deleteById(id);
	}

	@Override
	public Optional<BoardGame> findById(int id) {
		return boardGameRepository.findById(id);
	}

	@Override
	public List<BoardGame> findAll() {
		return boardGameRepository.findAll();
	}

	@Override
	public List<BoardGame> filter(String categoryName,Integer age, Float minCost,Float maxCost, Integer minPlayers) {
		return boardGameRepository.filter(categoryName,age,minCost,maxCost,minPlayers);
	}

}
