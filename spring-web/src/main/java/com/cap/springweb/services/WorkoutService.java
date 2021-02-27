package com.cap.springweb.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cap.springweb.entities.Category;
import com.cap.springweb.entities.Workout;
import com.cap.springweb.exceptions.CategoryNotFoundException;
import com.cap.springweb.exceptions.WorkoutNotFoundException;
import com.cap.springweb.repos.WorkoutRepository;

@Service
public class WorkoutService {

	@Autowired
	WorkoutRepository workoutRepository;
	
	@Autowired
	CategoryService categoryService;
	
	public void addWorkout(Workout workout)
	{
		try {
			Category category = categoryService.findCategoryByName(workout.getCategory().getName());
			if(category != null) {
				workout.setCategory(category);
			}
		}
		catch(CategoryNotFoundException e) {
			e.printStackTrace();
		}

		workoutRepository.save(workout);
			
	}
	
	public Workout findWorkout(int id)
	{
		Optional<Workout> workout = workoutRepository.findById(id);
		
		if(!workout.isPresent())
			throw new WorkoutNotFoundException("No workout found with id "+id);
		
		return workout.get();
	}
	
	public List<Workout> findAllWorkouts()
	{
		List<Workout> workouts = workoutRepository.findAll();
		
		if(workouts.isEmpty())
			throw new WorkoutNotFoundException("No workouts found...");
		
		return workouts;
	}
	
	public List<Workout> findAllWorkoutsWithCategory(String categoryName)
	{
		Category category = categoryService.findCategoryByName(categoryName);
		
		List<Workout> workouts = workoutRepository.findByCategory(category);
		
		if(workouts.isEmpty())
			throw new CategoryNotFoundException("No workouts found...");
		
		return workouts;
	}
	
	@Transactional
	public void updateWorkout(Workout workout)
	{
		Workout foundWorkout = findWorkout(workout.getId());
		foundWorkout.setTitle(workout.getTitle());
		foundWorkout.setNote(workout.getNote());
		foundWorkout.setCaloriesBurntPerMinute(workout.getCaloriesBurntPerMinute());
	}
	
	
	public void deleteWorkout(int id)
	{
		Workout workout = findWorkout(id);
		
		if(workout==null)
			throw new WorkoutNotFoundException("No workout found with id " + id);
		
		
		workoutRepository.delete(workout);
	}
	
}
