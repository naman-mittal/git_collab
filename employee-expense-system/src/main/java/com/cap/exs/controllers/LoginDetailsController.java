package com.cap.exs.controllers;

import java.util.List;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cap.exs.entities.Employee;
import com.cap.exs.entities.LoginDetails;
import com.cap.exs.services.LoginService;

@RestController
public class LoginDetailsController {

	@Autowired
	LoginService loginService;
	
	@PostMapping("/loginDetails")
	@ResponseStatus(code=HttpStatus.CREATED)
	public LoginDetails addDetails(@RequestBody LoginDetails loginDetails) {
		return loginService.addDetails(loginDetails);
	}

	@DeleteMapping("/loginDetails/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteDetailsById(@PathVariable("id") @Min(1) int empId) {
		
		loginService.deleteDetailsById(empId);
	}
	
}
