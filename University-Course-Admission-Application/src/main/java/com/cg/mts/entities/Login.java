package com.cg.mts.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "login")
public class Login implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "name", length = 20)
	private String userName;
	
	@Id
	@Column(name = "pass", length = 20)
	private String password;
	
	@OneToOne
	@JoinColumn(name = "ustaffvalidator")
	private UniversityStaffMember member;
	
	public Login() {
		/* no implementation */
	}
	
	public Login(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UniversityStaffMember getMember() {
		return member;
	}

	public void setMember(UniversityStaffMember member) {
		this.member = member;
	}

	@Override
	public String toString() {
		return String.format("Login [userName=%s, password=%s]", userName, password);
	}
	
}
