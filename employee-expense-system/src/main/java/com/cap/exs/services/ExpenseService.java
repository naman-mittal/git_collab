package com.cap.exs.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.cap.exs.entities.Expense;
import com.cap.exs.exceptions.ExpenseAlreadyExistException;
import com.cap.exs.exceptions.ExpenseAssociatedException;
import com.cap.exs.exceptions.ExpenseNotFoundException;
import com.cap.exs.repos.IExpenseRepository;
import com.cap.exs.service_interfaces.IExpenseService;

@Service
public class ExpenseService implements IExpenseService {
	
	@Autowired
	IExpenseRepository expenseRepository;
	
	Logger logger = LoggerFactory.getLogger(ExpenseService.class);

		public List<Integer> getAllExpenseCode()
		{
			List<Integer> expensesCodes = expenseRepository.getAllExpenseCodes();
			
			if(expensesCodes.isEmpty())
			{
				String errorMessage = "No Expenses found!!";
				logger.error(errorMessage,ExpenseNotFoundException.class);
				throw new ExpenseNotFoundException(errorMessage);
			}
			
			return expensesCodes;
		}
		
		public Expense addExpense(Expense expense)
		{
			
			Expense foundExpense = expenseRepository.findByExpenseType(expense.getExpenseType());
			if(foundExpense != null)
			{
				throw new ExpenseAlreadyExistException("Expense Already Exist!!");
			}
					
			return expenseRepository.save(expense);
		}
		
		
		public List<Expense> getAllExpenses()
		{
			List<Expense> allExpenses = expenseRepository.findAll();
			
			if(allExpenses.isEmpty())
			{
				String errorMessage = "No Expenses found!!";
				logger.error(errorMessage,ExpenseNotFoundException.class);
				throw new ExpenseNotFoundException(errorMessage);
			}
			return allExpenses;
		}
		
		public Expense getExpenseByCode(int id)
		{
			 Optional<Expense> expense =  expenseRepository.findById(id);
			 if(!expense.isPresent())
				{
				 
				 	String errorMessage = String.format("No Expense found with code = %d", id);
					logger.error(errorMessage,ExpenseNotFoundException.class);
					throw new ExpenseNotFoundException(errorMessage);
				}
			 
			 return expense.get();
		}
		
		public Expense updateExpense(Expense expense)
		{	
			this.findByCode(expense.getExpenseCode());
			
			Expense foundExpense = expenseRepository.findByExpenseType(expense.getExpenseType());
			
			if(foundExpense!=null && foundExpense.getExpenseCode()!=expense.getExpenseCode())
			{
				String errorMessage = String.format("expense type : %s already exists... Cannot update!", expense.getExpenseType());
				logger.error(errorMessage, ExpenseAlreadyExistException.class);
				throw new ExpenseAlreadyExistException(errorMessage);
			}
			
			return expenseRepository.save(expense);
		}
		
		public Expense deleteExpenseByCode(int expenseCode)
		{
			Expense expense = this.findByCode(expenseCode);
			try
			{
				expenseRepository.delete(expense);
			}
			catch(DataIntegrityViolationException e)
			{
				String errorMessage = String.format(" Cannot delete! Expense claim exist for expensee = %s", expense.toString());
				logger.error(errorMessage,ExpenseAssociatedException.class);
				throw new ExpenseAssociatedException(errorMessage);
			}
			
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
				String errorMessage = String.format("No Expense found with code = %d", expensecode);
				logger.error(errorMessage,ExpenseNotFoundException.class);
				throw new ExpenseNotFoundException(errorMessage);
			}
			return expense.get();
		}

		

		

		
}
