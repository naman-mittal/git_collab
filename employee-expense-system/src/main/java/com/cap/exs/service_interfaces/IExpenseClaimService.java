package com.cap.exs.service_interfaces;

import java.util.List;

import com.cap.exs.entities.Employee;
import com.cap.exs.entities.ExpenseClaim;

public interface IExpenseClaimService {
	
	public ExpenseClaim addExpenseClaim(ExpenseClaim expenseClaim);
	
	public List<ExpenseClaim> getAllExpenseClaim();
	
	public ExpenseClaim fetchExpenseClaimById(int expenseCodeID);
	
	public ExpenseClaim updateExpenseClaim(ExpenseClaim expenseClaim);
	
	public ExpenseClaim deleteExpenseClaimById(int id);
	
	public List<ExpenseClaim> getAllClaimsByEmployee(Employee employee);

}
