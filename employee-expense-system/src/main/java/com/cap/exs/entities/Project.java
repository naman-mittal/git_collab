package com.cap.exs.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.cap.exs.exceptions.InvalidEndDateException;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Project {

	@Id
	@SequenceGenerator(name = "project_sequence",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "project_sequence")
	private int projectCode;
	
	@NotNull
	@Pattern(regexp = "[a-z A-Z]*",message = "Invalid")
	@Size(min = 5 , max = 30)
	private String title;
	
	@NotNull
	@Size(min = 5,max = 50)
	private String projectDescription;
	
	@NotNull
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@JsonFormat(pattern = "MM/dd/yyyy")
	private LocalDate startDate;
	
	@NotNull
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@JsonFormat(pattern = "MM/dd/yyyy")
	private LocalDate endDate;
	
	//Class constructor
	public Project() {}
	
	
	//Constructor using fields
	public Project(String title, String projectDescription, LocalDate startDate, LocalDate endDate) {
		super();
		this.title = title;
		this.projectDescription = projectDescription;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	/* Getters Setters*/
	public int getProjectCode() {
		return projectCode;
	}
	
	public void setProjectCode(int projectCode) {
		this.projectCode = projectCode;
	}
	
	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getProjectDescription() {
		return projectDescription;
	}
	
	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}
	
	
	public LocalDate getStartDate() {
		return startDate;
	}
	
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	
	
	public LocalDate getEndDate() {
		return endDate;
	}
	
	public void setEndDate(LocalDate endDate) {
		if(endDate.isAfter(startDate)) {
			this.endDate = endDate;
			}
		else {
			throw new InvalidEndDateException("End date should be the date after start date");
			}
	}

	
	@Override
	public String toString() {
		return "Project [projectCode=" + projectCode + ", projectDescription=" + projectDescription + ", startDate="
				+ startDate + ", endDate=" + endDate + "]";
	}
	
	
}
