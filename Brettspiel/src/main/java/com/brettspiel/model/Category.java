package com.brettspiel.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "categories")
@Data
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name", nullable = false, length = 50, unique = true)
	private String name;
	
	@Column(name = "description", nullable = false, length = 500)
	private String description;
	
	
	@ManyToMany(mappedBy = "categories")
	@JsonIgnore
	private Set<BoardGame> boardGames = new HashSet<>();
}
