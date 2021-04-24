package com.cg.mts.repository;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.cg.mts.entities.Applicant;
import com.cg.mts.exception.ApplicantNotFoundException;
import com.cg.mts.util.JPAUtil;

public class ApplicantRepositoryImpl implements IApplicantRepository {
	
	EntityManagerFactory factory = JPAUtil.getEntityManagerFactory();
	EntityManager em = factory.createEntityManager();
	EntityTransaction txn = em.getTransaction();

	@Override
	public Applicant add(Applicant Applicant) throws ApplicantNotFoundException {
		txn.begin();
		em.persist(Applicant);
		txn.commit();
		return Applicant;
	}

	@Override
	public Applicant save(Applicant Applicant) throws ApplicantNotFoundException {
		return null;
	}

	@Override
	public boolean deleteById(String ApplicantId) throws ApplicantNotFoundException {
		Applicant c = em.find(Applicant.class, ApplicantId);
		txn.begin();
		em.remove(c);
		txn.commit();

		return true;
	}

	@Override
	public Applicant getById(String ApplicantId) throws ApplicantNotFoundException {
		Applicant c = em.find(Applicant.class, ApplicantId);
		return c;
	}

	@Override
	public List<Applicant> getAll() throws ApplicantNotFoundException {
		String jpql = "SELECT e FROM Applicant e";
		TypedQuery<Applicant> tqry = em.createQuery(jpql, Applicant.class);
		List<Applicant> ls = tqry.getResultList();
		return ls;
	}

}
