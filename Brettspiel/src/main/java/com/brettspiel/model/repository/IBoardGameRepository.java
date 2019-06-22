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
	
	
	/*@Query(value="SELECT * FROM public.boardgames b inner join public.boardgame_categories bc on "
			+ "b.id=bc.boardgame_id inner join public.categories c on c.id=bc.category_id "
			+ "where c.category_id=:categoryId::integer", nativeQuery = true)
	public List<BoardGame> filter(@Param("categoryId") Integer categoryId);*/
//and :minCost<=b.pricePerDay
	//@Query("Select b from BoardGame b inner join b.categories c where c.id=:categoryId and b.minAge>=:age and b.pricePerDay<=:maxCost and b.pricePerDay>=:minCost")
	//public List<BoardGame> filter(@Param("age") Integer age,@Param("minCost") Integer minCost,@Param("maxCost") Integer maxCost,@Param("categoryId") Integer categoryId);
}
