package com.brettspiel.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "promotions")
@Data
public class Promotion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name", nullable = false, length = 100)
	private String name;
	
	@Column(name = "description", nullable = false, length = 250)
	private String description;
	
	@Column(name = "start_date", nullable = false)
	private LocalDate startDate;
	
	@Column(name = "end_date", nullable = false)
	private LocalDate endDate;
	
	@Column(name = "minimum_rent_days", nullable = false)
	private Integer minimunRentDays;
	
	@Column(name = "maximum_rent_days", nullable = false)
	private Integer maximumRentDays;
	
	@OneToMany(mappedBy = "promotion")
	private List<PromotionDetail> promotionDetails;
	
	@OneToMany(mappedBy = "promotion")
	private List<WebCartDetail> webCartDetails;
	
	@OneToMany(mappedBy = "promotion")
	private List<BillDetail> billDetails;
	
}

