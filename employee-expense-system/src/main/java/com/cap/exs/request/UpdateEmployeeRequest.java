package com.cap.exs.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class UpdateEmployeeRequest {

	@NotNull
	@Positive
	private int id;
	
	@NotNull
	@Pattern(regexp = "[a-z A-Z]*",message = "Invalid")
	@Size(min = 4,max = 20)
	private String designation;
	
	@NotNull
	@Pattern(regexp = "[a-z A-Z]*",message = "Invalid")
	@Size(min = 4,max = 20)
	private String domain;
	
	@NotNull
	@Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}",message = "Invalid")
	private String pan;
	
	public UpdateEmployeeRequest(int id,String designation, String domain, String pan) {
		super();
		this.id = id;
		this.designation = designation;
		this.domain = domain;
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
