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
	@JsonIgnore
	@ManyToMany(cascade = {
		    CascadeType.PERSIST,
		    CascadeType.MERGE
		},fetch = FetchType.LAZY)
	@JoinTable(name = "boardgame_categories",
		joinColumns = @JoinColumn(name = "boardgame_id"),
		inverseJoinColumns = @JoinColumn(name = "category_id")
	)
	private Set<Category> categories = new HashSet<>();
	
	@JsonIgnore
	@ManyToMany(mappedBy = "boardGames")
	private Set<PlayList> playLists = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "boardGame")
	private List<Copy> copies;
	
	@JsonIgnore
	@OneToMany(mappedBy = "boardGame")
	private List<WebCartDetail> webCartDetails;
	
	@OneToMany(mappedBy = "boardGame")
	private List<PromotionDetail> promotionDetails;
	
	@OneToMany(mappedBy = "snack")
	private List<Image> images;
}
