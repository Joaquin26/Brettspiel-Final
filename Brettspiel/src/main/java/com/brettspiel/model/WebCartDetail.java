package com.brettspiel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "webcartdetails")
@Data
public class WebCartDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="webcart_id",nullable = false)
	private WebCart webCart;
	
	@ManyToOne
	@JoinColumn(name="snack_id")
	private Snack snack;
	
	@ManyToOne
	@JoinColumn(name="boardGame_id")
	private BoardGame boardGame;
	
	@ManyToOne
	@JoinColumn(name="promotion_id")
	private Promotion promotion ;
	
	@Column(name = "quantity",nullable = false)
	private Integer quantity;
}
