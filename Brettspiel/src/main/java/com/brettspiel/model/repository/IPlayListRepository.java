package com.brettspiel.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brettspiel.model.Playlist;

public interface IPlayListRepository extends JpaRepository<Playlist, Integer> {

}
