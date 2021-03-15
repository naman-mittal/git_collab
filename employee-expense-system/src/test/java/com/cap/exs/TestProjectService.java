package com.cap.exs;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cap.exs.entities.Project;
import com.cap.exs.repos.IProjectRepository;
import com.cap.exs.services.ProjectService;


@SpringBootTest
@RunWith(SpringRunner.class)
public class TestProjectService {
	
	@Autowired
	ProjectService projectService;
	
	@Autowired
	IProjectRepository projectRepository;
	
    @Test
	public void testGetAllProject(){
		assertEquals(1, projectService.getAllProject().size());	
	}
	
	
	//@Test
	public void testAddProject() {
		Project project = new Project();
		project.setProjectDescription("Java");
		
		LocalDate startDate = LocalDate.of(2021, 03, 01);
		project.setStartDate(startDate);
		
		LocalDate endDate = LocalDate.of(2021, 06, 01);
		project.setEndDate(endDate);
		
		
		assertNotNull(projectService.addProject(project));	
	}
	
	
	//@Test
	public void testUpdateProject() {
		
		Project project = new Project();
		project.setProjectCode(1);
		project.setProjectDescription("Java");
		
		LocalDate startDate = LocalDate.of(2021, 03, 01);
		project.setStartDate(startDate);
		
		LocalDate endDate = LocalDate.of(2021, 06, 01);
		project.setEndDate(endDate);
		
		assertNotNull(projectService.updateProject(project));
		
	}
	
	
	//@Test
	public void testDeleteProjectById() {
		projectService.deleteProjectById(1);
		assertEquals(1, projectRepository.count());
	}
	
	
	//@Test
	public void testGetAllProjectCodes(){
		List<Integer> allProjectCodes = projectService.getAllProjectCodes();
		assertEquals(2, allProjectCodes.size());	
	}
	
	
	//@Test
	public void testFindByCode() {
		Project project = projectService.findByCode(1);
		assertNotNull(project);
	}

}
