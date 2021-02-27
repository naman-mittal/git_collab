package com.cap.springweb.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.cap.springweb.entities.User;
import com.cap.springweb.entities.Workout;
import com.cap.springweb.entities.WorkoutActive;
import com.cap.springweb.exceptions.WorkoutActiveNotFoundException;
import com.cap.springweb.repos.WorkoutActiveRepository;

@Service
public class WorkoutActiveService {

	@Autowired
	WorkoutActiveRepository workoutActiveRepository;
	
	@Autowired
	WorkoutService workoutService;
	
	@Autowired
	UserService userService;
	
	public void addWorkoutActive(@RequestBody WorkoutActive workoutActive)
	{
		Workout workout = workoutService.findWorkout(workoutActive.getWorkout().getId());
		
		User user = userService.findUser(workoutActive.getUser().getId());
		
		workoutActive.setWorkout(workout);
		workoutActive.setUser(user);
		
		workoutActiveRepository.save(workoutActive);
	}
	
	public WorkoutActive findWorkoutActive(int id)
	{
		Optional<WorkoutActive> workoutActive = workoutActiveRepository.findById(id);
		
		if(!workoutActive.isPresent())
			throw new WorkoutActiveNotFoundException("No workoutActive found with id "+id);
		
		return workoutActive.get();
	}
	
	public List<WorkoutActive> findAllWorkoutActives()
	{
		List<WorkoutActive> workoutActives = workoutActiveRepository.findAll();
		
		if(workoutActives.isEmpty())
			throw new WorkoutActiveNotFoundException("No workoutActives found...");
		
		return workoutActives;
	}
	
	public void startWorkout(WorkoutActive workoutActive)
	{
		WorkoutActive foundWorkoutActive = findWorkoutActive(workoutActive.getId());
		
		foundWorkoutActive.setStartTime(LocalDateTime.now());
		
	}
	
	public void endWorkout(WorkoutActive workoutActive)
	{
		WorkoutActive foundWorkoutActive = findWorkoutActive(workoutActive.getId());
		
		foundWorkoutActive.setEndTime(LocalDateTime.now());
		
	}
	
	public void deleteWorkoutActive(int id)
	{
		WorkoutActive workoutActive = findWorkoutActive(id);
		
		if(workoutActive==null)
			throw new WorkoutActiveNotFoundException("No workoutActive found with id " + id);
		
		
		workoutActiveRepository.delete(workoutActive);
	}
	
}
