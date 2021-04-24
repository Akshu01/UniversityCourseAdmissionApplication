package com.cg.mts.service;

import com.cg.mts.exception.LoginFailedException;

public interface ILoginService {

	public boolean loginAsApplicant(String username,String pwd);
	public boolean loginAsAdmissionCommiteeMember(String username,String pwd);
	public boolean loginAsUniversityStaffMember(String username,String pwd) throws LoginFailedException;
	
}
