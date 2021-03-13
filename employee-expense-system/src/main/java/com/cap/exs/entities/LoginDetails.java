package com.cap.exs.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Entity
@Table(uniqueConstraints = { 
		@UniqueConstraint(columnNames = "userName") 
	})
public class LoginDetails {
	
	@Id
	@SequenceGenerator(name="loginDetails_sequence",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "loginDetails_sequence")
	private int id;		 //employee id
	
	@NotNull
	@Size(min = 4,max = 20)
	private String userName;		//employee username
	
	@NotNull
	private String password;		//employee password
	
	@NotNull
	@Size(min = 4,max = 20)
	private String role;			//employee role

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	
	//default constructor
	public LoginDetails() {
}

	public LoginDetails(String userName, String password, String role) {
		super();
		this.userName = userName;
		this.password = password;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	
	
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "LoginDetails [id=" + id + ", userName=" + userName + ", password=" + password + ", role=" + role + "]";
	}


	
	

}