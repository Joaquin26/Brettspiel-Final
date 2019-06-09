package com.brettspiel.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brettspiel.model.Promotion;

public interface IPromotionRepository extends JpaRepository<Promotion, Integer>  {

}
