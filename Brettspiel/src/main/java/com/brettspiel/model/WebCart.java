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
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "webcarts")
@Data
@NamedQuery(name="WebCart.findByUserId", query="Select w from WebCart w where w.user.id= ?1")
public class WebCart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "status", nullable = false)
	private String status;
	
	@Column(name = "created_date", nullable = false)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate createdDate;
	
	@Column(name = "cancelled_date", nullable = false)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate cancelledDate;
	
	@Column(name = "last_seen_date", nullable = false)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate lastSeenDate;
	
	@ManyToOne
	@JoinColumn(name="user_id",nullable = false)
	private User user;
	
	@JsonIgnore
	@OneToMany(mappedBy = "webCart")
	private List<WebCartDetail> webCartDetails;

	public WebCart(String status, LocalDate createdDate, LocalDate cancelledDate, LocalDate lastSeenDate, User user,
			List<WebCartDetail> webCartDetails) {
		super();
		this.status = status;
		this.createdDate = createdDate;
		this.cancelledDate = cancelledDate;
		this.lastSeenDate = lastSeenDate;
		this.user = user;
		this.webCartDetails = webCartDetails;
	}
	
	public WebCart() {}
}

