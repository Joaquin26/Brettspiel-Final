package com.brettspiel.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brettspiel.model.Copy;

public interface ICopyRepository extends JpaRepository<Copy, Integer> {

}
