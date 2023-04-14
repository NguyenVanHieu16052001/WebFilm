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
@Table(name = "nations")
public class Nation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "nation_id")
	private Integer nationId;
	
	@Column(name = "nation_name")
	private String nationName;
	
	@OneToMany(mappedBy = "nation", fetch = FetchType.EAGER)
	private Collection<Film> films;

	public Integer getNationId() {
		return nationId;
	}

	public void setNationId(Integer nationId) {
		this.nationId = nationId;
	}

	public String getNationName() {
		return nationName;
	}

	public void setNationName(String nationName) {
		this.nationName = nationName;
	}

	public Collection<Film> getFilms() {
		return films;
	}

	public void setFilms(Collection<Film> films) {
		this.films = films;
	}
}