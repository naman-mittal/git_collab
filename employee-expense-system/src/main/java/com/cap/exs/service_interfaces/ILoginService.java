package com.cap.exs.service_interfaces;

import com.cap.exs.entities.LoginDetails;

public interface ILoginService {
	
	public LoginDetails addDetails(LoginDetails details);
	
	public void deleteDetailsById(int id);
	
	public LoginDetails validateUser(LoginDetails details);
	
//	public LoginDetails logout(LoginDetails details);
	
}
