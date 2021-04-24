package com.cg.mts.entities;

import java.io.Serializable;
import java.time.LocalDate;
//import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="admissions")
public class Admission implements Serializable {

	private static final long serialVersionUID = 1L;

/*	private int admissionId;
	private int courseId;
	private int applicantId;
	private LocalDate admissionDate;
	private AdmissionStatus status; */
	
	@Id
	@Column(name="Admission_Id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long admissionId;
	
	@ManyToOne
	@JoinColumn(name="admissionCourse")
	private Course course;
	
	@Column(name="Admission_Date")
	private LocalDate admissionDate;
	
	@Column(name="status")
	private AdmissionStatus status;
	
	@OneToOne
	@JoinColumn(name="Applicant_Id")
	private Applicant applicant;
	
	public Admission() {
		/* no implementation*/
	}

	public Admission(Course course, LocalDate admissionDate, AdmissionStatus status, Applicant applicant) {
		super();
		this.course = course;
		this.admissionDate = admissionDate;
		this.status = status;
		this.applicant = applicant;
	}

	public Long getAdmissionId() {
		return admissionId;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public LocalDate getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(LocalDate admissionDate) {
		this.admissionDate = admissionDate;
	}
	
	public AdmissionStatus getStatus() {
		return status;
	}

	public void setStatus(AdmissionStatus status) {
		this.status = status;
	}

	public Applicant getApplicant() {
		return applicant;
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}

	@Override
	public String toString() {
		return String.format("Admission [admissionId=%s, course=%s, admissionDate=%s, status=%s, applicant=%s]",
				admissionId, course.getCourseId(), admissionDate, status);
	}

	
}

