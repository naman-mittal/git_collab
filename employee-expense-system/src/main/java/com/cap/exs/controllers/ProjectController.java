package com.cap.exs.controllers;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cap.exs.entities.Project;
import com.cap.exs.request.AddProjectRequest;
import com.cap.exs.request.UpdateProjectRequest;
import com.cap.exs.services.ProjectService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Validated
@RequestMapping("/api/v1")
@Api(value = "Project", tags = { "ProjectAPI" })
public class ProjectController {
	
	@Autowired
	ProjectService projectService;
	
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER') or hasRole('MANAGER')")
	@ApiOperation(value = "Retrieve all projects", response = List.class)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved projects"),
            @ApiResponse(code = 400, message = "Check your input parameters"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "No projects found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
	@GetMapping("/projects")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Project> getAllProject(){		//We will get all the projects available
		return projectService.getAllProject();
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@ApiOperation(value = "Add a new Project", response = Project.class)
	@ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created project"),
            @ApiResponse(code = 400, message = "Check your input parameters"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
	@PostMapping("/project")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Project addProject(@ApiParam(name="Add Project Request", required = true)@Valid @RequestBody AddProjectRequest request) {		//Add projects
		
		Project project = new Project();
		project.setProjectDescription(request.getDescription());
		project.setStartDate(request.getStartDate());
		project.setEndDate(request.getEndDate());
		
		return projectService.addProject(project);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@ApiOperation(value = "Update the Project", response = Project.class)
	@ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully updated project"),
            @ApiResponse(code = 400, message = "Check your input parameters"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "project not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
	@PutMapping("/project")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public Project updateProject(@ApiParam(name="Update Project Request", required = true)@Valid @RequestBody UpdateProjectRequest request) {	//Update projects
		
		Project project = new Project();
		
		project.setProjectCode(request.getId());
		project.setProjectDescription(request.getDescription());
		project.setStartDate(request.getStartDate());
		project.setEndDate(request.getEndDate());
		
		return projectService.updateProject(project);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@ApiOperation(value = "Delete the Project", response = Project.class)
	@ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully deleted project"),
            @ApiResponse(code = 400, message = "Check your input parameters"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "project not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
	@DeleteMapping("/project/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public Project deleteProjectById(@PathVariable("id")@Positive int id) {		//Delete projects by particular ID
		return projectService.deleteProjectById(id);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@ApiOperation(value = "Delete all Projects", response = Project.class)
	@ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully deleted projects"),
            @ApiResponse(code = 400, message = "Check your input parameters"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "projects not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
	@DeleteMapping("/projects")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteAllProject(){					//Extra method added to delete all projects at once
		projectService.deleteAllProject();
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/projects/projectCode")
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "Get the Projects code", response = List.class)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 400, message = "Check your input parameters"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "projects not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
	public List<Integer> getAllProjectCodes(){		//We will get all project codes(ID)
		return projectService.getAllProjectCodes();		
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/project/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "Find the Project by its code", response = List.class)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 400, message = "Check your input parameters"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "project not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
	public Project findByCode(@PathVariable("id")@Positive int projectCode) {	//We will get the project by ID
		return projectService.findByCode(projectCode);	
	}
}
