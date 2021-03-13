package com.cap.exs.request;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AddExpenseClaimRequest {

	@Positive
	private double amount;
	
	@NotNull
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@JsonFormat(pattern = "MM/dd/yyyy")
	private LocalDate startDate;
	
	public AddExpenseClaimRequest() {
		super();
	}

	@NotNull
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@JsonFormat(pattern = "MM/dd/yyyy")
	private LocalDate endDate;
	
	@Positive
	private int expenseId;
	
	@Positive
	private int projectId;
	
	@Positive
	private int employeeId;

	public AddExpenseClaimRequest(double amount,LocalDate startDate, LocalDate endDate,
			int expenseId,int projectId,  int employeeId) {
		super();
		this.amount = amount;
		this.startDate = startDate;
		this.endDate = endDate;
		this.expenseId = expenseId;
		this.projectId = projectId;
		this.employeeId = employeeId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
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

	public int getExpenseId() {
		return expenseId;
	}

	public void setExpenseId(int expenseId) {
		this.expenseId = expenseId;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	
	
	
}
