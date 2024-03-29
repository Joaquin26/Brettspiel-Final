package com.brettspiel.controller.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brettspiel.controller.service.IUserService;
import com.brettspiel.model.User;
import com.brettspiel.model.repository.IUserRepository;

@Service
public class UserService implements IUserService {

	@Autowired
	private IUserRepository userRepository;
	
	@Override
	public User insert(User t) {
		return userRepository.save(t);
	}

	@Override
	public User update(User t) {
		return userRepository.save(t);
	}

	@Override
	public void Delete(int id) {
		userRepository.deleteById(id);
	}

	@Override
	public Optional<User> findById(int id) {
		return userRepository.findById(id);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Optional<User> findByCredentials(String username, String password) {
		// TODO Auto-generated method stub
		return userRepository.findByCredentials(username, password);
	}

	@Override
	public Optional<User> findByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(username);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(email);
	}
	
}
