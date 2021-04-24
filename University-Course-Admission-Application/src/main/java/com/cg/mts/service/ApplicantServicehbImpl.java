package com.cg.mts.service;

import java.util.ArrayList;
import java.util.List;

import com.cg.mts.repository.IApplicantRepository;
import com.cg.mts.repository.ApplicantRepositoryImpl;
import com.cg.mts.entities.Applicant;
import com.cg.mts.exception.ApplicantNotFoundException;

public class ApplicantServicehbImpl implements IApplicantService {
private IApplicantRepository ApplicantRepository;
	
	public ApplicantServicehbImpl() {
		ApplicantRepository = new ApplicantRepositoryImpl();
	}
	
	public boolean isValidapplicantId(String applicantId) {
		   return applicantId != null && applicantId.length() >0;
	}
	
	public boolean isValidapplicantName(String applicantName) {
		return applicantName != null && (applicantName.length() >= 3 || applicantName.length() <= 20);
	}
	
	public boolean isValidmobileNumber(String mobileNumber) {
		return mobileNumber != null && mobileNumber.matches("[1-9][0-9]{9}");
	}
	
	public boolean isValidApplicant(Applicant Applicant) throws ApplicantNotFoundException {
		List<String> errorMessages = new ArrayList<String>();
		boolean isValid = true;
		
		if(Applicant != null) {
			if(!isValidapplicantId(Applicant.getapplicantId())) {
				isValid = false;
				errorMessages.add("applciant id cannot be blank and must be a posiitive number");
			}
			if(!isValidapplicantName(Applicant.getapplicantName())) {
				isValid = false;
				errorMessages.add("applicant name cannot be blank and must be 3 to 20 characters long");
			}
		
			if(!isValidmobileNumber( Applicant.getmobileNumber())) {
				isValid = false;
				errorMessages.add("mobile number cannot be blank and should be of length 10");
			}
			if(!errorMessages.isEmpty()) {
				throw new ApplicantNotFoundException("Invalid applciant :" + errorMessages);
			}
		}else {
			isValid = false;
			throw new  ApplicantNotFoundException("applicant details are not supplied");
		}
		return isValid;
	}
	@Override
	public  Applicant add( Applicant applicant) throws  ApplicantNotFoundException {
		if(isValidApplicant( applicant))
			 ApplicantRepository.add(applicant);
		return applicant;
	}

	@Override
	public  Applicant save( Applicant applicant) throws  ApplicantNotFoundException {
		if(isValidApplicant(applicant))
			 ApplicantRepository.save(applicant);
		return applicant;
	}


	@Override
	public boolean deleteById(String  applicantId) throws  ApplicantNotFoundException {
		return  ApplicantRepository.deleteById( applicantId);
	}

	@Override
	public  Applicant getById(String  applicantId) throws  ApplicantNotFoundException {
		return  ApplicantRepository.getById( applicantId);
	}

	@Override
	public List<Applicant> getAll() throws  ApplicantNotFoundException {
		return  ApplicantRepository.getAll();
	}
}
