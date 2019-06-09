package com.brettspiel.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brettspiel.model.PlayList;
import com.brettspiel.model.PromotionDetail;

public interface IPromotionDetailRepository extends JpaRepository<PromotionDetail, Integer> {

}
