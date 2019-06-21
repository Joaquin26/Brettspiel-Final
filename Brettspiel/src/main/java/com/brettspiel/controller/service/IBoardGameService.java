package com.brettspiel.controller.service;

import java.util.List;
import java.util.Optional;

import com.brettspiel.model.BoardGame;

public interface IBoardGameService extends ICrudService<BoardGame> {
	public List<BoardGame> filter(String categoryName,Integer age,Float minCost,Float maxCost,Integer minPlayers,String name);
	public Optional<BoardGame> findByName(String name);
}
