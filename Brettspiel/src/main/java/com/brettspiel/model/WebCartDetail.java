package com.brettspiel.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Entity
@Table(name = "webcartdetails")
@Data
@NamedQuery(name="WebCartDetail.repeatedSnack", query="Select w from WebCartDetail w where w.webCart.id= ?1 and snack.id=?2")
@NamedQuery(name="WebCartDetail.repeated", query="Select w from WebCartDetail w where w.webCart.id= ?1 and boardGame.id=?2")
@NamedQuery(name="WebCartDetail.findByWebcartId", query="Select w from WebCartDetail w where w.webCart.id= ?1")
public class WebCartDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne()
	@JoinColumn(name="webcart_id",nullable = false)
	@JsonIgnoreProperties({"webCartDetails","createdDate","cancelledDate","lastSeenDate","user"})
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
