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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "playlists")
@Data
@NamedQuery(name="playListDetail.findByUserId", query="Select w from PlayList w where w.user.id= ?1")
public class PlayList {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name",nullable = false,length = 80)
	private String name;
	
	@Column(name = "description",nullable = false)
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "user_id",nullable = false) 
	private User user;
	
	@ManyToMany(cascade = {
		    CascadeType.PERSIST,
		    CascadeType.MERGE
		})
	@JoinTable(name = "playlists_boardgames",
		joinColumns = @JoinColumn(name = "playlist_id"),
		inverseJoinColumns = @JoinColumn(name = "boardgame_id")
	)
	@JsonIgnore
	private Set<BoardGame> boardGames= new HashSet<>();
}
