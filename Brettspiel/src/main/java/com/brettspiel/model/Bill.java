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

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Table(name = "bills")
@Data
public class Bill {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="user_id",nullable = false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name="creditcard_id",nullable = false)
	private CreditCard creditCard;
	
	@Column(name = "date",nullable = false)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate date;
	
	@Column(name = "ruc")
	private Integer ruc;
	
	@Column(name = "status",nullable = false)
	private String status;
	
	@Column(name = "start_rent_date",nullable = false)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate startRentDayDate;
	
	@Column(name = "end_rent_day",nullable = false)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate endRentDayDate;
	
	@Column(name = "membership_discount")
	private Float membershipDiscount;
	
	@Column(name = "penalty")
	private Float penalty;
	
	@OneToMany(mappedBy = "bill")
	private List<BillDetail> billDetails;

	@OneToMany(mappedBy = "bill")
	private List<BillCopyDetail> billCopyDetails;
}
