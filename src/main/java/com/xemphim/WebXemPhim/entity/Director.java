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
@Table(name = "directors")
public class Director {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "director_id")
	private Integer directorId;
	
	@Column(name = "director_name")
	private String directorName;
	
	@OneToMany(mappedBy = "director", fetch = FetchType.EAGER)
	private Collection<Film> films;
	
	public Integer getDirectorId() {
		return directorId;
	}

	public void setDirectorId(Integer directorId) {
		this.directorId = directorId;
	}

	public String getDirectorName() {
		return directorName;
	}

	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}

	public Collection<Film> getFilms() {
		return films;
	}

	public void setFilms(Collection<Film> films) {
		this.films = films;
	}
}