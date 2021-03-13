package com.cap.exs.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cap.exs.entities.LoginDetails;


public interface ILoginRepository extends JpaRepository<LoginDetails,Integer>{

	public LoginDetails findByUserName(String userName);
	
	@Query("Select ld from LoginDetails ld where userName = :userName AND password = :password AND role= :role ")
	LoginDetails validateUser(@Param("userName") String userName , @Param("password") String password , @Param("role") String role);
	
	public LoginDetails findById(int id);
}