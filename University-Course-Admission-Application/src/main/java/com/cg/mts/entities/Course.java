package com.cg.mts.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/*@author Akshat Kumar*/

@Entity
@Table(name = "courses")
public class Course implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "courseId")
	private int courseId;

	@Column(name = "courseName", length = 30)
	private String courseName;

	@Column(name = "courseDuration", length = 20)
	private String courseDuration;

	@Column(name = "startDate")
	private LocalDate courseStartDate;

	@Column(name = "endDate")
	private LocalDate courseEndDate;

	@Column(name = "courseFees", length = 10)
	private String courseFees;

	@ManyToOne
	@JoinColumn(name = "staffId")
	private UniversityStaffMember universityStaffMember;
	
	@OneToMany(mappedBy="course")
	private List<Admission> admissionCourse = new ArrayList<>();

	public Course() {
		/* no implementation */
	}

	public Course(int courseId, String courseName, String courseDuration, LocalDate courseStartDate,
			LocalDate courseEndDate, String courseFees) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.courseDuration = courseDuration;
		this.courseStartDate = courseStartDate;
		this.courseEndDate = courseEndDate;
		this.courseFees = courseFees;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getCourseId() {
		return courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseDuration() {
		return courseDuration;
	}

	public void setCourseDuration(String courseDuration) {
		this.courseDuration = courseDuration;
	}

	public LocalDate getCourseStartDate() {
		return courseStartDate;
	}

	public void setCourseStartDate(LocalDate courseStartDate) {
		this.courseStartDate = courseStartDate;
	}

	public LocalDate getCourseEndDate() {
		return courseEndDate;
	}

	public void setCourseEndDate(LocalDate courseEndDate) {
		this.courseEndDate = courseEndDate;
	}

	public String getCourseFees() {
		return courseFees;
	}

	public void setCourseFees(String courseFees) {
		this.courseFees = courseFees;
	}

	public UniversityStaffMember getUniversityStaffMember() {
		return universityStaffMember;
	}

	public void setUniversityStaffMember(UniversityStaffMember universityStaffMember) {
		this.universityStaffMember = universityStaffMember;
	}

	public List<Admission> getAdmissionCourse() {
		return admissionCourse;
	}

	public void setAdmissionCourse(List<Admission> admissionCourse) {
		this.admissionCourse = admissionCourse;
	}

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseName=" + courseName + ", courseDuration=" + courseDuration
				+ ", courseStartDate=" + courseStartDate + ", courseEndDate=" + courseEndDate + ", courseFees="
				+ courseFees + "]";
	}

}