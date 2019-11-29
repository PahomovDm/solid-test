package com.pakhomov.solidtest.model.dto;

import java.time.LocalDateTime;

public class StatisticInformationDto {

	private LocalDateTime startingTime;

	private Long numberOfSales;

	private Double amountValueOfChecks;

	private Double discountAmount;

	public StatisticInformationDto(LocalDateTime startingTime, Long numberOfSales, Double amountValueOfChecks, Double discountAmount) {
		this.startingTime = startingTime;
		this.numberOfSales = numberOfSales;
		this.amountValueOfChecks = amountValueOfChecks;
		this.discountAmount = discountAmount;
	}

	public LocalDateTime getStartingTime() {
		return startingTime;
	}

	public Long getNumberOfSales() {
		return numberOfSales;
	}

	public Double getAmountValueOfChecks() {
		return amountValueOfChecks;
	}

	public Double getDiscountAmount() {
		return discountAmount;
	}
}
