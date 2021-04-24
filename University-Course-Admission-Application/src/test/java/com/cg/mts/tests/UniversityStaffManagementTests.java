package com.cg.mts.tests;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.cg.mts.entities.UniversityStaffMember;
import com.cg.mts.service.IUniversityStaffService;
import com.cg.mts.service.UniversityStaffServiceImpl;
import com.cg.mts.util.JPAUtil;

public class UniversityStaffManagementTests {
	public static final IUniversityStaffService staffService = new UniversityStaffServiceImpl();

	EntityManagerFactory factory = JPAUtil.getEntityManagerFactory();
	EntityManager em = factory.createEntityManager();
	EntityTransaction txn = em.getTransaction();

	/**
	 * case when University Staff Member exist, verifying null is not returned as an output
	 */
	@Test
	public void testFindByStaffId_1() {
		int staffId = 1;

		UniversityStaffMember result = staffService.viewStaff(staffId);

		Assertions.assertNotNull(result);

	}

	/**
	 * case when University Staff Member doesn't exists, verifying no staff data fetched/null returned
	 */
	@Test
	public void testFindByStaffId_2() {
		int staffId = 101;

		UniversityStaffMember result = staffService.viewStaff(staffId);

		Assertions.assertNull(result);

	}

	/**
	 * case when University Staff Member exists, verifying staff data is validated correctly for provided inputs 
	 * precondition : UniversityStaff table exists in database
	 */
	@Test
	public void testMatchByStaffDetails_3() {
		int staffId = 1;
		String password = "Ak#@135";
		String role = "Full Stack Trainer";
		UniversityStaffMember member = new UniversityStaffMember();
		member.setStaffId(staffId);
		member.setPassword(password);
		member.setRole(role);

		member = em.merge(member);
		int sId = member.getStaffId();
		UniversityStaffMember result = staffService.viewStaff(sId);

		Assertions.assertNotSame(result, member);
	}

	/**
	 * case when University Staff Member exists, verifying staff data is validated correctly for provided inputs 
	 * precondition : UniversityStaff table exists in database
	 */
	@Test
	public void testMatchByStaffDetails_4() {
		int staffId = 1;
		String password = "dean@1234";
		String role = "Full Stack Trainer";
		UniversityStaffMember member = new UniversityStaffMember();
		member.setStaffId(staffId);
		member.setPassword(password);
		member.setRole(role);

		int sId = member.getStaffId();
		UniversityStaffMember result = staffService.viewStaff(sId);

		Assertions.assertEquals(password, result.getPassword());
		Assertions.assertEquals(role, result.getRole());
	}

}
