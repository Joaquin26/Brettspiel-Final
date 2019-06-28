package com.brettspiel.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brettspiel.model.Copy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICopyRepository extends JpaRepository<Copy, Integer> {
    @Query(value = "SELECT * FROM copies c WHERE c.board_game_id = :id and c.available = true LIMIT :limit",
            nativeQuery = true)
    List<Copy> selectLimitAvailableByBoardGameId(
            @Param("id") Integer id,
            @Param("limit") Integer limit);
}
