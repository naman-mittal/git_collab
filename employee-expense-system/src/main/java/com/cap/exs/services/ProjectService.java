package com.cap.exs.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cap.exs.entities.Expense;
import com.cap.exs.entities.Project;
import com.cap.exs.repos.IProjectRepository;
import com.cap.exs.service_interfaces.IProjectService;
import com.cap.exs.exceptions.ExpenseNotFoundException;
import com.cap.exs.exceptions.ProjectNotFoundException;


@Service 
public class ProjectService implements IProjectService{
	
	@Autowired
	IProjectRepository projectRepository;
	
	public List<Project> getAllProject(){
		List<Project> projects = new ArrayList<Project>();
		projects = projectRepository.findAll();
		
		if(projects.isEmpty()) {
			throw new ProjectNotFoundException("No projects found...");
		}
		
		return projects;
	}
	
	
	public Project addProject(Project project) {
		return projectRepository.save(project);
	}
	
//	public List<Project> getAllProjectCode(int Id){}
	
	public Project updateProject(Project project) {
		return projectRepository.save(project);
	}
	
	
	public Project deleteProjectById(int id) {
		Project project = projectRepository.findById(id).get();
		if(project == null) {
			throw new ProjectNotFoundException("No such project exists...");
		}
		projectRepository.delete(project);
		return project;	
	}
	
	
//	public void deleteAllProject(){}
	
	
	public List<Integer> getAllProjectCodes(){
		List<Integer> projectCodes = new ArrayList<Integer>();
		projectCodes = projectRepository.getAllProjectCodes();
		
		if(projectCodes.isEmpty()) {
			throw new ProjectNotFoundException("No project code found...");
		}
		
		return projectCodes;
	}
	
	
	public Project findByCode(int projectCode) {
		Optional<Project> project = projectRepository.findById(projectCode);
		if(!project.isPresent())
		{
			throw new ProjectNotFoundException("No project found with projectCode: " + projectCode);
		}
		return project.get();		
	}
}
