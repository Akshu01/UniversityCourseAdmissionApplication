package com.cg.mts.entities;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ab")
public class AdmissionCommiteeMember implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "adminId")
	private String adminId;
	@Column(name = "aadminName")
	private String adminName;
	@Column(name = "adminContact")
	private String adminContact;
	
	public AdmissionCommiteeMember() {
//		No implementation required;
	}

	public AdmissionCommiteeMember(String adminId, String adminName, String adminContact) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminContact = adminContact;
	}

	public String getadminId() {
		return adminId;
	}

	public void setadminId(String adminId) {
		this.adminId = adminId;
	}

	public String getadminName() {
		return adminName;
	}

	public void setadminName(String adminName) {
		this.adminName = adminName;
	}

	public String getadminContact() {
		return adminContact;
	}

	public void setadminContact(String adminContact) {
		this.adminContact = adminContact;
	}
	
	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return String.format("Contact [adminId=%s, adminName=%s, adminContact=%s]", adminId, adminName,
				 adminContact);
	}

}
