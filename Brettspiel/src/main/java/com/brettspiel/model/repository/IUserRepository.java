package com.brettspiel.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brettspiel.model.User;

public interface IUserRepository extends JpaRepository<User, Integer> {
	
}
