package com.brettspiel.controller.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brettspiel.controller.service.IMembershipService;
import com.brettspiel.model.Membership;
import com.brettspiel.model.repository.IMembershipRepository;

@Service
public class MembershipService implements IMembershipService {

	@Autowired
	private IMembershipRepository membershipRepository;
	
	@Override
	public Membership insert(Membership t) {
		return membershipRepository.save(t);
	}

	@Override
	public Membership update(Membership t) {
		return membershipRepository.save(t);
	}

	@Override
	public void Delete(int id) {
		membershipRepository.deleteById(id);
	}

	@Override
	public Optional<Membership> findById(int id) {
		return membershipRepository.findById(id);
	}

	@Override
	public List<Membership> findAll() {
		return membershipRepository.findAll();
	}

}
