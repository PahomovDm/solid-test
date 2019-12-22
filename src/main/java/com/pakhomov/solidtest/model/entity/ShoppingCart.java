package com.pakhomov.solidtest.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "shopping_cart")
public class ShoppingCart {

	@Id
	private String sessionId;

	@NotNull
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "shopping_cart_id")
	private List<Position> positionList;

	public ShoppingCart() {
	}

	public ShoppingCart(String sessionId, List<Position> positionList) {
		this.sessionId = sessionId;
		this.positionList = positionList;
	}

	public ShoppingCart(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public List<Position> getPositionList() {
		return positionList;
	}

	public void setPositionList(List<Position> positionList) {
		this.positionList = positionList;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ShoppingCart that = (ShoppingCart) o;
		return Objects.equals(sessionId, that.sessionId) &&
				Objects.equals(positionList, that.positionList);
	}

	@Override
	public int hashCode() {
		return Objects.hash(sessionId, positionList);
	}
}
