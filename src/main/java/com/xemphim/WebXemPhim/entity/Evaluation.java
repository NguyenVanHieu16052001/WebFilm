package com.xemphim.WebXemPhim.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "evaluations")
public class Evaluation {

	@EmbeddedId
	private EvaluationId id;
	
	@Column(name = "star_number")
	private Integer starNumber;
	
	private String comment;

	public EvaluationId getId() {
		return id;
	}

	public void setId(EvaluationId id) {
		this.id = id;
	}

	public Integer getStarNumber() {
		return starNumber;
	}

	public void setStarNumber(Integer starNumber) {
		this.starNumber = starNumber;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}