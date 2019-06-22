package com.brettspiel.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brettspiel.model.WebCartDetail;

public interface IWebCartDetailRepository extends JpaRepository<WebCartDetail, Integer> {
	public Optional<WebCartDetail> repeated(int webcartId,int boardGameId);
	public Optional<WebCartDetail> repeatedSnack(int webcartId,int snackId);
}
