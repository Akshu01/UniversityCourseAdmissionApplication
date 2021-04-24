package com.cg.mts.repository;


import java.util.List;

import com.cg.mts.entities.Applicant;
import com.cg.mts.exception.ApplicantNotFoundException;

public interface IApplicantRepository {
	Applicant add(Applicant Applicant) throws ApplicantNotFoundException;
	Applicant save(Applicant Applicant) throws ApplicantNotFoundException;
	boolean	deleteById(String Applicantid) throws ApplicantNotFoundException;
	Applicant getById(String Applicant) throws ApplicantNotFoundException;
	List<Applicant> getAll() throws ApplicantNotFoundException;
	
//	String INSERT_CONTACT= "INSERT INTO contact(aid, aname, mobileNumber, aDegree , aGraduationPercent) VALUES(?,?,?,?,?)";
//	String UPDATE_CONTACT="UPDATE contact SET aname = ? mobileNumber = ? aDegree = ? aGraduationPercent = ?  WHERE aid = ?";
//	String DELETE_CONTACT="DELETE FROM contact WHERE aid = ?";
//	String GET_CONTACT_BY_ID="SELECT aid,aname,mobileNumber,aDegree,aGraduationPercent FROM contact WHERE aid = ?";
//	String GET_ALL_CONTACTS="SELECT aid,aname,mobileNumber,aDegree,agraduationPercent FROM Applicant";
}
