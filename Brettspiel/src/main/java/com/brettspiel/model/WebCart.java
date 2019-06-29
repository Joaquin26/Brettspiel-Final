package com.brettspiel.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

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
	
	@Column(name = "cancelled_date")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate cancelledDate;
	
	@Column(name = "last_seen_date", nullable = false)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate lastSeenDate;
	
	@ManyToOne
	@JoinColumn(name="user_id",nullable = false)
	private User user;
	
	
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

