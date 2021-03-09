package com.cap.exs.service_interfaces;

import java.util.List;

import com.cap.exs.entities.Project; 

public interface IProjectService {

	public List<Project> getAllProject();
	
	public Project addProject(Project project);
	
	public Project updateProject(Project project);
	
	public Project deleteProjectById(int id);
	
	public List<Integer> getAllProjectCodes();
	
	public Project findByCode(int projectCode);
}

