package com.cap.springweb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cap.springweb.entities.Category;
import com.cap.springweb.exceptions.CategoryNotFoundException;
import com.cap.springweb.exceptions.DuplicateCategoryException;
import com.cap.springweb.repos.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;

	public void addCategory(Category category)
	{
		Category foundCategory = categoryRepository.findByName(category.getName());
		
		if(foundCategory==null)
		{
			categoryRepository.save(category);
		}
		else
			throw new DuplicateCategoryException("category "+ category.getName() + " already exist...");
			
	}
	
	public Category findCategoryByName(String categoryName){
		
		Category category = categoryRepository.findByName(categoryName);
		
		if(category == null)
			throw new CategoryNotFoundException("No category found with name " + categoryName);
		
		return category;
	}
	
	public Category findCategory(int id)
	{
		Optional<Category> category = categoryRepository.findById(id);
		
		if(!category.isPresent())
			throw new CategoryNotFoundException("No category found with id " + id);
		
		return category.get();
	}
	
	public List<Category> findAllCategories()
	{
		List<Category> categories = categoryRepository.findAll();
		
		if(categories.isEmpty())
			throw new CategoryNotFoundException("No categories found...");
		
		return categories;
	}
	
	public void updateCategory(Category category)
	{
		Category foundCategory = findCategory(category.getId());
		
		if(foundCategory==null)
			throw new CategoryNotFoundException("No category found with id " + category.getId());
		
		foundCategory.setName(category.getName());
	}
	
	public void deleteCategory(int id)
	{
		Category category = findCategory(id);
		
		if(category==null)
			throw new CategoryNotFoundException("No category found with id " + id);
		
		
		categoryRepository.delete(category);
	}
}

