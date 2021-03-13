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
import com.cap.exs.services.ExpenseClaimService;
import com.cap.exs.services.ProjectService;


@SpringBootTest
@RunWith(SpringRunner.class)
class TestProjectService {
	
	@Autowired
	ProjectService projectService;
	
	@Autowired
	IProjectRepository projectRepository;
	
	@Autowired
	ExpenseClaimService expenseClaimService;
	
	
    //@Test
	void testGetAllProject(){
		assertEquals(2, projectService.getAllProject().size());	
	}
	
	
	//@Test
	void testAddProject() {
		Project project = new Project();
		project.setProjectDescription("Java");
		
		LocalDate startDate = LocalDate.of(2021, 03, 01);
		project.setStartDate(startDate);
		
		LocalDate endDate = LocalDate.of(2021, 06, 01);
		project.setEndDate(endDate);
		
		
		assertNotNull(projectService.addProject(project));	
	}
	
	
	//@Test
	void testUpdateProject() {
		
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
	void testDeleteProjectById() {
		projectService.deleteProjectById(1);
		assertEquals(1, projectRepository.count());
	}
	
	
	//@Test
	void testGetAllProjectCodes(){
		List<Integer> allProjectCodes = projectService.getAllProjectCodes();
		assertEquals(2, allProjectCodes.size());	
	}
	
	
	//@Test
	void testFindByCode() {
		Project project = projectService.findByCode(1);
		assertNotNull(project);
	}

}
