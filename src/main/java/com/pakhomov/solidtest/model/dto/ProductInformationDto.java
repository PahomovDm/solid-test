package com.pakhomov.solidtest.model.dto;

import java.time.LocalDateTime;

public class ProductInformationDto {

	private Long id;

	private Long number;

	private LocalDateTime lastSale;

	public ProductInformationDto(Long id, Long number, LocalDateTime lastSale) {
		this.id = id;
		this.number = number;
		this.lastSale = lastSale;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public LocalDateTime getLastSale() {
		return lastSale;
	}

	public void setLastSale(LocalDateTime lastSale) {
		this.lastSale = lastSale;
	}
}
