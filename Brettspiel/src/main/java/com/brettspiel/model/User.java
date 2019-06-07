package com.brettspiel.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="username",nullable = false,unique = true,length = 50)
	private String username;
	
	@Size(min = 8)
	@Column(name="password",nullable = false,length = 50)
	private String password;
	
	@Column(name="name",nullable = false)
	private String name;
	
	@Column(name="email", nullable = false)
	private String email;
	
	@Column(name="reputation", nullable = false)
	private Integer reputation;
	
	@ManyToOne
	@JoinColumn(name="membership_id",nullable = false)
	private Membership membership;
	
	@OneToMany(mappedBy = "user")
	private List<Playlist> playlists;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<WebCart> webCarts;
	
}
