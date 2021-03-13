package com.cap.exs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cap.exs.entities.Employee;
import com.cap.exs.entities.Expense;
import com.cap.exs.entities.ExpenseClaim;
import com.cap.exs.entities.Project;
import com.cap.exs.exceptions.EmployeeNotFoundException;
import com.cap.exs.exceptions.ExpenseNotFoundException;
import com.cap.exs.exceptions.ProjectNotFoundException;
import com.cap.exs.repos.IExpenseClaimRepository;
import com.cap.exs.services.ExpenseClaimService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestExpenseClaimService {
	
	Project project;
	Expense expense;
	Employee employee;
	ExpenseClaim expenseClaim;
	List<ExpenseClaim> claims;
	
	@Autowired
	ExpenseClaimService expenseClaimService;
	
	@Autowired
	IExpenseClaimRepository expenseClaimRepository;

	@Before
	public void setup() {
		
		project = new Project();
		project.setProjectCode(6);
		
		expense = new Expense();
		expense.setExpenseCode(2);
		
	    employee = new Employee();
	    employee.setEmpId(1);
	    
	    expenseClaim = new ExpenseClaim(45000,LocalDate.of(2021, 01, 01),LocalDate.of(2021, 05, 20),expense,project,employee);
	    
	    ExpenseClaim expenseClaim1 = new ExpenseClaim(40000,LocalDate.of(2021, 01, 01),LocalDate.of(2021, 05, 20),expense,project,employee);
	    
	    claims = Arrays.asList(expenseClaim1,expenseClaim);  
	}
	
//	@Test
	public void testAddExpenseClaim() {
	
		assertNotNull(expenseClaimService.addExpenseClaim(expenseClaim));
	}
	
//	@Test(expected=EmployeeNotFoundException.class)
	public void testAddExpenseClaimWithNonExistingEmployee() {
		
		 employee = new Employee();
		 employee.setEmpId(100);
		 expenseClaim.setEmployee(employee);
		
		 assertNotNull(expenseClaimService.addExpenseClaim(expenseClaim));
	}
	
	@Test(expected=ProjectNotFoundException.class)
	public void testAddExpenseClaimWithNonExistingProject() {
		
		 project = new Project();
		 project.setProjectCode(100);
		 expenseClaim.setProject(project);
		
		 assertNotNull(expenseClaimService.addExpenseClaim(expenseClaim));
	}
	
	@Test(expected=ExpenseNotFoundException.class)
	public void testAddExpenseClaimWithNonExistingExpense() {
		
		 expense = new Expense();
		 expense.setExpenseCode(50);
		 expenseClaim.setExpense(expense);
		
		 assertNotNull(expenseClaimService.addExpenseClaim(expenseClaim));
	}

//	@Test
	public void testGetAllExpenseClaim() {
		
		assertEquals(4, expenseClaimService.getAllExpenseClaim().size());
	}
	
//	@Test
	public void testFetchExpenseClaimById() {
		
		assertNotNull(expenseClaimService.fetchExpenseClaimById(1));
	}
	
//	@Test
	public void testUpdateExpenseClaim() {
	
		expenseClaim.setEmployee(null);
		expenseClaim.setExpense(null);
		expenseClaim.setProject(null);
		
		expenseClaim.setExpenseCodeId(1);
		expenseClaim.setExpenseAmount(33333);
		
		assertNotNull(expenseClaimService.updateExpenseClaim(expenseClaim));
	}
	
	
//	@Test
	public void testDeleteExpenseClaimById() {
		
		expenseClaimService.deleteExpenseClaimById(10);
		assertEquals(3, expenseClaimRepository.count());
	}
	
//	@Test
	public void testGetAllClaimsByEmployee() {
		
		assertNotNull(expenseClaimService.getAllClaimsByEmployee(employee));
	}
	
	@Test(expected=EmployeeNotFoundException.class)
	public void testGetAllClaimsByEmployeeWithNonExistingEmployee() {
		
		employee = new Employee();
		employee.setEmpId(100);
		assertNotNull(expenseClaimService.getAllClaimsByEmployee(employee));
	}
	
	
//	@Test
	public void testFindAllClaimsBetweenDates() {
		
		assertEquals(2, expenseClaimService.findAllClaimsBetweenDates(LocalDate.of(2020, 01, 01), LocalDate.now().plusDays(6)).size());
	}

}
