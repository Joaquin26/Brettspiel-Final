package com.brettspiel.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brettspiel.model.User;

public interface IUserRepository extends JpaRepository<User, Integer> {

	public Optional<User> findByCredentials(String username,String password);
	public Optional<User> findByUsername(String username);
	public Optional<User> findByEmail(String email);
}
