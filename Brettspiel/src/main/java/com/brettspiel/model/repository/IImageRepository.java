package com.brettspiel.model.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.brettspiel.model.Image;
import com.brettspiel.model.Membership;

public interface IImageRepository extends JpaRepository<Image, Integer> {
	
}
