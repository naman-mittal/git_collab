package com.cap.exs.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.cap.exs.entities.LoginDetails;
import com.cap.exs.exceptions.EmployeeAssociatedException;
import com.cap.exs.exceptions.InvalidUserException;
import com.cap.exs.exceptions.UsernameAlreadyExistException;
import com.cap.exs.repos.IEmployeeRepository;
import com.cap.exs.repos.ILoginRepository;
import com.cap.exs.service_interfaces.ILoginService;

@Service
public class LoginService implements ILoginService{

	@Autowired
	IEmployeeRepository employeeRepository;
	
	@Autowired
	ILoginRepository loginRepository;
	
	Logger logger = LoggerFactory.getLogger(LoginService.class);
	
	//method to add details of the employee
	public LoginDetails addDetails(LoginDetails details) {
	LoginDetails loginDetails = loginRepository.findByUserName(details.getUserName());
	
	if(loginDetails!=null)
	{
		String errorMessage = String.format("username %s already taken!!", details.getUserName());
		logger.error(errorMessage, UsernameAlreadyExistException.class);
		throw new UsernameAlreadyExistException(errorMessage);
	}
	
	return loginRepository.save(details);
	}	
	
	
	
public void deleteDetailsById(int Id) {

	LoginDetails details = loginRepository.findById(Id);
	
	try
	{
	loginRepository.delete(details);
	}
	catch(DataIntegrityViolationException e)
	{	
		String errorMessage = String.format("employee exist with loginDetails = %s", details.toString());
		logger.error(errorMessage,EmployeeAssociatedException.class);
		throw new EmployeeAssociatedException(errorMessage);
	}
	
}


public LoginDetails validateUser(LoginDetails details) {
	LoginDetails foundDetails = loginRepository.validateUser(details.getUserName(), details.getPassword(), details.getRole());
	
	if(foundDetails == null)
	{	
		String errorMessage = "loginDetails does not exist";
		logger.error(errorMessage,InvalidUserException.class);
		throw new InvalidUserException(errorMessage);
	}
	
	return foundDetails;
}


//public LoginDetails logout(LoginDetails details) {
//	return details;
//}

}