package com.cap.exs.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.format.annotation.DateTimeFormat;

import com.cap.exs.exceptions.InvalidEndDateException;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class ExpenseClaim {
	
	@Id
	@SequenceGenerator(name="expenseClaim_sequence",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "expenseClaim_sequence")
	private int expenseCodeId;
	
	@Positive
	private double expenseAmount;
	
	@NotNull
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@JsonFormat(pattern = "MM/dd/yyyy")
	private LocalDate startDate;
	
	@NotNull
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@JsonFormat(pattern = "MM/dd/yyyy")
	private LocalDate endDate;
	
	@NotNull
	@OneToOne(cascade = CascadeType.PERSIST)
	private Expense expense;
	
	@NotNull
	@OneToOne(cascade = CascadeType.PERSIST)
	private Project project;
	
	@NotNull
	@OneToOne(cascade = CascadeType.PERSIST)
	private Employee employee;
	
	public ExpenseClaim() {}
	
	public ExpenseClaim(double expenseAmount, LocalDate startDate, LocalDate endDate,
			Expense expense, Project project, Employee employee) {
		super();
		this.expenseAmount = expenseAmount;
		this.startDate = startDate;
		this.endDate = endDate;
		this.expense = expense;
		this.project = project;
		this.employee = employee;
	}

	public int getExpenseCodeId() {
		return expenseCodeId;
	}

	public void setExpenseCodeId(int expenseCodeId) {
		this.expenseCodeId = expenseCodeId;
	}

	public double getExpenseAmount() {
		return expenseAmount;
	}

	public void setExpenseAmount(double expenseAmount) {
		this.expenseAmount = expenseAmount;
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
		if(endDate.isAfter(startDate)) {
	           this.endDate = endDate;
	         } else {
	           throw new InvalidEndDateException("End date should be the date after start date");
	         }
	}

	public Expense getExpense() {
		return expense;
	}

	public void setExpense(Expense expense) {
		this.expense = expense;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "ExpenseClaim [expenseCodeId=" + expenseCodeId + ", expenseAmount=" + expenseAmount + ", startDate="
				+ startDate + ", endDate=" + endDate + ", employee=" + employee + "]";
	}
	
}
