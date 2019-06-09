package com.brettspiel.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brettspiel.model.Batch;
import com.brettspiel.model.Bill;
import com.brettspiel.model.Promotion;

public interface IBillRepository extends JpaRepository<Bill, Integer>  {

}
