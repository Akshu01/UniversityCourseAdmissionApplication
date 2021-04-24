package com.cg.mts.repository;


import java.util.List;

import com.cg.mts.entities.AdmissionCommiteeMember;
import com.cg.mts.exception.AdmissionCommiteeMemberNotFoundException;

public interface IAdmissionCommiteeMemberRepository {
	AdmissionCommiteeMember add(AdmissionCommiteeMember AdmissionCommiteeMember) throws AdmissionCommiteeMemberNotFoundException;
	AdmissionCommiteeMember save(AdmissionCommiteeMember AdmissionCommiteeMember) throws AdmissionCommiteeMemberNotFoundException;
	boolean	deleteById(String adminid) throws AdmissionCommiteeMemberNotFoundException;
	AdmissionCommiteeMember getById(String AdmissionCommiteeMember) throws AdmissionCommiteeMemberNotFoundException;
	List<AdmissionCommiteeMember> getAll() throws AdmissionCommiteeMemberNotFoundException;
	
//	String INSERT_AdmissionCommiteeMember= "INSERT INTO contact(aid, aname, adminContact) VALUES(?,?,?)";
//	String UPDATE_AdmissionCommiteeMember="UPDATE contact SET aname = ? adminContact = ?  WHERE aid = ?";
//	String DELETE_AdmissionCommiteeMember="DELETE FROM contact WHERE aid = ?";
//	String GET_AdmissionCommiteeMember_BY_ID="SELECT aid,aname,adminContact FROM contact WHERE aid = ?";
//	String GET_ALL_AdmissionCommiteeMembers="SELECT aid,aname,adminContact FROM AdmissionCommiteeMember";
}
