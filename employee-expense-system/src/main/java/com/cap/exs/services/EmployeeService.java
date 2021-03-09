package com.cap.exs.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cap.exs.entities.Employee;
import com.cap.exs.entities.LoginDetails;
import com.cap.exs.exceptions.EmployeeNotFoundException;
import com.cap.exs.exceptions.UsernameAlreadyExistException;
import com.cap.exs.repos.IEmployeeRepository;
import com.cap.exs.repos.ILoginRepository;
import com.cap.exs.service_interfaces.IEmployeeService;

@Service
public class EmployeeService implements IEmployeeService {

	@Autowired
	IEmployeeRepository employeeRepository;
	
	@Autowired
	ILoginRepository loginRepository;
	
	@Autowired
	LoginService loginService;
	
	public Employee addEmployee(Employee employee) {
		
		LoginDetails loginDetails = loginRepository.findByUserName(employee.getLoginDetails().getUserName());
		
		if(loginDetails!=null)
		{
			throw new UsernameAlreadyExistException("username " + loginDetails.getUserName() + " already exist!!");
		}
		
		return employeeRepository.save(employee);
	}
	
	public List<Employee> getEmployees(){
		
		List<Employee> employees = employeeRepository.findAll();
		
		if(employees.isEmpty())
		{
			throw new EmployeeNotFoundException("no employees found!!");
		}
		
		return employees;
		
	}
	
	public Employee findByEmployeeCode(int empId) {
		
		Optional<Employee> employee = employeeRepository.findById(empId);
		
		if(!employee.isPresent())
		{
			throw new EmployeeNotFoundException("no employee found with id = " + empId);
		}
		
		return employee.get();
		
	}
	
	public void deleteEmpById(int empId) {
		
		Employee employee = this.findByEmployeeCode(empId);
		
		employeeRepository.delete(employee);
		
	}
	
	public Employee updateEmployee(Employee employee) {
		
		//update logic
		return null;
	}
	
	public Employee getDetailsByAll(String username, String password, String role) {
		
		LoginDetails loginDetails = new LoginDetails(username,password,role);
		
		LoginDetails foundLoginDetails = loginService.validateUser(loginDetails);
		
		Employee employee = employeeRepository.findByLoginDetails(foundLoginDetails);
		
		if(employee==null)
		{
			throw new EmployeeNotFoundException("no employee found with username = " + username + " AND password = " + password + " AND role = " + role);
		}
		
		return employee;
	}
	
}
