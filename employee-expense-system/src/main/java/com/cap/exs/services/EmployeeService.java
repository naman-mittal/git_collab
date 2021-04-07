 package com.cap.exs.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.cap.exs.entities.Employee;
import com.cap.exs.entities.LoginDetails;
import com.cap.exs.exceptions.EmailAlreadyRegisteredException;
import com.cap.exs.exceptions.EmployeeNotFoundException;
import com.cap.exs.exceptions.ExpenseClaimAssociatedException;
import com.cap.exs.exceptions.PANAlreadyRegisteredException;
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
	
	Logger logger = LoggerFactory.getLogger(EmployeeService.class);
	
	@Transactional
	public Employee addEmployee(Employee employee) {
		
		Employee foundEmployee = employeeRepository.findByEmpEmailId(employee.getEmpEmailId());
		
		if(foundEmployee!=null)
		{
			String errorMessage = "email already registered!!";
			logger.error(errorMessage, EmailAlreadyRegisteredException.class);
			throw new EmailAlreadyRegisteredException(errorMessage);
		}
		
		if(employee.getEmpPAN()!=null)
		{
			
			foundEmployee = employeeRepository.findByEmpPAN(employee.getEmpPAN());
			if(foundEmployee!=null)
			{
				String errorMessage = "PAN already registered!!";
				logger.error(errorMessage, PANAlreadyRegisteredException.class);
				throw new PANAlreadyRegisteredException(errorMessage);
			}
			
		}
		
		
		LoginDetails loginDetails = loginService.addDetails(employee.getLoginDetails());
		
		employee.setLoginDetails(loginDetails);
		
		return employeeRepository.save(employee);
	}
	
	public List<Employee> getEmployees(){
		
		List<Employee> employees = employeeRepository.findAll();
		
		if(employees.isEmpty())
		{
			String errorMessage = "no employees found!!";
			logger.error(errorMessage,EmployeeNotFoundException.class);
			throw new EmployeeNotFoundException(errorMessage);
		}
		
		return employees;
		
	}
	
	public Employee findByEmployeeCode(int empId) {
		
		Optional<Employee> employee = employeeRepository.findById(empId);
		
		if(!employee.isPresent())
		{
			String errorMessage = String.format("no employee found with id = %d", empId);
			logger.error(errorMessage,EmployeeNotFoundException.class);
			throw new EmployeeNotFoundException(errorMessage);
		}
		
		return employee.get();
		
	}
	
	public void deleteEmpById(int empId) {
		
		Employee employee = this.findByEmployeeCode(empId);
		
		try
		{
		employeeRepository.delete(employee);
		// loginService.deleteDetailsById(employee.getLoginDetails().getId());
		}
		catch(DataIntegrityViolationException e)
		{
			String errorMessage = String.format("Cannot delete! Expense claim exist for employee = %s", employee.toString());
			logger.error(errorMessage,ExpenseClaimAssociatedException.class);
			throw new ExpenseClaimAssociatedException(errorMessage);
		}
		
	}
	
	@Transactional
	public Employee updateEmployee(Employee employee) {
		
		Employee foundEmployee = this.findByEmployeeCode(employee.getEmpId());
		
		if(employee.getEmpPAN()!=null)
		{
		
			Employee found = employeeRepository.findByEmpPAN(employee.getEmpPAN());
			
			if(found!=null && found.getEmpId()!=employee.getEmpId())
			{
				String errorMessage = "pan already registered... Cannot update !!";
				logger.error(errorMessage, PANAlreadyRegisteredException.class);
				throw new PANAlreadyRegisteredException(errorMessage);
			}
			
		}
		
		
		
		Employee found = employeeRepository.findByEmpEmailId(employee.getEmpEmailId());
		
		if(found!=null && found.getEmpId()!=employee.getEmpId())
		{
			String errorMessage = "email already registered... Cannot update !!";
			logger.error(errorMessage, EmailAlreadyRegisteredException.class);
			throw new EmailAlreadyRegisteredException(errorMessage);
		}
		
//		LoginDetails loginDetails = loginRepository.findByUserName(employee.getLoginDetails().getUserName());
//		
//		if(loginDetails!=null && loginDetails.getId()!=employee.getLoginDetails().getId())
//		{
//			String errorMessage = "username already taken!!";
//			logger.error(errorMessage, UsernameAlreadyExistException.class);
//			throw new UsernameAlreadyExistException(errorMessage);
//		}
//		
		foundEmployee.setEmpDesignation(employee.getEmpDesignation());
		foundEmployee.setEmpDomain(employee.getEmpDomain());
		
		foundEmployee.setEmpName(employee.getEmpName());
		foundEmployee.setEmpDOB(employee.getEmpDOB());
		foundEmployee.setEmpDOJ(employee.getEmpDOJ());
		
		foundEmployee.setEmpEmailId(employee.getEmpEmailId());
		
		foundEmployee.setEmpPAN(employee.getEmpPAN());
		
		foundEmployee.setEmpSalary(employee.getEmpSalary());
		
		LoginDetails ld = foundEmployee.getLoginDetails();
		
		ld.setRole(employee.getLoginDetails().getRole());
		
		foundEmployee.setLoginDetails(ld);
//		
//		foundEmployee.setEmpName(employee.getEmpName());
//		foundEmployee.setEmpDOB(employee.getEmpDOB());
		
		
		
		
		return foundEmployee;
	}
	
	public Employee getDetailsByAll(String username, String password, String role) {
		
		LoginDetails loginDetails = new LoginDetails(username,password,role);
		
		LoginDetails foundLoginDetails = loginService.validateUser(loginDetails);
		
		Employee employee = employeeRepository.findByLoginDetails(foundLoginDetails);
		
		if(employee==null)
		{
			String errorMessage = String.format("no employee found with username = %s And password = %s and role = %s", username,password,role);
			logger.error(errorMessage,EmployeeNotFoundException.class);
			throw new EmployeeNotFoundException(errorMessage);
		}
		
		return employee;
	}
	
}
