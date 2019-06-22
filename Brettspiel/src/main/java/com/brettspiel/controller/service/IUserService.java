package com.brettspiel.controller.service;

import java.util.Optional;

import com.brettspiel.model.User;

public interface IUserService extends ICrudService<User> {

	public Optional<User> findByCredentials(String username,String password);
	public Optional<User> findByUsername(String username);
	public Optional<User> findByEmail(String email);
}
