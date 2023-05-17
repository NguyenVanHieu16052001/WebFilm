package com.xemphim.WebXemPhim.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class PurchasedFilmPackageId implements Serializable{
	
	@ManyToOne
	@JoinColumn(name = "account_name")
	private Account account;
	
	@ManyToOne
	@JoinColumn(name = "film_package_id")
	private FilmPackage filmPackage;

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public FilmPackage getFilmPackage() {
		return filmPackage;
	}

	public void setFilmPackage(FilmPackage filmPackage) {
		this.filmPackage = filmPackage;
	}

	@Override
	public int hashCode() {
		return Objects.hash(account, filmPackage);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PurchasedFilmPackageId other = (PurchasedFilmPackageId) obj;
		return Objects.equals(account, other.account) && Objects.equals(filmPackage, other.filmPackage);
	}

	@Override
	public String toString() {
		return "PurchasedFilmPackageId [account=" + account + ", filmPackage=" + filmPackage + "]";
	}
}