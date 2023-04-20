package com.xemphim.WebXemPhim.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "episodes")
public class Episode {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "episode_id")
	private Integer episodeId;
	
	@Column(name = "episode_path")
	private String episodePath;
	
	@Column(name = "numerical_order")
	private Integer numericalOrder;
	
	@ManyToOne
	@JoinColumn(name = "film_id")
	private Film film;

	public Integer getEpisodeId() {
		return episodeId;
	}

	public void setEpisodeId(Integer episodeId) {
		this.episodeId = episodeId;
	}

	public String  getEpisodePath() {
		return episodePath;
	}

	public void setEpisodePath(String episodePath) {
		this.episodePath = episodePath;
	}

	public Integer getNumericalOrder() {
		return numericalOrder;
	}

	public void setNumericalOrder(Integer numericalOrder) {
		this.numericalOrder = numericalOrder;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}
}