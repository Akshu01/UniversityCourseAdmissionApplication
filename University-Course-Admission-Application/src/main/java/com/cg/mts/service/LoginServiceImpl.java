package com.cg.mts.service;

import java.util.ArrayList;
import java.util.List;

import com.cg.mts.entities.Login;
import com.cg.mts.exception.LoginFailedException;
import com.cg.mts.repository.ILoginRepository;
import com.cg.mts.repository.LoginRepositoryImpl;

public class LoginServiceImpl implements ILoginService {
	
	private ILoginRepository repo;
	
	public LoginServiceImpl() {
		repo = new LoginRepositoryImpl();
	}
	public boolean isValidName(String name) {
		return name != null && (name.length() <= 20) && (!("".equals(name)));
	}

	public boolean isValidPassword(String password) {
		return password != null && (password.length() <= 20) && (!("".equals(password)));
	}
	
	public boolean isValidLogin(Login login) throws LoginFailedException {
		List<String> error = new ArrayList<>();
		boolean isvalid = true;

		if (login != null) {
			if (!isValidName(login.getUserName())) {
				isvalid = false;
				error.add("User Name cannot be blank and must be less then 20 characters");
			}
			if (!isValidPassword(login.getPassword())) {
				isvalid = false;
				error.add("password cannot be blank and must be less then 20 characters");
			}
			if (!error.isEmpty()) {
				throw new LoginFailedException("Invalid Login Input : " + error);
			}
		} else {
			isvalid = false;
			throw new LoginFailedException("Login details incorrectly supplied");
		}

		return isvalid;
	}
	
	@Override
	public boolean loginAsApplicant(String username, String pwd) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean loginAsAdmissionCommiteeMember(String username, String pwd) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean loginAsUniversityStaffMember(String username, String pwd) throws LoginFailedException {
		boolean bool = false;
		Login login = new Login(username,pwd);
		if(isValidLogin(login))
		bool = repo.verifyUniversityStaffMemberCredentials(username, pwd);
		return bool;
	}

}
