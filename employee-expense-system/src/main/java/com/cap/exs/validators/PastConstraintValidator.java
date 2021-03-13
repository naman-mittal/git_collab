package com.cap.exs.validators;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PastConstraintValidator implements ConstraintValidator<Past, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		LocalDate date = null;
		
		try {
			
			date = LocalDate.parse(value,DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		}
		catch(Exception e)
		{
			return false;
		}
		return date.isBefore(LocalDate.now());
		
		
	}

}
