package com.brettspiel.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brettspiel.model.Batch;
import com.brettspiel.model.Promotion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IBatchRepository extends JpaRepository<Batch, Integer>  {
    @Query(value = "SELECT b FROM Batch b WHERE b.snack.id = :id")
    Optional<Batch> selectBySnackId(
            @Param("id") Integer id);
}
