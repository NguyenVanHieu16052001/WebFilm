package com.xemphim.WebXemPhim.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "film_packages")
public class FilmPackage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "film_package_id")
	private Integer filmPackageId;
	
	@Column(name = "used_time")
	private Integer usedTime;
	
	@Column(name = "applicable_date")
	private Date applicableDate;
	
	private Integer price;

	public Integer getFilmPackageId() {
		return filmPackageId;
	}

	public void setFilmPackageId(Integer filmPackageId) {
		this.filmPackageId = filmPackageId;
	}

	public Integer getUsedTime() {
		return usedTime;
	}

	public void setUsedTime(Integer usedTime) {
		this.usedTime = usedTime;
	}

	public Date getApplicableDate() {
		return applicableDate;
	}

	public void setApplicableDate(Date applicableDate) {
		this.applicableDate = applicableDate;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
}