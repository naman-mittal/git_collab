package com.cap.springweb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cap.springweb.entities.User;
import com.cap.springweb.exceptions.DuplicateUserException;
import com.cap.springweb.exceptions.UserNotFoundException;
import com.cap.springweb.repos.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public void addUser(User user)
	{
		User foundUser = userRepository.findByUsername(user.getUsername());
		
		if(foundUser==null)
		{
			userRepository.save(user);
		}
		else
			throw new DuplicateUserException("user "+ user.getUsername() + " already exist...");
		
		
	}
	
	public User findUser(int id)
	{
		Optional<User> user = userRepository.findById(id);
		
		if(!user.isPresent())
			throw new UserNotFoundException("No category found with id " + id);
		
		return user.get();
	}
	
	public User findUserByName(String username){
		
		User user = userRepository.findByUsername(username);
		
		if(user == null)
			throw new UserNotFoundException("No user found with username " + username);
		
		return user;
	}
	
	public User findUserByNameAndPassword(String username, String password)
	{
		
		User user = userRepository.findByUsernameAndPassword(username, password);
		
		if(user==null)
			throw new UserNotFoundException("No user found with username = " + username + " and password = " + password);
		
		
		return user;
	}
	
	public List<User> findAllUsers()
	{
		List<User> users = userRepository.findAll(); 
		
		
		if(users.isEmpty())
			throw new UserNotFoundException("no users found");
		
		
		return users;
		
	}
	
	public void deleteUser(int id)
	{
		User user = findUser(id);
		
		if(user==null)
			throw new UserNotFoundException("No user found with id " + id);
		
		
		userRepository.delete(user);
	}
	
}
