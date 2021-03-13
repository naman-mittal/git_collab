package com.cap.exs.request;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UpdateExpenseClaimRequest {

	@NotNull
	@Positive
	private int id;
	
	@NotNull
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@JsonFormat(pattern = "MM/dd/yyyy")
	private LocalDate startDate;
	
	@NotNull
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@JsonFormat(pattern = "MM/dd/yyyy")
	private LocalDate endDate;
	
	@NotNull
	@Positive
	private double expenseAmount;
	
	
	
	public double getExpenseAmount() {
		return expenseAmount;
	}


	public void setExpenseAmount(double expenseAmount) {
		this.expenseAmount = expenseAmount;
	}


	public UpdateExpenseClaimRequest() {
		super();
	}
	
	
	public UpdateExpenseClaimRequest(int id, LocalDate startDate, LocalDate endDate, double expenseAmount) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.expenseAmount = expenseAmount;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	
	
}
