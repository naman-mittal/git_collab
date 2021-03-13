package com.cap.exs;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cap.exs.entities.Employee;
import com.cap.exs.entities.LoginDetails;
import com.cap.exs.exceptions.EmployeeNotFoundException;
import com.cap.exs.repos.IEmployeeRepository;
import com.cap.exs.services.EmployeeService;


@SpringBootTest
@RunWith(SpringRunner.class)
public class TestEmployeeService {

	Employee employee;
	LoginDetails loginDetails;
	
	@Autowired
	EmployeeService employeeService;


	@Autowired
	IEmployeeRepository employeeRepository;
	
	@Before
	public void setup()
	{
		
		loginDetails = new LoginDetails("naman", "ghdgfhdgf", "fdghfd");
		
		employee = new Employee("naman mittal","RTYUT5678R", "02/05/2020", "02/05/2020", "45000", "email@gmail.com", loginDetails);
		
	}
	
	@Test
	public void testAddEmployee() {
		
		
		Employee emp = employeeService.addEmployee(employee);
		
		System.out.println(emp);
		
	}

	//@Test(expected = UsernameAlreadyExistException.class)
		public void testAddEmployeeWithExistingUsername() {
			
			Employee emp = new Employee();
			emp.setEmpName("John");
			
			LoginDetails ld = new LoginDetails("test2", "test", "tester");
			emp.setLoginDetails(ld);
			
			employeeService.addEmployee(emp);
			
		}
	
	//@Test(expected = NullPointerException.class)
	public void testAddEmployeeWithoutLoginDetails() {
		
		Employee emp = new Employee();
		emp.setEmpName("Danny");
		
		employeeService.addEmployee(emp);
		
	}
	
	//@Test
	public void testFindByEmployeeCode()
	{
		Employee emp = new Employee("Naman", null, null, null, null, null, new LoginDetails());
		emp.setEmpId(1);
		
		assertEquals(emp,employeeService.findByEmployeeCode(2));
		
	}

	//@Test(expected = EmployeeNotFoundException.class)
	public void testFindNonExistingEmployee()
	{
		when(employeeRepository.findById(100)).thenThrow(EmployeeNotFoundException.class);
		Employee emp = employeeService.findByEmployeeCode(100);
		System.out.println(emp);
		
	}
	
	//@Test
	public void testGetEmployees()
	{
		
		Employee emp1 = new Employee();
		emp1.setEmpName("naman");
		Employee emp2 = new Employee();
		emp2.setEmpName("aman");
		
		when(employeeRepository.findAll()).thenReturn(Arrays.asList(emp1,emp2));
		assertEquals(2, employeeService.getEmployees().size());
	}
	
	//@Test
	public void testDeleteEmployeeById()
	{
		Employee emp = new Employee();
		emp.setEmpId(1);
		when(employeeRepository.findById(1)).thenReturn(Optional.of(emp));
		employeeService.deleteEmpById(1);
		
		verify(employeeRepository,times(1)).delete(emp);
		
	}
	
	//@Test(expected = EmployeeNotFoundException.class)
	public void testDeleteNonExistingEmployee()
	{
		Employee emp = new Employee("Naman", null, null, null, null, null, new LoginDetails());
		emp.setEmpId(100);
		when(employeeRepository.findById(emp.getEmpId())).thenThrow(EmployeeNotFoundException.class);
		employeeService.deleteEmpById(emp.getEmpId());
		
		verify(employeeRepository,times(1)).delete(emp);
	}
	
}
