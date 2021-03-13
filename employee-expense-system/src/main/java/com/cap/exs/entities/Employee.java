package com.cap.exs.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.cap.exs.validators.Past;

@Entity
@Table(uniqueConstraints = { 
	@UniqueConstraint(columnNames = "empEmailId"), 
	@UniqueConstraint(columnNames = "empPAN") 
})
public class Employee {

	@Id
	@SequenceGenerator(name="employee_sequence",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "employee_sequence")
	private int empId;
	
	@NotNull
	@Size(min = 4,max = 20)
	@Pattern(regexp = "[a-zA-Z]+ [a-zA-Z]+",message = "Name should be in format : [FirstName LastName]")
	private String empName;
	
	@Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}",message = "Invalid")
	private String empPAN;
	
	@Pattern(regexp = "[a-z A-Z]*",message = "Invalid")
	@Size(min = 4,max = 20)
	private String empDesignation;
	
	@Pattern(regexp = "[a-z A-Z]*",message = "Invalid")
	@Size(min = 4,max = 20)
	private String empDomain;
	
	
	@NotNull(message = "Employee date of joining cannot be null")
	@Pattern(regexp = "^(0[1-9]|1[012])/(0[1-9]|[12][0-9]|3[01])/(19|20)\\d\\d$", message = "Invalid date of joining")
	@Past
	private String empDOJ;
	
	
	@NotNull(message = "Employee date of birth cannot be null")
	@Pattern(regexp = "^(0[1-9]|1[012])/(0[1-9]|[12][0-9]|3[01])/(19|20)\\d\\d$", message = "Invalid date of birth")
	@Past
	private String empDOB;
	
	@NotNull
	@Pattern(regexp = "[0-9]*",message = "Invalid")
	@Size(min = 4,message="should be greater than equal to 1000")
	private String empSalary;
	 
	@NotNull(message = "Employee email cannot be null")
	@Pattern(regexp = "[A-Za-z0-9]+@[A-Za-z0-9.-]+[.][A-Za-z]{2,4}", message = "Invalid email address")
	private String empEmailId;
	
	@NotNull(message = "LoginDetails cannot be null")
	@OneToOne(cascade = CascadeType.PERSIST,orphanRemoval = true)
	private LoginDetails loginDetails;

	
	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public Employee() {
		super();
	}

	public Employee(String empName, String empPAN, String empDOJ,
			String empDOB, String empSalary, String empEmailId, LoginDetails loginDetails) {
		super();
		this.empName = empName;
		this.empPAN = empPAN;
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
