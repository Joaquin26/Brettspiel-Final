package com.brettspiel.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brettspiel.model.WebCart;

public interface IWebCartRepository extends JpaRepository<WebCart, Integer> {

}
