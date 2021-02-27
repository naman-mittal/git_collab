package com.cap.springweb.controllers;

import java.util.List;

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

import com.cap.springweb.entities.Workout;
import com.cap.springweb.services.WorkoutService;

@RestController
@RequestMapping("/api/v1/workouts")
public class WorkoutController {

	@Autowired
	WorkoutService workoutService;
	
	@PostMapping("")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void addWorkout(@RequestBody Workout workout)
	{
		workoutService.addWorkout(workout);
			
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Workout findWorkout(@PathVariable("id") int id)
	{
		return workoutService.findWorkout(id);
	}
	
	@GetMapping("")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Workout> findAllWorkouts()
	{
		
		
		return workoutService.findAllWorkouts();
	}
	
	@GetMapping("/category/{name}")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Workout> findAllWorkoutsWithCategory(@PathVariable("name") String categoryName)
	{
		return workoutService.findAllWorkoutsWithCategory(categoryName);
	}
	
	@PutMapping("")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void updateWorkout(@RequestBody Workout workout)
	{
		workoutService.updateWorkout(workout);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteWorkout(@PathVariable("id") int id)
	{
		workoutService.deleteWorkout(id);
	}
}
