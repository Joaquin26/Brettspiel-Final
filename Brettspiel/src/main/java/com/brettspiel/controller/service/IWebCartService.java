package com.brettspiel.controller.service;

import java.util.Optional;

import com.brettspiel.model.WebCart;

public interface IWebCartService extends ICrudService<WebCart> {
	public Optional<WebCart> findByUserId(int userId);
}
