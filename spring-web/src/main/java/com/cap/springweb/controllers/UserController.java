package com.cap.springweb.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cap.springweb.entities.User;
import com.cap.springweb.repos.UserRepository;

@RestController
public class UserController {

	@Autowired
	UserRepository userRepository;
	
	
	@PostMapping("/users")
	public ResponseEntity<Void> addUser(@RequestBody User u)
	{
		ResponseEntity<Void> re = null;
		ResponseEntity<User> user = findUserByName(u.getUsername());
		
		
		if(user.getStatusCode()==HttpStatus.NOT_FOUND)
		{
			userRepository.save(u);
			
			re = new ResponseEntity<>(HttpStatus.CREATED);	
		}
		else
			re = new ResponseEntity<>(HttpStatus.CONFLICT);
		
		return re;
		
		
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<User> findUser(@PathVariable("id")int id)
	{
		ResponseEntity<User> re = null;
		
		Optional<User> user = userRepository.findById(id);
		if(user.isPresent()) {
			
			re = new ResponseEntity<>(user.get(), HttpStatus.OK);
			
		}
		else
			re = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return re;
	}
	
	@GetMapping("/user/{username}")
	public ResponseEntity<User> findUserByName(@PathVariable("username")String username)
	{
		ResponseEntity<User> re = null;
		User user = userRepository.findByUsername(username);
		
		if(user!=null)
		{	
			re = new ResponseEntity<>(user,HttpStatus.FOUND);	
		}
		else
			re = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return re;
	}
	
	@GetMapping("/login/{username}/{password}")
	public ResponseEntity<User> findUserByNameAndPassword(@PathVariable("username")String username,@PathVariable("password")String password)
	{
		ResponseEntity<User> re = null;
		User user = userRepository.findByUsernameAndPassword(username, password);
		
		if(user!=null)
		{	
			re = new ResponseEntity<>(user,HttpStatus.FOUND);	
		}
		else
			re = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return re;
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> findAllUsers()
	{
		List<User> users = userRepository.findAll(); 
		ResponseEntity<List<User>> re = null;
		
		if(users.isEmpty())
			re = new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
		else
			re = new ResponseEntity<List<User>>(users,HttpStatus.OK);
		
		return re;
		
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable("id")int id)
	{
		Optional<User> user = userRepository.findById(id);
		ResponseEntity<Void> re = null;
		if(user.isPresent())
		{
			userRepository.delete(user.get());
			re = new ResponseEntity<Void>(HttpStatus.OK);
		}
		else
			re = new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			
			
		return re;
		
	}
}
