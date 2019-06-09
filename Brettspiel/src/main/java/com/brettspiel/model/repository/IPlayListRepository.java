package com.brettspiel.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brettspiel.model.PlayList;

public interface IPlayListRepository extends JpaRepository<PlayList, Integer> {

}
