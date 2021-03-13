package com.cap.exs.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(uniqueConstraints = { 
		@UniqueConstraint(columnNames = "expenseType") 
	})

public class Expense {
	
	@Id
	@SequenceGenerator(name = "expense_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "expense_sequence")
	private int expenseCode;
	
	@NotNull
	@Pattern(regexp = "[a-z A-Z]*",message = "Invalid")
	@Size(min = 5 , max = 30)
	private String expenseType;
	
	@NotNull
	@Size(min = 5 , max = 50)
	private String expenseDescription;
	
	
	public int getExpenseCode() {
		return expenseCode;
	}
	public void setExpenseCode(int expenseCode) {
		this.expenseCode = expenseCode;
	}
	public String getExpenseType() {
		return expenseType;
	}
	public void setExpenseType(String expenseType) {
		this.expenseType = expenseType;
	}
	public String getExpenseDescription() {
		return expenseDescription;
	}
	@Override
	public String toString() {
		return "Expense [expenseCode=" + expenseCode + ", expenseType=" + expenseType + ", expenseDescription="
				+ expenseDescription + "]";
	}
	public void setExpenseDescription(String expenseDescription) {
		this.expenseDescription = expenseDescription;
	}
	public Expense(int expenseCode, String expenseType, String expenseDescription) {
		super();
		this.expenseCode = expenseCode;
		this.expenseType = expenseType;
		this.expenseDescription = expenseDescription;
	}
	public Expense() {
		super();
	}
	
	

}
