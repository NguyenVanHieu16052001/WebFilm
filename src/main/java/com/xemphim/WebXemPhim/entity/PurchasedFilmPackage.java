package com.xemphim.WebXemPhim.entity;

import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "purchased_film_packages")
public class PurchasedFilmPackage {
	
	@EmbeddedId
	private PurchasedFilmPackageId id;
	
	@Column(name = "purchase_date")
	private Date purchaseDate;
	
	@Column(name = "expiration_date")
	private Date expirationDate;

	public PurchasedFilmPackageId getId() {
		return id;
	}

	public void setId(PurchasedFilmPackageId id) {
		this.id = id;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Date getExpiration_date() {
		return expirationDate;
	}

	public void setExpiration_date(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(expirationDate, id, purchaseDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PurchasedFilmPackage other = (PurchasedFilmPackage) obj;
		return Objects.equals(expirationDate, other.expirationDate) && Objects.equals(id, other.id)
				&& Objects.equals(purchaseDate, other.purchaseDate);
	}
}