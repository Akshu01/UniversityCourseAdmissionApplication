package com.cg.mts.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.cg.mts.util.JPAUtil;
import com.cg.mts.entities.Admission;
import com.cg.mts.entities.Course;
import com.cg.mts.exception.AdmissionNotGrantedException;

public class AdmissionRepositoryImpl implements IAdmissionRepository {
	EntityManagerFactory factory = JPAUtil.getEntityManagerFactory();
	
	EntityManager em = factory.createEntityManager();
	
	EntityTransaction txn = em.getTransaction();
	
	@Override
	public Admission addAdmission(Admission admission) throws AdmissionNotGrantedException {
		txn.begin();
		em.persist(admission);
		txn.commit();
		return admission;
	}

	@Override
	public Admission updateAdmission(Admission admission) throws AdmissionNotGrantedException {
	    Scanner scan = new Scanner(System.in);
		Admission a = em.find(Admission.class,admission.getAdmissionId());
		txn.begin();
		System.out.println("Enter new Course ID: ");
		Course c = a.getCourse();
		c.setCourseId(scan.nextInt());
		System.out.println("Enter new Date of join: ");
		a.setAdmissionDate(LocalDate.parse(scan.next()));
		txn.commit();
		scan.close();
		return a;	
	}

	@Override
	public Admission cancelAdmission(int admissionid) throws AdmissionNotGrantedException {
		Admission a = em.find(Admission.class,admissionid);
		txn.begin();
		em.remove(a);
		txn.commit();
		return null;
	}

	@Override
	public List<Admission> showAllAdmissionsByCourseId(int courseid) {
		String jpql = "SELECT a FROM Admission a WHERE a.CourseId = :c";
		TypedQuery<Admission> tqry = em.createQuery(jpql,Admission.class);
		tqry.setParameter("c",courseid);
		List<Admission> admissions = tqry.getResultList();
		return admissions;
	}

	@Override
	public List<Admission> showAllAdmissionsByDate(LocalDate admissiondate) {
		TypedQuery<Admission> tqry = em.createQuery("SELECT a FROM Admission a WHERE a.admissionDate = :d",Admission.class);
		tqry.setParameter("d",admissiondate);
		List<Admission> admissions = tqry.getResultList();
		return admissions;
	}

	@Override
	public Admission showAdmissionByApplicant(int applicantid) {
		String jpql = "SELECT a FROM admission a WHERE a.ApplicantId = :i";
		TypedQuery<Admission> tqry = em.createQuery(jpql,Admission.class);
		tqry.setParameter("i",applicantid);
		Admission admission = tqry.getSingleResult();
		return admission;
	}
	
	

}
