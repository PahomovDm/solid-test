package com.pakhomov.solidtest.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "positions")
public class Position {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Product product;

	private Integer number;

	public Position() {
	}

	public Position(Product product, Integer number) {
		this.product = product;
		this.number = number;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Position position = (Position) o;
		return Objects.equals(id, position.id) &&
				Objects.equals(product, position.product) &&
				Objects.equals(number, position.number);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, product, number);
	}
}
