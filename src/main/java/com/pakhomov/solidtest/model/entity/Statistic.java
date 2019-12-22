package com.pakhomov.solidtest.model.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "statistics")
public class Statistic {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDateTime startingTime;

	@OneToMany
	@JoinColumn(name = "statistic_id")
	private List<Sale> sales;

	public Statistic() {
	}

	public Statistic(LocalDateTime startingTime) {
		this.startingTime = startingTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getStartingTime() {
		return startingTime;
	}

	public void setStartingTime(LocalDateTime startingTime) {
		this.startingTime = startingTime;
	}

	public List<Sale> getSales() {
		return sales;
	}

	public void setSales(List<Sale> sales) {
		this.sales = sales;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Statistic statistic = (Statistic) o;
		return Objects.equals(id, statistic.id) &&
				Objects.equals(startingTime, statistic.startingTime) &&
				Objects.equals(sales, statistic.sales);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, startingTime, sales);
	}
}
