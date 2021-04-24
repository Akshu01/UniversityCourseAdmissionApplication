package com.cg.mts.service;

import java.util.List;

import com.cg.mts.entities.AdmissionCommiteeMember;
import com.cg.mts.exception.AdmissionCommiteeMemberNotFoundException;

public interface IAdmissionCommiteeMemberService {
	AdmissionCommiteeMember add(AdmissionCommiteeMember AdmissionCommiteeMember) throws AdmissionCommiteeMemberNotFoundException;
	AdmissionCommiteeMember save(AdmissionCommiteeMember AdmissionCommiteeMember) throws AdmissionCommiteeMemberNotFoundException;
	boolean	deleteById(String adminid) throws AdmissionCommiteeMemberNotFoundException;
	AdmissionCommiteeMember getById(String adminid) throws AdmissionCommiteeMemberNotFoundException;
	List<AdmissionCommiteeMember> getAll() throws AdmissionCommiteeMemberNotFoundException;
	
	
}
