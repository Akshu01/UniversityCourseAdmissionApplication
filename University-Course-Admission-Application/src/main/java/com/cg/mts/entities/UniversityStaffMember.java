package com.cg.mts.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ustaffmembers")
public class UniversityStaffMember implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "staffId")
	private int staffId;

	@Column(name = "password", length = 20)
	private String password;

	@Column(name = "role", length = 30)
	private String role;

	@OneToMany(mappedBy = "universityStaffMember")
	private List<Course> course = new ArrayList<>();

	@OneToOne(mappedBy = "member")
	private Login ustaffvalidator;

	public UniversityStaffMember() {
		/* no implementation */
	}

	public UniversityStaffMember(int staffId, String password, String role) {
		super();
		this.staffId = staffId;
		this.password = password;
		this.role = role;
	}

	public int getStaffId() {
		return staffId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Course> getCourse() {
		return course;
	}

	public void setCourse(List<Course> course) {
		this.course = course;
	}

	public Login getUstaffvalidator() {
		return ustaffvalidator;
	}

	public void setUstaffvalidator(Login ustaffvalidator) {
		this.ustaffvalidator = ustaffvalidator;
	}

	@Override
	public String toString() {
		return "UniversityStaffMember [staffId=" + staffId + ", password=" + password + ", role=" + role + "]";
	}

}
