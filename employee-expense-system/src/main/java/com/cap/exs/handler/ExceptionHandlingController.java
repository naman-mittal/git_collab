package com.cap.exs.handler;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.cap.exs.exceptions.EmailAlreadyRegisteredException;
import com.cap.exs.exceptions.EmployeeAssociatedException;
import com.cap.exs.exceptions.EmployeeNotFoundException;
import com.cap.exs.exceptions.ExpenseAlreadyExistException;
import com.cap.exs.exceptions.ExpenseClaimAssociatedException;
import com.cap.exs.exceptions.ExpenseClaimNotFoundException;
import com.cap.exs.exceptions.ExpenseNotFoundException;
import com.cap.exs.exceptions.InvalidEndDateException;
import com.cap.exs.exceptions.InvalidUserException;
import com.cap.exs.exceptions.NoIdProvidedException;
import com.cap.exs.exceptions.PANAlreadyRegisteredException;
import com.cap.exs.exceptions.ProjectNotFoundException;
import com.cap.exs.exceptions.UsernameAlreadyExistException;

@RestControllerAdvice
public class ExceptionHandlingController {

	@ExceptionHandler(ConstraintViolationException.class)
	  ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
	    return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	  }
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	  ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		
		List<String> errors = e.getFieldErrors().stream().map(err-> err.getField() + " : " +  err.getDefaultMessage()).collect(Collectors.toList());
	    return new ResponseEntity<>(errors.toString(), HttpStatus.BAD_REQUEST);
	  }
//	
	@ExceptionHandler(EmployeeNotFoundException.class)
	  ResponseEntity<String> handleEmployeeNotFoundException(EmployeeNotFoundException e) {
	    return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	  }
	
	@ExceptionHandler(ExpenseClaimNotFoundException.class)
	  ResponseEntity<String> handleExpenseClaimNotFoundException(ExpenseClaimNotFoundException e) {
	    return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	  }
	
	@ExceptionHandler(ExpenseNotFoundException.class)
	  ResponseEntity<String> handleExpenseNotFoundException(ExpenseNotFoundException e) {
	    return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	  }
	
	@ExceptionHandler(UsernameAlreadyExistException.class)
	  ResponseEntity<String> handleUsernameAlreadyExistException(UsernameAlreadyExistException e) {
	    return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
	  }
	
	@ExceptionHandler(EmailAlreadyRegisteredException.class)
	  ResponseEntity<String> handleEmailAlreadyRegisteredException(EmailAlreadyRegisteredException e) {
	    return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
	  }
	
	@ExceptionHandler(PANAlreadyRegisteredException.class)
	  ResponseEntity<String> handlePANAlreadyRegisteredException(PANAlreadyRegisteredException e) {
	    return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
	  }
	
	@ExceptionHandler(ExpenseAlreadyExistException.class)
	  ResponseEntity<String> handleExpenseAlreadyExistException(ExpenseAlreadyExistException e) {
	    return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
	  }
	
	@ExceptionHandler(ProjectNotFoundException.class)
	ResponseEntity<String> handleProjectNotFoundException(ProjectNotFoundException e)
	{
		
		return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(ExpenseClaimAssociatedException.class)
	ResponseEntity<String> handleExpenseClaimAssociatedException(ExpenseClaimAssociatedException e)
	{
		
		return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(EmployeeAssociatedException.class)
	ResponseEntity<String> handleEmployeeAssociatedException(EmployeeAssociatedException e)
	{
		
		return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(InvalidUserException.class)
	ResponseEntity<String> handleInvalidUserException(InvalidUserException e)
	{
		
		return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		
	}
	
	
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException e)
	{
		
		return new ResponseEntity<>(e.getMostSpecificCause().getMessage(),HttpStatus.BAD_REQUEST);
		
	}
	
	
	
	@ExceptionHandler(InvalidEndDateException.class)
	ResponseEntity<String> handleInvalidEndDateException(InvalidEndDateException e)
	{
		
		return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		
	}
	
	
	
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	ResponseEntity<String> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e)
	{
		
		return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		
	}
	
	
	
	@ExceptionHandler(NoIdProvidedException.class)
	ResponseEntity<String> handleNoIdProvidedException(NoIdProvidedException e)
	{
		
		return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		
	}
}
