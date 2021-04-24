package com.cg.mts.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.cg.mts.entities.Login;
import com.cg.mts.entities.UniversityStaffMember;
import com.cg.mts.util.JPAUtil;

public class LoginRepositoryImpl implements ILoginRepository {

	EntityManagerFactory factory = JPAUtil.getEntityManagerFactory();
	EntityManager em = factory.createEntityManager();
	EntityTransaction txn = em.getTransaction();

	@Override
	public boolean verifyApplicantCredentials(String username, String pwd) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean verifyAdmissionCommiteeMemberCredentials(String username, String pwd) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean verifyUniversityStaffMemberCredentials(String username, String pwd) {
		Login l = new Login(username, pwd);
		Login bool = em.find(Login.class, l);
		if(bool == null)
		{
			return false;
		}
		UniversityStaffMember uId = bool.getMember();
		System.out.println(uId);
		if (uId != null)
			return true;
		else
			return false;

	}

}
