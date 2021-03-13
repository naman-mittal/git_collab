package com.cap.exs;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;


import com.cap.exs.entities.LoginDetails;
import com.cap.exs.exceptions.EmployeeAssociatedException;
import com.cap.exs.exceptions.UsernameAlreadyExistException;
import com.cap.exs.repos.ILoginRepository;
import com.cap.exs.services.LoginService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestLoginService {
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	ILoginRepository loginRepository;
	
	
	
//	@Test
	public void testAddDetails() {
	LoginDetails ld =new LoginDetails();
	ld.setUserName("aman");
	ld.setPassword("aman1");
	ld.setRole("analyst");

	
	
	assertEquals(ld,loginService.addDetails(ld));	
	}
	
//	@Test(expected = UsernameAlreadyExistException.class)
	public void testAddDetailsWithExistingUsername() {
		LoginDetails ld = new LoginDetails();
		ld.setUserName("lalit");
		loginService.addDetails(ld);
	}
	
	@Test(expected = EmployeeAssociatedException.class)
	public void testDeleteLoginDetailsById()
	{
		LoginDetails ld = new LoginDetails();
		ld.setId(2);
		
		loginService.deleteDetailsById(2);
		
		assertEquals(2, loginRepository.count());
	}
}
