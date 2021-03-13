package com.cap.exs.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cap.exs.entities.ERole;
import com.cap.exs.entities.Role;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Integer> {

	
	 Optional<Role> findByName(ERole name);
}
