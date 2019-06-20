package com.brettspiel.controller.service;

import java.util.List;

import com.brettspiel.model.BoardGame;

public interface IBoardGameService extends ICrudService<BoardGame> {
	public List<BoardGame> filter(String categoryName,Integer age,Float minCost,Float maxCost,Integer minPlayers);
}
