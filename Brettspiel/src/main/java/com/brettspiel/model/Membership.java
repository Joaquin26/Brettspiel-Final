package com.brettspiel.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "memberships")
@Data
public class Membership {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name", nullable = false, length = 50)
	private String name;

	@Column(name = "description", nullable = false, length = 250)
	private String description;

	@Column(name = "minimum_expenditure_year", nullable = false)
	private Float minimumExpenditureYear;

	@Column(name = "discount", nullable = false)
	private Float discount;
	@JsonIgnore	
	@OneToMany(mappedBy = "membership")
	private List<User> users;
}
