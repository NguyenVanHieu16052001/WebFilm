package com.xemphim.WebXemPhim.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class DiscountDetailId implements Serializable{
	
	@ManyToOne
	@JoinColumn(name = "discount_id")
	private Discount discount;
	
	@ManyToOne
	@JoinColumn(name = "film_package_id")
	private FilmPackage filmPackage;

	public Discount getDiscount() {
		return discount;
	}

	public void setDiscount(Discount discount) {
		this.discount = discount;
	}

	public FilmPackage getFilmPackage() {
		return filmPackage;
	}

	public void setFilmPackage(FilmPackage filmPackage) {
		this.filmPackage = filmPackage;
	}

	@Override
	public int hashCode() {
		return Objects.hash(discount, filmPackage);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DiscountDetailId other = (DiscountDetailId) obj;
		return Objects.equals(discount, other.discount) && Objects.equals(filmPackage, other.filmPackage);
	}
}