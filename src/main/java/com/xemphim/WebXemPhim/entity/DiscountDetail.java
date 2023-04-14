package com.xemphim.WebXemPhim.entity;

import java.util.Objects;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "discount_details")
public class DiscountDetail {
	
	@EmbeddedId
	private DiscountDetailId id;

	public DiscountDetailId getId() {
		return id;
	}

	public void setId(DiscountDetailId id) {
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
		DiscountDetail other = (DiscountDetail) obj;
		return Objects.equals(id, other.id);
	}
}