package com.cap.exs.handler;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cap.exs.exceptions.EmployeeNotFoundException;
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
	    return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	  }
//	
	@ExceptionHandler(EmployeeNotFoundException.class)
	  ResponseEntity<String> handleEmployeeNotFoundException(EmployeeNotFoundException e) {
	    return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	  }
	
	@ExceptionHandler(UsernameAlreadyExistException.class)
	  ResponseEntity<String> handleUsernameAlreadyExistException(UsernameAlreadyExistException e) {
	    return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
	  }
	@ExceptionHandler(ProjectNotFoundException.class)
	ResponseEntity<String> handleProjectNotFoundException(ProjectNotFoundException e)
	{
		
		return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		
	}
}
