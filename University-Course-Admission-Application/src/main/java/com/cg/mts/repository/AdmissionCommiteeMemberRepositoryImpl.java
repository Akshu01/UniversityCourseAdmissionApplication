package com.cg.mts.repository;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.cg.mts.entities.AdmissionCommiteeMember;
import com.cg.mts.exception.AdmissionCommiteeMemberNotFoundException;
import com.cg.mts.util.JPAUtil;

public class AdmissionCommiteeMemberRepositoryImpl implements IAdmissionCommiteeMemberRepository {
	
	EntityManagerFactory factory = JPAUtil.getEntityManagerFactory();
	EntityManager em = factory.createEntityManager();
	EntityTransaction txn = em.getTransaction();

	@Override
	public AdmissionCommiteeMember add(AdmissionCommiteeMember AdmissionCommiteeMember) throws AdmissionCommiteeMemberNotFoundException {
		txn.begin();
		em.persist(AdmissionCommiteeMember);
		txn.commit();
		return AdmissionCommiteeMember;
	}

	@Override
	public AdmissionCommiteeMember save(AdmissionCommiteeMember AdmissionCommiteeMember) throws AdmissionCommiteeMemberNotFoundException {
		return null;
	}

	@Override
	public boolean deleteById(String adminId) throws AdmissionCommiteeMemberNotFoundException {
		AdmissionCommiteeMember c = em.find(AdmissionCommiteeMember.class, adminId);
		txn.begin();
		em.remove(c);
		txn.commit();

		return true;
	}

	@Override
	public AdmissionCommiteeMember getById(String adminId) throws AdmissionCommiteeMemberNotFoundException {
		AdmissionCommiteeMember c = em.find(AdmissionCommiteeMember.class, adminId);
		return c;
	}

	@Override
	public List<AdmissionCommiteeMember> getAll() throws AdmissionCommiteeMemberNotFoundException {
		String jpql = "SELECT e FROM AdmissionCommiteeMember e";
		TypedQuery<AdmissionCommiteeMember> tqry = em.createQuery(jpql, AdmissionCommiteeMember.class);
		List<AdmissionCommiteeMember> ls = tqry.getResultList();
		return ls;
	}

}
