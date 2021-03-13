package com.cap.exs;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import com.cap.exs.entities.Expense;
import com.cap.exs.repos.IExpenseRepository;
import com.cap.exs.services.ExpenseService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestExpenseService {
	
	@Autowired
	IExpenseRepository expenseRepository;
	
	
	@Autowired
	ExpenseService expenseService;
	
	
	
	//@Test
	public void testAddExpense()
	{
		Expense exp = new Expense();
		exp.setExpenseType("abc");
		exp.setExpenseDescription("okok");
		assertEquals(1, expenseRepository.count());
		
	}
	
	
	
	@Test
	public void testFindByExpenseCode()
	{
			Expense exp = expenseService.findByCode(1);
			assertNotNull(exp);	
	}
	
	
	
	//@Test
		void testUpdateExpense() {
			Expense exp = new Expense();
			exp.setExpenseCode(101);
			exp.setExpenseType("abc");
			exp.setExpenseDescription("decs");
			assertNotNull(expenseService.updateExpense(exp));
		}
		
		
		
	//@Test
	void testGetAllExpenseCodes(){
		List<Integer> allExpenseCodes = expenseService.getAllExpenseCode();
		assertEquals(2, allExpenseCodes.size());	
	}
	
	
	
	//@Test
		void testDeleteExpenseByCode() {
			expenseService.deleteExpenseByCode(1);
			assertEquals(1, expenseRepository.count());
		}
	
		
		
	//@Test
	void testGetAllExpenses(){
		assertEquals(2, expenseService.getAllExpenses().size());	
		}
	
	

}
