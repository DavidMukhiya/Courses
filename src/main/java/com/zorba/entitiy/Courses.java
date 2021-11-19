package com.zorba.entitiy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Zorba_Courses")
public class Courses {
	@Id
	@Column(name="ID")
	private int id;
	@Column(name="Course_Name")
	private String Course_Name;
	@Column(name="Position")
	private String Position;
	@Column(name="Duration")
	private String duration;
	
	public Courses() {
		
	}
	
	public Courses(int id, String course_Name, String position, String duration) {
		super();
		this.id = id;
		Course_Name = course_Name;
		Position = position;
		this.duration = duration;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCourse_Name() {
		return Course_Name;
	}
	public void setCourse_Name(String course_Name) {
		Course_Name = course_Name;
	}
	public String getPosition() {
		return Position;
	}
	public void setPosition(String position) {
		Position = position;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
}
