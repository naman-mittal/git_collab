package com.cap.exs.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
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

import com.cap.exs.entities.ERole;
import com.cap.exs.entities.Employee;
import com.cap.exs.entities.LoginDetails;
import com.cap.exs.entities.Role;
import com.cap.exs.repos.IRoleRepository;
import com.cap.exs.request.SignupRequest;
import com.cap.exs.request.UpdateEmployeeRequest;
import com.cap.exs.response.MessageResponse;
import com.cap.exs.services.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Validated
@RequestMapping("/api/v1")
@Api(value = "Employee", tags = { "EmployeeAPI" })
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	IRoleRepository roleRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	// Add a new Employee
	
	@PostMapping("/signup")
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation(value = "Signup", response = Employee.class)
	@ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully signed up"),
            @ApiResponse(code = 400, message = "Check your input parameters"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
	public ResponseEntity<?> addEmployee(@ApiParam(name="Signup Request", required = true) @Valid @RequestBody SignupRequest request) {
		
		Employee employee = new Employee();
		LoginDetails loginDetails = new LoginDetails();
		
		employee.setEmpName(request.getName());
		employee.setEmpPAN(request.getPan());
		employee.setEmpDesignation(request.getDesignation());
		employee.setEmpDomain(request.getDomain());
		employee.setEmpDOB(request.getDob());
		employee.setEmpDOJ(request.getDoj());
		employee.setEmpSalary(request.getSalary());
		employee.setEmpEmailId(request.getEmail());
		
		loginDetails.setUserName(request.getUsername());
		loginDetails.setPassword(encoder.encode(request.getPassword()));
		loginDetails.setRole(request.getRole());
		
		Set<String> strRoles = request.getRoles();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "manager":
					Role modRole = roleRepository.findByName(ERole.ROLE_MANAGER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		loginDetails.setRoles(roles);
		
		employee.setLoginDetails(loginDetails);
		
		employeeService.addEmployee(employee);
		
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	
	// Get all the employees
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/employees")
	@ApiOperation(value = "Get all Employees", response = List.class)
	@ResponseStatus(code = HttpStatus.OK)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved all employees"),
            @ApiResponse(code = 400, message = "Check your input parameters"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "No employees found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
	public List<Employee> getEmployees(){
		
		return employeeService.getEmployees();
	}
	
	// Find an employee by its id
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/employee/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "Retrieve an employee using its Id", response = Employee.class)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Employee details"),
            @ApiResponse(code = 400, message = "Check your input parameters"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "No employee found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
	public Employee findByEmployeeCode(@PathVariable("id") @Positive int empId) {
		
		return employeeService.findByEmployeeCode(empId);		
	}
	
	// delete an employee by its id
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/employee/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Delete an employee by its Id")
	@ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully deleted"),
            @ApiResponse(code = 400, message = "Check your input parameters"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "No employee found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
	public void deleteEmpById(@PathVariable("id") @Positive int empId) {
		
		employeeService.deleteEmpById(empId);
	}
	
	// update employee's designation,domain and PAN
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	@PutMapping("/employee")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Update the employee", response = Employee.class)
	@ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully updated"),
            @ApiResponse(code = 400, message = "Check your input parameters"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "No employee found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
	public Employee updateEmployee(@ApiParam(name="Update Employee Request", required = true)@Valid @RequestBody UpdateEmployeeRequest request) {
		
		Employee employee = new Employee();
		
		employee.setEmpId(request.getId());
		employee.setEmpDesignation(request.getDesignation());
		employee.setEmpDomain(request.getDomain());
		employee.setEmpPAN(request.getPan());
		
		return employeeService.updateEmployee(employee);
	}
	
	// Get employee by its username, password and role
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/employee")
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "Retrieve an employee using its username, password and role", response = Employee.class)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved employee"),
            @ApiResponse(code = 400, message = "Check your input parameters"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "No employee found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
	public Employee getDetailsByAll(@ApiParam(name="Employee's username", required = true)@RequestParam(name = "userName") String username,@ApiParam(name="Employee's password", required = true) @RequestParam(name = "password") String password,@ApiParam(name="Employee's role", required = true) @RequestParam(name = "role") String role) {
		
		return employeeService.getDetailsByAll(username, password, role);
	}
	
	
}
