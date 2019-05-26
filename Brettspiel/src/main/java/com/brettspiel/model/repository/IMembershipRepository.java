package com.brettspiel.model.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.brettspiel.model.Membership;

public interface IMembershipRepository extends JpaRepository<Membership, Integer> {
	
}
