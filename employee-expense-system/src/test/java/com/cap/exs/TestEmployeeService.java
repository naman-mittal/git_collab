package com.cap.exs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cap.exs.entities.Employee;
import com.cap.exs.entities.LoginDetails;
import com.cap.exs.exceptions.EmailAlreadyRegisteredException;
import com.cap.exs.exceptions.EmployeeNotFoundException;
import com.cap.exs.exceptions.UsernameAlreadyExistException;
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
		
		loginDetails = new LoginDetails("naman1212", "ghdgfhdgf", "fdghfd");
		
		employee = new Employee("naman mittal","QTYIT5678R", "02/05/2020", "02/05/2020", "45000", "email11@gmail.com", loginDetails);
		
	}
	
	//@Test
	public void testAddEmployee() {
		
		
		Employee emp = employeeService.addEmployee(employee);
		
		assertNotNull(emp);
		
	}

	@Test(expected = UsernameAlreadyExistException.class)
		public void testAddEmployeeWithExistingUsername() {
			
			
			
			
			employee.setEmpEmailId("naman12345@gmail.com");
			employee.setEmpPAN("SDFTY3465T");
			employeeService.addEmployee(employee);
			
		}
		
		@Test(expected = EmailAlreadyRegisteredException.class)
				public void testAddEmployeeWithExistingEmail() {
					
					employee.setEmpEmailId("naman@gmail.com");
					
					employeeService.addEmployee(employee);
					
				}
	
	//@Test(expected = NullPointerException.class)
	public void testAddEmployeeWithoutLoginDetails() {
		
		
		
		employee.setLoginDetails(null);
		
		employeeService.addEmployee(employee);
		
	}
	
	@Test
	public void testFindByEmployeeCode()
	{
		employee.setEmpId(1);
		
		assertNotNull(employeeService.findByEmployeeCode(1));
		
	}

	@Test(expected = EmployeeNotFoundException.class)
	public void testFindNonExistingEmployee()
	{
		Employee emp = employeeService.findByEmployeeCode(100);
		System.out.println(emp);
		
	}
	
	//@Test
	public void testGetEmployees()
	{
		assertEquals(4, employeeRepository.count());
	}
	
	//@Test
	public void testDeleteEmployeeById()
	{
		Employee emp = new Employee();
		emp.setEmpId(1);
		when(employeeRepository.findById(1)).thenReturn(Optional.of(emp));
		employeeService.deleteEmpById(1);
		
		assertEquals(2,employeeRepository.count());
		
	}
	
	@Test(expected = EmployeeNotFoundException.class)
	public void testDeleteNonExistingEmployee()
	{
		
		employee.setEmpId(100);
		
		employeeService.deleteEmpById(employee.getEmpId());
		
		
	}
	
}
