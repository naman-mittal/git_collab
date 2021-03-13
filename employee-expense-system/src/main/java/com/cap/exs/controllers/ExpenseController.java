package com.cap.exs.controllers;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cap.exs.entities.Expense;
import com.cap.exs.request.AddExpenseRequest;
import com.cap.exs.request.UpdateExpenseRequest;
import com.cap.exs.services.ExpenseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Validated
@RequestMapping("/api/v1")
@Api(value = "Expense", tags = { "ExpenseAPI" })
public class ExpenseController {
	
	@Autowired
	private ExpenseService expenseService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/expense/expenseCode")
	@ApiOperation(value = "Get all Expense Code", response = List.class)
	@ResponseStatus(code = HttpStatus.OK)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved all expense code"),
            @ApiResponse(code = 400, message = "Check your input parameters"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "No expense claims found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
	public List<Integer> getAllExpenseCode()
	{
		return expenseService.getAllExpenseCode();
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/expense")
	@ApiOperation(value = "Add expense", response = Expense.class)
	@ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully added expense"),
            @ApiResponse(code = 400, message = "Check your input parameters"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
	})
	@ResponseStatus(code = HttpStatus.CREATED)
	public Expense addExpense(@ApiParam(name="Expense Request", required = true)@Valid @RequestBody AddExpenseRequest request)
	{
		Expense expense = new Expense();
		expense.setExpenseType(request.getExpenseType());
		expense.setExpenseDescription(request.getExpenseDescription());
		return expenseService.addExpense(expense);
	}
	
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER') or hasRole('MANAGER')")
	@GetMapping("/expenses")
	@ApiOperation(value = "Get all Expenses", response = List.class)
	@ResponseStatus(code = HttpStatus.OK)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved all expenses"),
            @ApiResponse(code = 400, message = "Check your input parameters"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "No expense claims found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
	public List<Expense> getAllExpenses()
	{
		return expenseService.getAllExpenses();
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/expense/{id}")
	@ApiOperation(value = "Retrieve expense using its Id", response = Expense.class)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Expense details"),
            @ApiResponse(code = 400, message = "Check your input parameters"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
	@ResponseStatus(code = HttpStatus.OK)
	public Expense getExpenseByCode(@PathVariable("id") @Positive int expId)
	{
		return expenseService.getExpenseByCode(expId);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/expense")
	@ApiOperation(value = "Upadate the expense", response = Expense.class)
	@ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully updated"),
            @ApiResponse(code = 400, message = "Check your input parameters"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "Expense not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public Expense updateExpense(@ApiParam(name="Update Expense Request", required = true)@Valid @RequestBody UpdateExpenseRequest request)
	{
		Expense expense = new Expense();
		expense.setExpenseCode(request.getExpenseCode());
		expense.setExpenseType(request.getExpenseType());
		expense.setExpenseDescription(request.getExpenseDescription());
		return expenseService.updateExpense(expense);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/expense/{id}")
	@ApiOperation(value = "Delete expense", response = Expense.class)
	@ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully deleted"),
            @ApiResponse(code = 400, message = "Check your input parameters"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "No expense found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public Expense deleteExpenseByCode(@PathVariable("id") @Positive int expCode)
	{
		return expenseService.deleteExpenseByCode(expCode);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/expenses")
	@ApiOperation(value = "Delete all expenses")
	@ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully deleted all expenses"),
            @ApiResponse(code = 400, message = "Check your input parameters"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "No expenses found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteAllExpenses()
	{
		expenseService.deleteAllExpenses();
	}
	
	
	

}
