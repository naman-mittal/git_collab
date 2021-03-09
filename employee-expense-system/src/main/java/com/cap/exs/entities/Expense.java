package com.cap.exs.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Expense {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int expenseCode;
	private String expenseType;
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
		// TODO Auto-generated constructor stub
	}
	
	

}
