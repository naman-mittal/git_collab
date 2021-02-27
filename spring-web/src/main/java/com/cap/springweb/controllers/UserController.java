package com.cap.springweb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cap.springweb.entities.User;
import com.cap.springweb.services.UserService;

@RestController
@RequestMapping("/api/v1")
public class UserController {

	@Autowired
	UserService userService;
	
	
	@PostMapping("/users")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void addUser(@RequestBody User user)
	{
		userService.addUser(user);
	}
	
	@GetMapping("/users/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public User findUser(@PathVariable("id") int id)
	{
		return userService.findUser(id);
	}
	
	@GetMapping("/user/{username}")
	@ResponseStatus(code = HttpStatus.OK)
	public User findUserByName(@PathVariable("username") String username){
		
		return userService.findUserByName(username);
	}
	
	@GetMapping("/login/{username}/{password}")
	@ResponseStatus(code = HttpStatus.OK)
	public User findUserByNameAndPassword(@PathVariable("username")String username,@PathVariable("password")String password)
	{
		return userService.findUserByNameAndPassword(username, password);
	}
	
	@GetMapping("/users")
	@ResponseStatus(code = HttpStatus.OK)
	public List<User> findAllUsers()
	{
		return userService.findAllUsers();
	}
	
	@DeleteMapping("/users/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable("id") int id)
	{
		userService.deleteUser(id);
	}
}
