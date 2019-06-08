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
@Table(name = "promotiondetails")
@Data
public class PromotionDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="promotion_id")
	private Promotion promotion;
	
	@ManyToOne
	@JoinColumn(name="snack_id")
	private Snack snack;
	
	@ManyToOne
	@JoinColumn(name="boardgame_id")
	private BoardGame boardGame;
	
	@Column(name = "price_promotion_per_day")
	private	Integer pricePromotionPerDay;
	
	@Column(name="price_promotion_snack")
	private Integer pricePromotionSnack;
	
	@Column(name="quantity")
	private Integer quantity;
	
	
}
