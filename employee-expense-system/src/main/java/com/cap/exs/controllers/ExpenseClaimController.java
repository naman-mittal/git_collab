package com.cap.exs.controllers;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.cap.exs.exceptions.ExpenseClaimNotFoundException;
import com.cap.exs.request.AddExpenseClaimRequest;
import com.cap.exs.request.UpdateExpenseClaimRequest;
import com.cap.exs.response.MessageResponse;
import com.cap.exs.services.ExpenseClaimService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = "*",maxAge = 30)
@RestController
@Validated
@RequestMapping("/api/v1")
@Api(value = "ExpenseClaim", tags = { "ExpenseClaimAPI" })
public class ExpenseClaimController {
	
	@Autowired
	ExpenseClaimService expenseClaimService;
	
	/**
	 * This method is for fetching all expense claims
	 * 
	 * @return List<ExpenseClaim>
	 * @throws @{@link ExpenseClaimNotFoundException}
	 * 
	 */
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
	
	/**
	 * This method is for adding a new expense claims
	 * 
	 * @param AddExpenseClaimRequest
	 * @return List<ExpenseClaim>
	 * @throws {@link EmployeeNotFoundException}
	 * @throws {@link ExpenseNotFoundException}
	 * @throws {@link ProjectNotFoundException}
	 * @throws {@link MethodArgumentNotValidException}
	 * @throws {@link InvalidEndDateException}
	 * 
	 */
	
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
	
	/**
	 * This method is for fetching an expense claim by its id
	 * 
	 * @param expenseClaimId
	 * @return ExpenseClaim
	 * @throws {@link ExpenseClaimNotFoundException}
	 * @throws {@link MethodArgumentNotValidException}
	 * 
	 */
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
	public ExpenseClaim fetchExpenseClaimById(@PathVariable("id") @Positive int expenseClaimId){
		return expenseClaimService.fetchExpenseClaimById(expenseClaimId);
	}
	
	/**
	 * This method is for updating an expense claim
	 * 
	 * @param UpdateExpenseClaimRequest
	 * @return ExpenseClaim
	 * @throws {@link EmployeeNotFoundException}
	 * @throws {@link MethodArgumentNotValidException}
	 * @throws {@link InvalidEndDateException}
	 * 
	 */
	
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
	
	/**
	 * This method is for deleting an expense claims
	 * 
	 * @param expenseClaimId
	 * @return ExpenseClaim
	 * @throws {@link ExpenseClaimNotFoundException}
	 * @throws {@link MethodArgumentNotValidException}
	 * 
	 */
	
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
	
	/**
	 * This method is for fetching an expense claim by its id
	 * 
	 * @param expenseClaimId
	 * @return ExpenseClaim
	 * @throws {@link ExpenseClaimNotFoundException}
	 * @throws {@link MethodArgumentNotValidException}
	 * 
	 */
	
	@PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('USER')")
	@GetMapping("/expenseClaims/employee/{id}")
	@ApiOperation(value = "Get all Expense Claims by Employee", response = List.class)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved all expense claims"),
            @ApiResponse(code = 400, message = "Check your input parameters"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "No expense claims found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
	@ResponseStatus(code = HttpStatus.OK)
	public List<ExpenseClaim> getAllClaimsByEmployee(@PathVariable("id") int empId){
		
		Employee employee = new Employee();
		employee.setEmpId(empId);
		return expenseClaimService.getAllClaimsByEmployee(employee);
	}
	
	/**
	 * This method is for approving an expense claim
	 * 
	 * @param ExpenseClaim
	 * @return MessageResponse
	 * @throws {@link ExpenseClaimNotFoundException}
	 * @throws {@link MethodArgumentNotValidException}
	 * 
	 */
	
		@PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
		@PutMapping("/expenseClaim/approve/{id}")
		@ApiOperation(value = "Get all Expense Claims by Employee", response = List.class)
		@ApiResponses(value = {
	            @ApiResponse(code = 200, message = "Successfully retrieved all expense claims"),
	            @ApiResponse(code = 400, message = "Check your input parameters"),
	            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	            @ApiResponse(code = 404, message = "No expense claims found"),
	            @ApiResponse(code = 500, message = "Application failed to process the request")
	    })
		@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public ResponseEntity<MessageResponse> approveClaim(@PathVariable("id") @Positive int expenseClaimId){
			
			ExpenseClaim expenseClaim = new ExpenseClaim();
			expenseClaim.setExpenseCodeId(expenseClaimId);
			
			expenseClaimService.approveClaim(expenseClaim);
			return ResponseEntity.ok(new MessageResponse("Claim Approved"));
		}
	
		/**
		 * This method is for finding claims between two dates
		 * 
		 * @param startDate
		 * @param endDate
		 * @return List<ExpenseClaim>
		 * @throws {@link ExpenseClaimNotFoundException}
		 * @throws {@link MethodArgumentNotValidException}
		 * 
		 */
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
