package com.brettspiel.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brettspiel.model.PlayList;

public interface IPlayListRepository extends JpaRepository<PlayList, Integer> {

	public List<PlayList> findByUserId(int Id);
}
