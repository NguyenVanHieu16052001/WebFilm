package com.xemphim.WebXemPhim.entity;

import java.util.Objects;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "films_categories")
public class FilmCategory {
	
	@EmbeddedId
	private FilmCategoryId id;

	public FilmCategoryId getId() {
		return id;
	}

	public void setId(FilmCategoryId id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FilmCategory other = (FilmCategory) obj;
		return Objects.equals(id, other.id);
	}
}