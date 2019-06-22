package com.brettspiel.controller.service;

import java.util.Optional;

import com.brettspiel.model.WebCart;
import com.brettspiel.model.WebCartDetail;

public interface IWebCartDetailService extends ICrudService<WebCartDetail> {

	public Optional<WebCartDetail> repeated(int webcartId,int boardGameId);
	public Optional<WebCartDetail>repeatedSnack(int webcartId,int snackId);
	
}
