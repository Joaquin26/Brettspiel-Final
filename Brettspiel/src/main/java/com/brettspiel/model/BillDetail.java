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
@Table(name = "billDetails")
@Data
public class BillDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="bill_id",nullable = false)
	private Bill bill;
	
	@ManyToOne
	@JoinColumn(name="batch_id",nullable = false)
	private Batch batch;
	
	@ManyToOne
	@JoinColumn(name="promotion_id")
	private Promotion promotion;
	
	@Column(name = "quantity",nullable = false)
	private Integer quantity;
	
	@Column(name = "price_snacks",nullable = false)
	private Float priceSnacks;
	
	
	
	
}
