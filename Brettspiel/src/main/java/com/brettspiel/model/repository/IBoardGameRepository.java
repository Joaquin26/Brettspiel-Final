package com.brettspiel.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.brettspiel.model.BoardGame;

public interface IBoardGameRepository extends JpaRepository<BoardGame, Integer> {
	@Query(value="select distinct b from BoardGame b join b.categories c " + 
			"where c.name like %:categoryName% and b.minAge>=:age and b.pricePerDay<=:maxCost and b.pricePerDay>=:minCost and "
			+ "b.maxNumberPlayers>=:minPlayers and b.name like %:name%")
	public List<BoardGame> filter(@Param("categoryName") String categoryId,@Param("age") Integer age,@Param("minCost") Float minCost,
			@Param("maxCost") Float maxCost,@Param("minPlayers") Integer minPlayers, @Param("name") String name);
	
	public Optional<BoardGame> findByName(String name);
	
	@Query(value="select distinct b from BoardGame b join b.playLists p " + 
			" where p.id=:id ")
	public List<BoardGame> findByPlayListId(@Param("id") Integer id);
	
}
