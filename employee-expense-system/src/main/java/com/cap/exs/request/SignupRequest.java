package com.cap.exs.request;

import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.cap.exs.validators.Past;

public class SignupRequest {

	@NotNull
	@Size(min = 4,max = 20)
	@Pattern(regexp = "[a-zA-Z]+ [a-zA-Z]+",message = "Name should be in format : [FirstName LastName]")
	private String name;
	
	@Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}",message = "Invalid")
	private String pan;
	
	@Pattern(regexp = "[a-z A-Z]*",message = "Invalid")
	@Size(min = 4,max = 20)
	private String designation;
	
	@Pattern(regexp = "[a-z A-Z]*",message = "Invalid")
	@Size(min = 4,max = 20)
	private String domain;
	
	
	@NotNull(message = "Employee date of joining cannot be null")
	@Pattern(regexp = "^(0[1-9]|1[012])[-/.](0[1-9]|[12][0-9]|3[01])[-/.](19|20)\\d\\d$", message = "Invalid date of joining")
	@Past
	private String doj;
	
	
	@NotNull(message = "Employee date of birth cannot be null")
	@Pattern(regexp = "^(0[1-9]|1[012])/(0[1-9]|[12][0-9]|3[01])/(19|20)\\d\\d$", message = "Invalid date of birth")
	@Past
	private String dob;
	
	@NotNull
	@Pattern(regexp = "[0-9]*",message = "Invalid")
	@Size(min = 4,message="should be greater than equal to 1000")
	private String salary;
	 
	@NotNull(message = "Employee email cannot be null")
	@Pattern(regexp = "[A-Za-z0-9]+@[A-Za-z0-9.-]+[.][A-Za-z]{2,4}", message = "Invalid email address")
	private String email;
	
	@NotNull
	@Size(min = 4,max = 20)
	private String username;		

	@NotNull
	@Size(min = 8,max = 20)
	private String password;		
	
	@NotNull
	@Size(min = 4,max = 20)
	private String role;

	 private Set<String> roles;
	
	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public SignupRequest() {
		super();
	}

	public SignupRequest(String name, String doj, String dob, String domain, String designation, String pan,
			String salary, String email, String username, String password, String role) {
		super();
		this.name = name;
		this.doj = doj;
		this.dob = dob;
		this.domain = domain;
		this.designation = designation;
		this.pan = pan;
		this.salary = salary;
		this.email = email;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
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

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
	
	
	
}
