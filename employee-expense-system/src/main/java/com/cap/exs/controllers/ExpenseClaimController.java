package com.cap.exs.controllers;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cap.exs.entities.Employee;
import com.cap.exs.entities.Expense;
import com.cap.exs.entities.ExpenseClaim;
import com.cap.exs.entities.Project;
import com.cap.exs.request.AddExpenseClaimRequest;
import com.cap.exs.request.UpdateExpenseClaimRequest;
import com.cap.exs.services.ExpenseClaimService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Validated
@RequestMapping("/api/v1")
@Api(value = "ExpenseClaim", tags = { "ExpenseClaimAPI" })
public class ExpenseClaimController {
	
	@Autowired
	ExpenseClaimService expenseClaimService;
	
	// Get all the Expense Claims
	@PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
	@GetMapping("/expenseClaims")
	@ApiOperation(value = "Get all Expense Claims", response = List.class)
	@ResponseStatus(code = HttpStatus.OK)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved all expense claims"),
            @ApiResponse(code = 400, message = "Check your input parameters"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "No expense claims found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
	public List<ExpenseClaim> getAllExpenseClaim(){
		return expenseClaimService.getAllExpenseClaim();
	}
	
	// Add Expense Claim
	
	@PreAuthorize("hasRole('USER')")
	@PostMapping("/expenseClaim")
	@ApiOperation(value = "Add expense claim", response = ExpenseClaim.class)
	@ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully added expense claim"),
            @ApiResponse(code = 400, message = "Check your input parameters"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
	})
	@ResponseStatus(code = HttpStatus.CREATED)
	public ExpenseClaim addExpenseClaim(@ApiParam(name="ExpenseClaim Request", required = true) @Valid @RequestBody AddExpenseClaimRequest request) {
		
		ExpenseClaim claim = new ExpenseClaim();
		
		claim.setExpenseAmount(request.getAmount());
		claim.setStartDate(request.getStartDate());
		claim.setEndDate(request.getEndDate());
		
		Employee employee = new Employee();
		employee.setEmpId(request.getEmployeeId());
		claim.setEmployee(employee);
		
		Project project = new Project();
		project.setProjectCode(request.getProjectId());
		claim.setProject(project);
		
		Expense expense = new Expense();
		expense.setExpenseCode(request.getExpenseId());
		claim.setExpense(expense);
		
		return expenseClaimService.addExpenseClaim(claim);
	}
	
	// Find an Expense Claim by its id
	@PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
	@GetMapping("/expenseClaim/{id}")
	@ApiOperation(value = "Retrieve expense claim using its Id", response = ExpenseClaim.class)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Expense claim details"),
            @ApiResponse(code = 400, message = "Check your input parameters"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "Expense claim not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
	@ResponseStatus(code = HttpStatus.OK)
	public ExpenseClaim fetchExpenseClaimById(@PathVariable("id") @Min(1) int expenseClaimId){
		return expenseClaimService.fetchExpenseClaimById(expenseClaimId);
	}
	
	// Update Expense Claim
	@PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
	@PutMapping("/expenseClaim")
	@ApiOperation(value = "Upadate the expense claim", response = ExpenseClaim.class)
	@ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully updated"),
            @ApiResponse(code = 400, message = "Check your input parameters"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "Expense Claim not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public ExpenseClaim updateExpenseClaim(@ApiParam(name="Update ExpenseClaim Request", required = true)@Valid @RequestBody UpdateExpenseClaimRequest request) {
		
		ExpenseClaim expenseClaim = new ExpenseClaim();
		expenseClaim.setExpenseCodeId(request.getId());
		expenseClaim.setStartDate(request.getStartDate());
		expenseClaim.setEndDate(request.getEndDate());
		expenseClaim.setExpenseAmount(request.getExpenseAmount());
		
		return expenseClaimService.updateExpenseClaim(expenseClaim);
	}
	
	// Delete an Expense Claim by its id
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/expenseClaim/{id}")
	@ApiOperation(value = "Delete expense claim", response = ExpenseClaim.class)
	@ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully deleted"),
            @ApiResponse(code = 400, message = "Check your input parameters"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "Expense claim not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public ExpenseClaim deleteExpenseClaimById(@PathVariable("id") @Min(1) int expenseClaimId) {
		return expenseClaimService.deleteExpenseClaimById(expenseClaimId);
	}
	
	// Get Expense Claim by Employee's id
	@PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('USER')")
	@GetMapping("/expenseClaims/employee")
	@ApiOperation(value = "Get all Expense Claims by Employee", response = List.class)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved all expense claims"),
            @ApiResponse(code = 400, message = "Check your input parameters"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "No expense claims found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
	@ResponseStatus(code = HttpStatus.OK)
	public List<ExpenseClaim> getAllClaimsByEmployee(Employee employee){
		return expenseClaimService.getAllClaimsByEmployee(employee);
	}
	
	// Find all Expense Claims between two dates
	@PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
	@GetMapping("/expenseClaims/dates")
	@ApiOperation(value = "Get all Expense Claims between two dates", response = List.class)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved all expense claims"),
            @ApiResponse(code = 400, message = "Check your input parameters"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "No expense claims found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
	@ResponseStatus(code = HttpStatus.OK)
	public List<ExpenseClaim> findAllClaimsBetweenDates(@RequestParam("startDate")@DateTimeFormat(pattern="MM/dd/yyyy") LocalDate startDate ,@RequestParam("endDate")@DateTimeFormat(pattern="MM/dd/yyyy") LocalDate endDate){
		return expenseClaimService.findAllClaimsBetweenDates(startDate, endDate);
	}

}
