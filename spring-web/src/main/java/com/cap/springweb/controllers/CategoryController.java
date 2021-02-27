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

import com.cap.springweb.entities.Category;
import com.cap.springweb.services.CategoryService;

@RestController
@RequestMapping("/api/v1")
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	
	@PostMapping("/categories")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void addCategory(@RequestBody Category category)
	{
		categoryService.addCategory(category);
	}
	
	@GetMapping("/category/{name}")
	@ResponseStatus(code = HttpStatus.OK)
	public Category findCategoryByName(@PathVariable("name") String categoryName){
		
		return categoryService.findCategoryByName(categoryName);
	}
	
	@GetMapping("/categories/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Category findCategory(@PathVariable("id") int id)
	{
		return categoryService.findCategory(id);
	}
	
	@GetMapping("/categories")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Category> findAllCategories()
	{
		return categoryService.findAllCategories();
	}
	
	@PutMapping("/categories")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@Transactional
	public void updateCategory(@RequestBody Category category)
	{
		categoryService.updateCategory(category);
	}
	
	@DeleteMapping("/categories/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteCategory(@PathVariable("id") int id)
	{
		categoryService.deleteCategory(id);
	}
}
