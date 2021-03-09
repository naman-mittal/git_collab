package com.cap.exs.service_interfaces;

import java.util.List;

import com.cap.exs.entities.Expense;


public interface IExpenseService {

	public Expense addExpense(Expense expense);
	
	public List<Integer> getAllExpenseCode();
	
	public List<Expense> getAllExpenses();
	
	public Expense getExpenseByCode(int expenseCode);
	
	public Expense updateExpense(Expense expense);

	public void deleteAllExpenses();
	
	public Expense deleteExpenseByCode(int expenseCode);
	
	public Expense findByCode(int expenseCode);
	
	
	
	

}
