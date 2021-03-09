package com.cap.exs.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int empId;
	
	@NotNull
	private String empName;
	private String empPAN;
	private String empDesignation;
	private String empDomain;
	
	@Pattern(regexp = "^(0[1-9]|1[012])[-/.](0[1-9]|[12][0-9]|3[01])[-/.](19|20)\\d\\d$", message = "Invalid date of joining.")
	private String empDOJ;
	
	@Pattern(regexp = "^(0[1-9]|1[012])[-/.](0[1-9]|[12][0-9]|3[01])[-/.](19|20)\\d\\d$", message = "Invalid date of birth.")
	private String empDOB;
	
	
	private String empSalary;
	 
	
	@Pattern(regexp = "[A-Za-z0-9]+@[A-Za-z0-9.-]+[.][A-Za-z]{2,4}", message = "Invalid email address.")
	private String empEmailId;
	
	@OneToOne(cascade = CascadeType.ALL)
	private LoginDetails loginDetails;

	
	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public Employee() {
		super();
	}

	public Employee(String empName, String empPAN, String empDesignation, String empDomain, String empDOJ,
			String empDOB, String empSalary, String empEmailId, LoginDetails loginDetails) {
		super();
		this.empName = empName;
		this.empPAN = empPAN;
		this.empDesignation = empDesignation;
		this.empDomain = empDomain;
		this.empDOJ = empDOJ;
		this.empDOB = empDOB;
		this.empSalary = empSalary;
		this.empEmailId = empEmailId;
		this.loginDetails = loginDetails;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpPAN() {
		return empPAN;
	}

	public void setEmpPAN(String empPAN) {
		this.empPAN = empPAN;
	}

	public String getEmpDesignation() {
		return empDesignation;
	}

	public void setEmpDesignation(String empDesignation) {
		this.empDesignation = empDesignation;
	}

	public String getEmpDomain() {
		return empDomain;
	}

	public void setEmpDomain(String empDomain) {
		this.empDomain = empDomain;
	}

	public String getEmpDOJ() {
		return empDOJ;
	}

	public void setEmpDOJ(String empDOJ) {
		this.empDOJ = empDOJ;
	}

	public String getEmpDOB() {
		return empDOB;
	}

	public void setEmpDOB(String empDOB) {
		this.empDOB = empDOB;
	}

	public String getEmpSalary() {
		return empSalary;
	}

	public void setEmpSalary(String empSalary) {
		this.empSalary = empSalary;
	}

	public String getEmpEmailId() {
		return empEmailId;
	}

	public void setEmpEmailId(String empEmailId) {
		this.empEmailId = empEmailId;
	}

	public LoginDetails getLoginDetails() {
		return loginDetails;
	}

	public void setLoginDetails(LoginDetails loginDetails) {
		this.loginDetails = loginDetails;
	}

	public int getEmpId() {
		return empId;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", empPAN=" + empPAN + ", empDesignation="
				+ empDesignation + ", empDomain=" + empDomain + ", empDOJ=" + empDOJ + ", empDOB=" + empDOB
				+ ", empSalary=" + empSalary + ", empEmailId=" + empEmailId + ", loginDetails=" + loginDetails + "]";
	}
	
	
	
	
}
