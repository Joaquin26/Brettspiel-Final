package com.brettspiel.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brettspiel.model.WebCartDetail;

public interface IWebCartDetailRepository extends JpaRepository<WebCartDetail, Integer> {
	
}
