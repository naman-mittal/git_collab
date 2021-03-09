package com.cap.exs.service_interfaces;

import java.util.List;

import com.cap.exs.entities.Employee;

public interface IEmployeeService {

	public Employee addEmployee(Employee employee);
	
	public List<Employee> getEmployees();
	
	public Employee findByEmployeeCode(int empId);
	
	public void deleteEmpById(int empId);
	
	public Employee updateEmployee(Employee emp);
	
	public Employee getDetailsByAll(String userName, String password, String role);
	
}
