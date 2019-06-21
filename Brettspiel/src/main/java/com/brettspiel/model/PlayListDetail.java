package com.brettspiel.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "playListDetails")
@Data
@NamedQuery(name="playListDetail.findByPlayListId", query="Select w from PlayListDetail w where w.playList.id= ?1")
public class PlayListDetail {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="boardGame_id")
	private BoardGame boardGame;
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="playList_id")
	private PlayList playList;
	
}
