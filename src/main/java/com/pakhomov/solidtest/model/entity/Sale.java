package com.pakhomov.solidtest.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "sales")
public class Sale {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@NotNull
	private List<Position> positions;

	private LocalDateTime date;

	public Sale() {
	}

	public Sale(List<Position> positions, LocalDateTime date) {
		this.positions = positions;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Position> getPositions() {
		return positions;
	}

	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Sale sale = (Sale) o;
		return Objects.equals(id, sale.id) &&
				Objects.equals(positions, sale.positions) &&
				Objects.equals(date, sale.date);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, positions, date);
	}
}
