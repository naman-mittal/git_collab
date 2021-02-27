package com.cap.springweb.controllers;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cap.springweb.entities.WorkoutActive;
import com.cap.springweb.services.WorkoutActiveService;

@RestController
@RequestMapping("/api/v1/workoutActives")
public class WorkoutActiveController {

	@Autowired
	WorkoutActiveService workoutActiveService;
	
	@PostMapping("")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void addWorkoutActive(@RequestBody WorkoutActive wa)
	{
		workoutActiveService.addWorkoutActive(wa);
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public WorkoutActive findWorkoutActive(@PathVariable("id") int id)
	{
		return workoutActiveService.findWorkoutActive(id);
	}
	
	@GetMapping("")
	@ResponseStatus(code = HttpStatus.OK)
	public List<WorkoutActive> findAllWorkoutActives()
	{
		return workoutActiveService.findAllWorkoutActives();
	}
	
	
	@PutMapping("/S")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@Transactional
	public void startWorkout(@RequestBody WorkoutActive workoutActive)
	{
		workoutActiveService.startWorkout(workoutActive);
	}
	
	@PutMapping("/E")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@Transactional
	public void endWorkout(@RequestBody WorkoutActive workoutActive)
	{
		workoutActiveService.endWorkout(workoutActive);
		
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteWorkoutActive(@PathVariable("id") int id)
	{
		workoutActiveService.deleteWorkoutActive(id);
	}
}
