package com.brettspiel.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brettspiel.model.Bill;

public interface IBillRepository extends JpaRepository<Bill, Integer>  {

}
