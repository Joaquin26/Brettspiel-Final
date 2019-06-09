package com.brettspiel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "copies")
@Data
public class Copy {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="boardGame_id",nullable = false)
	private BoardGame boardGame;
	
	@Column(name = "condition", nullable = false, length = 500)
	private String condition;
	
	@Column(name = "available", nullable = false, length = 500)
	private boolean available;
}
