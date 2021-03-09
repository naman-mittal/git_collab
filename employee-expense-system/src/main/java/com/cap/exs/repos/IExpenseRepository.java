package com.cap.exs.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cap.exs.entities.Expense;

public interface IExpenseRepository extends JpaRepository<Expense, Integer>{
	
	// public Expense findByCode(int code);
	@Query("SELECT e.expenseCode FROM Expense e")
	public List<Integer> getAllExpenseCodes();
	
	Expense findByExpenseType(String expenseType);
	
	

}
