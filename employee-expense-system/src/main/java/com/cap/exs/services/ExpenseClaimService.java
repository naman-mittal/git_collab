package com.cap.exs.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cap.exs.entities.Employee;
import com.cap.exs.entities.Expense;
import com.cap.exs.entities.ExpenseClaim;
import com.cap.exs.entities.Project;
import com.cap.exs.exceptions.EmployeeNotFoundException;
import com.cap.exs.exceptions.ExpenseClaimNotFoundException;
import com.cap.exs.repos.IExpenseClaimRepository;
import com.cap.exs.service_interfaces.IExpenseClaimService;

@Service
public class ExpenseClaimService implements IExpenseClaimService {
	
	@Autowired
	IExpenseClaimRepository expenseClaimRepository;
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	ProjectService projectSevice;
	
	@Autowired
	ExpenseService expenseService;
	
	public ExpenseClaim addExpenseClaim(ExpenseClaim expenseClaim) {
		
		Employee employee = employeeService.findByEmployeeCode(expenseClaim.getEmployee().getEmpId());
		Project project = projectSevice.findByCode(expenseClaim.getProject().getProjectCode());
		Expense expense = expenseService.findByCode(expenseClaim.getExpense().getExpenseCode());
		
		
		expenseClaim.setEmployee(employee);
		expenseClaim.setExpense(expense);
		expenseClaim.setProject(project);
		
		return expenseClaimRepository.save(expenseClaim);
	}
	public List<ExpenseClaim> getAllExpenseClaim(){
		
		List<ExpenseClaim> expenseClaims = expenseClaimRepository.findAll();
		
		if(expenseClaims.isEmpty())
		{
			throw new ExpenseClaimNotFoundException("no Claims found!!");
		}
		
		return expenseClaims;
		
	}
	
	public ExpenseClaim fetchExpenseClaimById(int expenseCodeID) {
		
		Optional<ExpenseClaim> expenseClaim = expenseClaimRepository.findById(expenseCodeID);
		if(!expenseClaim.isPresent())
		{
			throw new ExpenseClaimNotFoundException("No Claims found with expenseCode ID " + expenseCodeID);
		}
		
		return expenseClaim.get();
	
	}
	
	public ExpenseClaim updateExpenseClaim(ExpenseClaim expenseClaim) {
		
		return null;
		
	}
	
	public ExpenseClaim deleteExpenseClaimById(int id) {
		
		ExpenseClaim expenseClaim = this.fetchExpenseClaimById(id);
		
		expenseClaimRepository.delete(expenseClaim);
		
		return expenseClaim;
		
	}
	
	public List<ExpenseClaim> getAllClaimsByEmployee(Employee employee){
		
		Employee foundEmployee = employeeService.findByEmployeeCode(employee.getEmpId());
		
		List<ExpenseClaim> expenseClaims = expenseClaimRepository.findByEmployee(foundEmployee);		
		if(expenseClaims.isEmpty())
		{
			throw new ExpenseClaimNotFoundException("no Expense claim  found with username = \" + username ");
		}
		
		return expenseClaims;
	}


	
}
