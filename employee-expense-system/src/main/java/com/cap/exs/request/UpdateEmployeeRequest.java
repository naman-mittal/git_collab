package com.cap.exs.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.cap.exs.validators.Past;

public class UpdateEmployeeRequest {

	@NotNull
	@Positive
	private int id;
	
	@NotNull
	@Positive
	private int loginId;
	
	public int getLoginId() {
		return loginId;
	}
	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}
	@NotNull
	@Size(min = 4,max = 20)
	@Pattern(regexp = "[a-zA-Z]+ [a-zA-Z]+",message = "Name should be in format : [FirstName LastName]")
	private String name;
	
	@NotNull(message = "Employee email cannot be null")
	@Pattern(regexp = "[A-Za-z0-9]+@[A-Za-z0-9.-]+[.][A-Za-z]{2,4}", message = "Invalid email address")
	private String email;
	
//	@NotNull
//	@Size(min = 4,max = 20)
//	private String username;		
//
//	@NotNull
//	@Size(min = 8,max = 20)
//	private String password;
//	
	
	@Pattern(regexp = "[a-z A-Z]*",message = "Invalid")
	@Size(min = 4,max = 20)
	private String designation;
//	
	
	@Pattern(regexp = "[a-z A-Z]*",message = "Invalid")
	@Size(min = 4,max = 20)
	private String domain;
//	
	
	@Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}",message = "Invalid")
	private String pan;
	
	
	@Pattern(regexp = "^(0[1-9]|1[012])[-/.](0[1-9]|[12][0-9]|3[01])[-/.](19|20)\\d\\d$", message = "Invalid date of joining")
	@Past
	private String doj;
//	
//	
	@Pattern(regexp = "[a-z A-Z]*",message = "Invalid")
	@Size(min = 4,max = 20)
	private String role;	
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Pattern(regexp = "^(0[1-9]|1[012])/(0[1-9]|[12][0-9]|3[01])/(19|20)\\d\\d$", message = "Invalid date of birth")
	@Past
	private String dob;
//	
	@Pattern(regexp = "[0-9]*",message = "Invalid")
	@Size(min = 4,message="should be greater than equal to 1000")
	private String salary;
	 
	public String getDoj() {
		return doj;
	}
	public void setDoj(String doj) {
		this.doj = doj;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getEmail() {
		return email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
//	public String getUsername() {
//		return username;
//	}
//	public void setUsername(String username) {
//		this.username = username;
//	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public UpdateEmployeeRequest(int id,String pan) {
		super();
		this.id = id;
//		this.designation = designation;
//		this.domain = domain;
		this.pan = pan;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public UpdateEmployeeRequest() {
		super();
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	
	
}
