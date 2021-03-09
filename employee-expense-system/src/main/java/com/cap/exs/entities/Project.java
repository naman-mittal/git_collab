package com.cap.exs.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int projectCode;
	private String projectDescription;
	private LocalDate startDate;
	private LocalDate endDate;
	
	public Project() {}
	
	public Project(int projectCode, String projectDescription, LocalDate startDate, LocalDate endDate) {
		super();
		this.projectCode = projectCode;
		this.projectDescription = projectDescription;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public int getProjectCode() {
		return projectCode;
	}
	
	public void setProjectCode(int projectCode) {
		this.projectCode = projectCode;
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
		this.endDate = endDate;
	}

	
	@Override
	public String toString() {
		return "Project [projectCode=" + projectCode + ", projectDescription=" + projectDescription + ", startDate="
				+ startDate + ", endDate=" + endDate + "]";
	}
	
	
}
