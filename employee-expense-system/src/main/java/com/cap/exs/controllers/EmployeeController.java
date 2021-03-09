package com.cap.exs.controllers;

import java.util.List;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cap.exs.entities.Employee;
import com.cap.exs.services.EmployeeService;

@RestController
@Validated
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@PostMapping("/employees")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Employee addEmployee(@RequestBody Employee employee) {
		
		return employeeService.addEmployee(employee);
	}
	
	@GetMapping("/employees")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Employee> getEmployees(){
		
		return employeeService.getEmployees();
	}
	
	@GetMapping("/employee/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Employee findByEmployeeCode(@PathVariable("id") @Min(1) int empId) {
		
		return employeeService.findByEmployeeCode(empId);		
	}
	
	@DeleteMapping("/employee/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteEmpById(@PathVariable("id") @Min(1) int empId) {
		
		employeeService.deleteEmpById(empId);
	}
	
	@PutMapping("/employee")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public Employee updateEmployee(@RequestBody Employee employee) {
		
		return employeeService.updateEmployee(employee);
	}
	
	@GetMapping("/employee")
	@ResponseStatus(code = HttpStatus.OK)
	public Employee getDetailsByAll(@RequestParam(name = "userName") String username, @RequestParam(name = "password") String password, @RequestParam(name = "role") String role) {
		
		return employeeService.getDetailsByAll(username, password, role);
	}
	
	
}
