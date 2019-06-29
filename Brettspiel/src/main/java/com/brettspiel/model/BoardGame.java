package com.brettspiel.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "boardgames")
@Data
public class BoardGame {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name", nullable = false, length = 150, unique = true)
	private String name;
	
	@Column(name = "description", nullable = false, length = 750)
	private String description;
	
	@Column(name = "tutorial_link", nullable = false, length = 300)
	private String tutorialLink;
	
	@Column(name = "available", nullable = false)
	private Integer available;
	
	@Column(name = "stock", nullable = false)
	private Integer stock;
	
	@Column(name = "rented", nullable = false)
	private Integer rented;
	
	@Column(name = "price_per_day", nullable = false)
	private Float pricePerDay;
	
	@Column(name= "min_number_players", nullable = false)
	private Integer minNumberPlayers;
	
	@Column(name= "max_number_players", nullable = false)
	private Integer maxNumberPlayers;
	
	@Column(name= "min_age", nullable = false)
	private Integer minAge;
	
	@Column(name = "cost", nullable = false)
	private Float cost;
	
	@ManyToMany(cascade = {
		    CascadeType.PERSIST,
		    CascadeType.MERGE
		},fetch = FetchType.LAZY)
	@JoinTable(name = "boardgame_categories",
		joinColumns = @JoinColumn(name = "boardgame_id"),
		inverseJoinColumns = @JoinColumn(name = "category_id")
	)
	private List<Category> categories;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "boardGames")
	private List<PlayList> playLists;
	
	@JsonIgnore
	@OneToMany(mappedBy = "boardGame")
	private List<Copy> copies;
	
	@JsonIgnore
	@OneToMany(mappedBy = "boardGame")
	private List<WebCartDetail> webCartDetails;
	@JsonIgnore
	@OneToMany(mappedBy = "boardGame")
	private List<PromotionDetail> promotionDetails;
	
	@OneToMany(mappedBy = "boardGame")
	private List<Image> images;
}
