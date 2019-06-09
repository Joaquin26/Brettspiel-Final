package com.brettspiel.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "bill_copy_details")
@Data
public class BillCopyDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="bill_id",nullable = false)
	private Bill bill;
	
	@ManyToOne
	@JoinColumn(name="copy_id")
	private Copy copy;
	
	@ManyToOne
	@JoinColumn(name="promotion_id")
	private Promotion promotion;
}
