package com.cap.exs.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cap.exs.entities.Expense;
import com.cap.exs.services.ExpenseService;

@RestController
public class ExpenseController {
	
	@Autowired
	private ExpenseService expenseService;
	
	@GetMapping("/expenses/expenseCode")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Integer> getAllExpenseCode()
	{
		return expenseService.getAllExpenseCode();
	}
	
	@PostMapping("/expenses")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Expense addExpense(@RequestBody Expense expense)
	{
		return expenseService.addExpense(expense);
	}
	
	@GetMapping("/expenses")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Expense> getAllExpenses()
	{
		return expenseService.getAllExpenses();
	}
	
	@GetMapping("/expenses/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Expense getExpenseByCode(@PathVariable("id") int expId)
	{
		return expenseService.getExpenseByCode(expId);
	}
	
	@PutMapping("/expenses")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public Expense updateExpense(@RequestBody Expense expense)
	{
		return expenseService.updateExpense(expense);
	}
	
	@DeleteMapping("/expenses/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public Expense deleteExpenseByCode(@PathVariable("id") int expCode)
	{
		return expenseService.deleteExpenseByCode(expCode);
	}
	
	
	

}
