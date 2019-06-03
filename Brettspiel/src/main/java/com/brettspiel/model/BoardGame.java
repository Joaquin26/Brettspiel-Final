package com.brettspiel.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "boardgames")
@Data
public class BoardGame {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name", nullable = false, length = 100)
	private String name;
	
	@Column(name = "description", nullable = false, length = 250)
	private String description;
	
	@Column(name = "tutorial_link", nullable = false, length = 300)
	private String tutorialLink;
	
	@Column(name = "available", nullable = false)
	private Integer available;
	
	@Column(name = "stock", nullable = false)
	private Integer stock;
	
	@Column(name = "rented", nullable = false)
	private Integer rented;
	
	@Column(name = "price_per_day")
	private Float pricePerDay;
	
	@Column(name = "cost", nullable = false)
	private Float cost;
	
	@ManyToMany(cascade = {
		    CascadeType.PERSIST,
		    CascadeType.MERGE
		})
	@JoinTable(name = "boardgame_categories",
		joinColumns = @JoinColumn(name = "boardgame_id"),
		inverseJoinColumns = @JoinColumn(name = "category_id")
	)
	private Set<Category> categories = new HashSet<>();
	
	@ManyToMany(mappedBy = "boardGames")
	private Set<Playlist> playLists = new HashSet<>();
}
