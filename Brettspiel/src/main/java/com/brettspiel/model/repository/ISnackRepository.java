package com.brettspiel.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brettspiel.model.Snack;

public interface ISnackRepository extends JpaRepository<Snack, Integer> {

}
