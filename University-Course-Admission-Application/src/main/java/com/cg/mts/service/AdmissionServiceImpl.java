package com.cg.mts.service;

import java.time.LocalDate;
import java.util.List;

import com.cg.mts.entities.Admission;
import com.cg.mts.exception.AdmissionNotGrantedException;
import com.cg.mts.repository.AdmissionRepositoryImpl;
import com.cg.mts.repository.IAdmissionRepository;

public class AdmissionServiceImpl implements IAdmissionService {
	
	public AdmissionServiceImpl() {
		
	}
/*	boolean isValidAdmissionId(int admissionId) {
		return admissionId>0;
	}
	
	boolean isValidAdmissionDate(LocalDate admissionDate) {
		return (admissionDate !=null && admissionDate.isBefore(LocalDate.now()));
	}
	
	boolean isValidCourse(Course course) {
		
	}
	
	boolean isValidApplicantId(Applicant applicantId) {
		
	}
	*/
	
	
	IAdmissionRepository  aDao = new AdmissionRepositoryImpl();
	
	@Override
	public Admission addAdmission(Admission admission) throws AdmissionNotGrantedException {
		return aDao.addAdmission(admission);
	}

	@Override
	public Admission updateAdmission(Admission admission) throws AdmissionNotGrantedException {
		return aDao.updateAdmission(admission);
	}

	@Override
	public Admission cancelAdmission(int admissionId) throws AdmissionNotGrantedException {
		return aDao.cancelAdmission(admissionId);
	}

	@Override
	public List<Admission> showAllAdmissionsByCourseId(int courseId) {
		return aDao.showAllAdmissionsByCourseId(courseId);
	}

	@Override
	public List<Admission> showAllAdmissionsByDate(LocalDate admissionDate) {
		return aDao.showAllAdmissionsByDate(admissionDate);
	}
	
	@Override
	public Admission showAdmissionByApplicant(int applicantid) {
		return aDao.showAdmissionByApplicant(applicantid);
	}
	

}
