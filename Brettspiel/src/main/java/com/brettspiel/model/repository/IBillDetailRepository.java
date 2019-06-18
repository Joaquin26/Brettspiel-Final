package com.brettspiel.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brettspiel.model.Batch;
import com.brettspiel.model.BillDetail;
import com.brettspiel.model.Promotion;

public interface IBillDetailRepository extends JpaRepository<BillDetail, Integer>  {

}
