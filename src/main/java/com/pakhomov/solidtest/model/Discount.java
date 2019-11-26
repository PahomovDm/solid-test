package com.pakhomov.solidtest.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "discounts")
public class Discount {

	public final static int MIN_DISCOUNT_PERCENT = 5;
	public final static int MAX_DISCOUNT_PERCENT = 10;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@NotNull
	private Product product;

	private Byte size;

	private LocalDateTime startTime;

	public Discount() {
	}

	public Discount(@NotNull Product product, Byte size, LocalDateTime startTime) {
		this.product = product;
		this.size = size;
		this.startTime = startTime;
	}

	public Discount(Product product, Byte size) {
		this.product = product;
		this.size = size;
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

	public Byte getSize() {
		return size;
	}

	public void setSize(Byte size) {
		this.size = size;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Discount discount = (Discount) o;
		return Objects.equals(id, discount.id) &&
				Objects.equals(product, discount.product) &&
				Objects.equals(size, discount.size);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, product, size);
	}
}
