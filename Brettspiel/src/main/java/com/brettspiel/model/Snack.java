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
@Table(name = "snacks")
@Data
public class Snack {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name",nullable = false,length = 80)
	private String name;
	
	@Column(name = "type",nullable = false)
	private String description;
	
	@Column(name = "size_value",nullable = false)
	private Float sizeValue;
	
	@Column(name = "si_units",nullable = false)
	private String siUnits;
	
	@Column(name = "stock",nullable = false)
	private int stock;
	
	@Column(name = "price",nullable = false)
	private Float price;
	
	@JsonIgnore
	@OneToMany(mappedBy = "snack")
	private List<WebCartDetail> webCartDetails;
	
}

