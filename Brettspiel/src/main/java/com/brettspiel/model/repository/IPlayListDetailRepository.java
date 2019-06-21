package com.brettspiel.model.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.brettspiel.model.PlayListDetail;


public interface IPlayListDetailRepository extends JpaRepository<PlayListDetail, Integer> {
	
	public List<PlayListDetail> findByPlayListId(int Id);
}
