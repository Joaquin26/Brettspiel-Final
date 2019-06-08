package com.brettspiel.model;

import java.time.LocalDate;
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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "batches")
@Data
public class Batch {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="snack_id",nullable = false)
	private Snack snack;
	
	@Column(name = "snack_cost",nullable = false)
	private Float snackCost;
	
	@Column(name = "expired_date",nullable = false)
	private LocalDate expiredDate;
	
	@Column(name = "bought_date",nullable = false)
	private LocalDate boughtDate;
	
	@JsonIgnore
	@OneToMany(mappedBy = "batch")
	private List<BillDetail> billDetails;
}
