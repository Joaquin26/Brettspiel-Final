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
@Table(name = "creditcards")
@Data
public class CreditCard {
    
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "number",nullable = false,length = 80)
	private int number;
	
	@Column(name = "expiration",nullable = false)
	private String expiration;
	
	@Column(name = "cvv",nullable = false)
	private double cvv;
	
	@Column(name = "name",nullable = false)
	private String name;
	
	@JsonIgnore
	@OneToMany(mappedBy = "creditCard")
	private List<Bill> bills;
	
}

