package com.cg.mts.service;

import java.util.ArrayList;
import java.util.List;

import com.cg.mts.repository.IAdmissionCommiteeMemberRepository;
import com.cg.mts.repository.AdmissionCommiteeMemberRepositoryImpl;
import com.cg.mts.entities.AdmissionCommiteeMember;
import com.cg.mts.exception.AdmissionCommiteeMemberNotFoundException;

public class AdmissionCommiteeMemberServiceImpl implements IAdmissionCommiteeMemberService {
private IAdmissionCommiteeMemberRepository AdmissionCommiteeMemberRepository;
	
	public AdmissionCommiteeMemberServiceImpl() {
		AdmissionCommiteeMemberRepository = new AdmissionCommiteeMemberRepositoryImpl();
	}
	
	public boolean isValidadminId(String adminId) {
		   return adminId != null && adminId.length() >0;
	}
	
	public boolean isValidadminName(String adminName) {
		return adminName != null && (adminName.length() >= 3 || adminName.length() <= 20);
	}
	
	public boolean isValidadminContact(String adminContact) {
		return adminContact != null && adminContact.matches("[1-9][0-9]{9}");
	}
	
	public boolean isValidAdmissionCommiteeMember(AdmissionCommiteeMember AdmissionCommiteeMember) throws AdmissionCommiteeMemberNotFoundException {
		List<String> errorMessages = new ArrayList<String>();
		boolean isValid = true;
		
		if(AdmissionCommiteeMember != null) {
			if(!isValidadminId(AdmissionCommiteeMember.getadminId())) {
				isValid = false;
				errorMessages.add("admin id cannot be blank and must be a posiitive number");
			}
			if(!isValidadminName(AdmissionCommiteeMember.getadminName())) {
				isValid = false;
				errorMessages.add("admin name cannot be blank and must be 3 to 20 characters long");
			}
		
			if(!isValidadminContact( AdmissionCommiteeMember.getadminContact())) {
				isValid = false;
				errorMessages.add("admin contact cannot be blank and should be of length 10");
			}
			if(!errorMessages.isEmpty()) {
				throw new AdmissionCommiteeMemberNotFoundException("Invalid admin :" + errorMessages);
			}
		}else {
			isValid = false;
			throw new  AdmissionCommiteeMemberNotFoundException("admin details are not supplied");
		}
		return isValid;
	}
	@Override
	public  AdmissionCommiteeMember add( AdmissionCommiteeMember AdmissionCommiteeMember) throws  AdmissionCommiteeMemberNotFoundException {
		if(isValidAdmissionCommiteeMember( AdmissionCommiteeMember))
			AdmissionCommiteeMemberRepository.add(AdmissionCommiteeMember);
		return AdmissionCommiteeMember;
	}

	@Override
	public  AdmissionCommiteeMember save( AdmissionCommiteeMember AdmissionCommiteeMember) throws  AdmissionCommiteeMemberNotFoundException {
		if(isValidAdmissionCommiteeMember(AdmissionCommiteeMember))
			AdmissionCommiteeMemberRepository.save(AdmissionCommiteeMember);
		return AdmissionCommiteeMember;
	}


	@Override
	public boolean deleteById(String  adminId) throws  AdmissionCommiteeMemberNotFoundException {
		return  AdmissionCommiteeMemberRepository.deleteById( adminId);
	}

	@Override
	public AdmissionCommiteeMember getById(String adminId) throws  AdmissionCommiteeMemberNotFoundException {
		return  AdmissionCommiteeMemberRepository.getById( adminId);
	}

	@Override
	public List<AdmissionCommiteeMember> getAll() throws  AdmissionCommiteeMemberNotFoundException {
		return  AdmissionCommiteeMemberRepository.getAll();
	}
}
