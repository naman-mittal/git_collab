package com.cap.exs.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cap.exs.entities.Employee;
import com.cap.exs.entities.Expense;
import com.cap.exs.entities.ExpenseClaim;
import com.cap.exs.entities.Project;
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
	
	Logger logger = LoggerFactory.getLogger(ExpenseClaimService.class);
	
	@Transactional
	public ExpenseClaim addExpenseClaim(ExpenseClaim expenseClaim) {
		// finding employee object from database
		Employee employee = employeeService.findByEmployeeCode(expenseClaim.getEmployee().getEmpId());
		
		// finding project object from database
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
			String errorMessage = "No Claims Records Found!!";  
			logger.error(errorMessage, ExpenseClaimNotFoundException.class);
			throw new ExpenseClaimNotFoundException(errorMessage);
		}
		
		return expenseClaims;
		
	}
	
	public ExpenseClaim fetchExpenseClaimById(int expenseCodeID) {
		
		Optional<ExpenseClaim> expenseClaim = expenseClaimRepository.findById(expenseCodeID);
		if(!expenseClaim.isPresent())
		{
			String errorMessage = String.format("No Claims Records Found With Provided Expense ID : %d",expenseCodeID);  
			logger.error(errorMessage, ExpenseClaimNotFoundException.class);
			throw new ExpenseClaimNotFoundException(errorMessage);
		}
		
		return expenseClaim.get();
	
	}
	
	@Transactional
	public ExpenseClaim updateExpenseClaim(ExpenseClaim expenseClaim) {
		
		ExpenseClaim foundClaim = this.fetchExpenseClaimById(expenseClaim.getExpenseCodeId());
		
		foundClaim.setExpenseAmount(expenseClaim.getExpenseAmount());
		foundClaim.setStartDate(expenseClaim.getStartDate());
		foundClaim.setEndDate(expenseClaim.getEndDate());
		
		return foundClaim;
		
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
			String errorMessage = String.format("no Expense claims  found for employee :  %s", foundEmployee.getEmpName());
			logger.error(errorMessage,ExpenseClaimNotFoundException.class);
			throw new ExpenseClaimNotFoundException(errorMessage);
		}
		
		return expenseClaims;
	}

	public void deleteAllClaimsByEmployee(Employee employee) {
		
		Employee foundEmployee = employeeService.findByEmployeeCode(employee.getEmpId());
		
		List<ExpenseClaim> calims =  expenseClaimRepository.findByEmployee(foundEmployee);
		
		expenseClaimRepository.deleteAll(calims);
		
	}
	
	public void deleteAllClaimsByExpense(Expense expense) {
		
		Expense foundExpense = expenseService.findByCode(expense.getExpenseCode());
		
		List<ExpenseClaim> calims =  expenseClaimRepository.findByExpense(foundExpense);
		
		expenseClaimRepository.deleteAll(calims);
		
	}

	public void deleteAllClaimsByProject(Project project) {
	
	Project foundProject = projectSevice.findByCode(project.getProjectCode());
	
	List<ExpenseClaim> calims =  expenseClaimRepository.findByProject(foundProject);
	
	expenseClaimRepository.deleteAll(calims);
	
	}
	
	public List<ExpenseClaim> findAllClaimsBetweenDates(LocalDate startDate , LocalDate endDate){
		List<ExpenseClaim> claimWithinDates =  expenseClaimRepository.findAllBetweenDates(startDate,endDate);
		
		if(claimWithinDates.isEmpty()) {
			
			String errorMessage = String.format("No Claim Records Found between %s and %s dates!!", startDate.toString(),endDate.toString()); 
			logger.error(errorMessage, ExpenseClaimNotFoundException.class);
			throw new ExpenseClaimNotFoundException(errorMessage);
		}
		
		return claimWithinDates;
	}
	
	@Transactional
	public void approveClaim(ExpenseClaim expenseClaim)
	{
		
		ExpenseClaim claim = this.fetchExpenseClaimById(expenseClaim.getExpenseCodeId());
		
		claim.setStatus("Approved");
		
	}
}