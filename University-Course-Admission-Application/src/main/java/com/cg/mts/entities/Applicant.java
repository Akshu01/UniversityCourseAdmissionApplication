package com.cg.mts.entities;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ab")
public class Applicant implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "applicantId")
	private String applicantId;
	@Column(name = "applicantname")
	private String applicantName;
	@Column(name = "mobileNumber")
	private String mobileNumber;
	@Column(name = "applicantDegree")
	private String applicantDegree;
	@Column(name = "applicantGraduationPercent")
	private float applicantGraduationPercent;

	public Applicant() {
//		No implementation required;
	}

	public Applicant(String applicantId, String applicantName, String mobileNumber, String applicantDegree, Long applicantGraduationPercent) {
		super();
		this.applicantId = applicantId;
		this.applicantName = applicantName;
		this.mobileNumber = mobileNumber;
		this.applicantDegree = applicantDegree;
		this.applicantGraduationPercent = applicantGraduationPercent;
	}

	public String getapplicantId() {
		return applicantId;
	}

	public void setapplicantId(String applicantId) {
		this.applicantId = applicantId;
	}

	public String getapplicantName() {
		return applicantName;
	}

	public void setapplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public String getmobileNumber() {
		return mobileNumber;
	}

	public void setmobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	public String getapplicantDegree() {
		return applicantDegree;
	}
	
	public void setapplicantDegree(String applicantDegree) {
		this.applicantDegree = applicantDegree;
	}


	public float getapplicantGraduationPercent() {
		return applicantGraduationPercent;
	}

	public void setapplicantGraduationPercent(float applicantGratuationPercent) {
		this.applicantGraduationPercent = applicantGratuationPercent;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return String.format("Contact [applicantId=%s, applicantName=%s, mobileNumber=%s, applicantDegree=%s, applicantGraduationPercent=%s]", applicantId, applicantName,
				 mobileNumber, applicantDegree, applicantGraduationPercent);
	}

}
