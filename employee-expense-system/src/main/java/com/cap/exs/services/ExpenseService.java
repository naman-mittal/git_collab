package com.cap.exs.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cap.exs.entities.Expense;
import com.cap.exs.exceptions.ExpenseNotFoundException;
import com.cap.exs.exceptions.NullExpenseFoundException;
import com.cap.exs.repos.IExpenseRepository;
import com.cap.exs.service_interfaces.IExpenseService;

@Service
public class ExpenseService implements IExpenseService {
	
	@Autowired
	IExpenseRepository expenseRepository;

		public List<Integer> getAllExpenseCode()
		{
			List<Integer> expensesCodes = new ArrayList<Integer>();
			// expensesCodes = expenseRepository.findAll().stream().map(e->e.getExpenseCode()).collect(Collectors.toList());
			expensesCodes = expenseRepository.getAllExpenseCodes();
			return expensesCodes;
		}
		
		public Expense addExpense(Expense expense)
		{
			if(expense==null)
			throw new NullExpenseFoundException("expense can't be null");
			
			Expense foundExpense = expenseRepository.findByExpenseType(expense.getExpenseType());
			if(foundExpense != null)
			{
				// throw ExpenseAlreadyExist
			}
					
			return expenseRepository.save(expense);
		}
		
		
		public List<Expense> getAllExpenses()
		{
			List<Expense> allExpenses = new ArrayList<Expense>();
			expenseRepository.findAll().forEach(e->allExpenses.add(e));
			
			if(allExpenses.isEmpty())
			{
				throw new ExpenseNotFoundException("No Expenses found!!!!");
			}
			return allExpenses;
		}
		
		public Expense getExpenseByCode(int id)
		{
			 Optional<Expense> expense =  expenseRepository.findById(id);
			 if(!expense.isPresent())
				{
					throw new ExpenseNotFoundException("No Expense found with expenseCode: " + id);
				}
			 
			 return expense.get();
		}
		
		public Expense updateExpense(Expense expense)
		{	
			return expenseRepository.save(expense);
		}
		
		public Expense deleteExpenseByCode(int expenseCode)
		{
			Expense expense = expenseRepository.findById(expenseCode).get();
			expenseRepository.delete(expense);
			return expense;
		}
		
		public void deleteAllExpenses()
		{
			expenseRepository.deleteAll();
		}
		
		public Expense findByCode(int expensecode)
		{
			Optional<Expense> expense = expenseRepository.findById(expensecode);
			if(!expense.isPresent())
			{
				throw new ExpenseNotFoundException("No Expense found with expenseCode: " + expensecode);
			}
			return expense.get();
		}

		

		

		
}
