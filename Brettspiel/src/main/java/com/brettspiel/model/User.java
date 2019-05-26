package com.brettspiel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Table(name = "Users")
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
}
