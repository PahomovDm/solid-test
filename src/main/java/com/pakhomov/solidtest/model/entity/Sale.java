package com.pakhomov.solidtest.model.entity;

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

	@OneToMany
	@JoinColumn(name = "sale_id")
	private List<Position> positions;

	private Double amount;

	private Double amountWithDiscount;

	private LocalDateTime date;

	public Sale() {
	}

	public Sale(@NotNull List<Position> positions, Double amount, Double amountWithDiscount, LocalDateTime date) {
		this.positions = positions;
		this.amount = amount;
		this.amountWithDiscount = amountWithDiscount;
		this.date = date;
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

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getAmountWithDiscount() {
		return amountWithDiscount;
	}

	public void setAmountWithDiscount(Double amountWithDiscount) {
		this.amountWithDiscount = amountWithDiscount;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Sale sale = (Sale) o;
		return Objects.equals(id, sale.id) &&
				Objects.equals(positions, sale.positions) &&
				Objects.equals(amount, sale.amount) &&
				Objects.equals(amountWithDiscount, sale.amountWithDiscount) &&
				Objects.equals(date, sale.date);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, positions, amount, amountWithDiscount, date);
	}
}
