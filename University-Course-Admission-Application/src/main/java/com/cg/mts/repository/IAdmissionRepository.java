package com.cg.mts.repository;

import java.time.LocalDate;
import java.util.List;

import com.cg.mts.entities.Admission;
import com.cg.mts.exception.AdmissionNotGrantedException;

public interface IAdmissionRepository {
	public Admission addAdmission(Admission admission) throws AdmissionNotGrantedException;
	public Admission updateAdmission(Admission admission) throws AdmissionNotGrantedException;
	public Admission cancelAdmission(int admissionid) throws AdmissionNotGrantedException;
	public List<Admission> showAllAdmissionsByCourseId(int courseid);
	public List<Admission> showAllAdmissionsByDate(LocalDate admissiondate);
	public Admission showAdmissionByApplicant(int applicantid);
	
	
	
}

