package com.cap.exs.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cap.exs.entities.Employee;
import com.cap.exs.entities.LoginDetails;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Integer>{

	
	public Employee findByLoginDetails(LoginDetails loginDetails);
	
	public Employee findByEmpEmailId(String email);
	
	public Employee findByEmpPAN(String pan);
	
	@Query("Select e from Employee e where e.loginDetails.userName=:username")
	public Employee findByUsername(@Param("username") String username);
	
}
