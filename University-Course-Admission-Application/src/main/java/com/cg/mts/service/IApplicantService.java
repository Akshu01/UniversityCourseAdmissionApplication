package com.cg.mts.service;

import java.util.List;

import com.cg.mts.entities.Applicant;
import com.cg.mts.exception.ApplicantNotFoundException;

public interface IApplicantService {
	Applicant add(Applicant Applicant) throws ApplicantNotFoundException;
	Applicant save(Applicant Applicant) throws ApplicantNotFoundException;
	boolean	deleteById(String Applicantid) throws ApplicantNotFoundException;
	Applicant getById(String Applicantid) throws ApplicantNotFoundException;
	List<Applicant> getAll() throws ApplicantNotFoundException;
	
	
}
