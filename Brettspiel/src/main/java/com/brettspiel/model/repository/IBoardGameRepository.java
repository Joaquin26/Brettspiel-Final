package com.brettspiel.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brettspiel.model.BoardGame;

public interface IBoardGameRepository extends JpaRepository<BoardGame, Integer> {

}
