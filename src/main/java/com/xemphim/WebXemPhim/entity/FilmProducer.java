package com.xemphim.WebXemPhim.entity;

import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "film_producers")
public class FilmProducer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "film_producer_id")
	private Integer filmProducerId;
	
	@Column(name = "film_producer_name")
	private String filmProducerName;
	
	@OneToMany(mappedBy = "filmProducer", fetch = FetchType.EAGER)
	private Collection<Film> films;

	public Integer getFilmProducerId() {
		return filmProducerId;
	}

	public void setFilmProducerId(Integer filmProducerId) {
		this.filmProducerId = filmProducerId;
	}

	public String getFilmProducerName() {
		return filmProducerName;
	}

	public void setFilmProducerName(String filmProducerName) {
		this.filmProducerName = filmProducerName;
	}

	public Collection<Film> getFilms() {
		return films;
	}

	public void setFilms(Collection<Film> films) {
		this.films = films;
	}
}