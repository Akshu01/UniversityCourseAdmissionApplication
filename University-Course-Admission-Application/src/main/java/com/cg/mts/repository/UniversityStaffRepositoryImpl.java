package com.cg.mts.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.cg.mts.entities.Course;
import com.cg.mts.entities.UniversityStaffMember;
import com.cg.mts.util.JPAUtil;

public class UniversityStaffRepositoryImpl implements IUniversityStaffRepository {

	EntityManagerFactory factory = JPAUtil.getEntityManagerFactory();
	EntityManager em = factory.createEntityManager();
	EntityTransaction txn = em.getTransaction();

	@Override
	public UniversityStaffMember addStaff(UniversityStaffMember user) {
		txn.begin();
		em.persist(user);
		txn.commit();
		return user;
	}

	@Override
	public UniversityStaffMember updateStaff(UniversityStaffMember user) {
		UniversityStaffMember usm = em.find(UniversityStaffMember.class, user.getStaffId());
		usm.setPassword(user.getPassword());
		usm.setRole(user.getRole());
		txn.begin();
		em.persist(usm);
		txn.commit();
		return usm;
	}

	@Override
	public UniversityStaffMember viewStaff(int staffid) {
		UniversityStaffMember usm = em.find(UniversityStaffMember.class, staffid);
		return usm;
	}

	@Override
	public void removeStaff(int staffid) {
		UniversityStaffMember usm = em.find(UniversityStaffMember.class, staffid);
		txn.begin();
		em.remove(usm);
		txn.commit();
	}

	@Override
	public List<UniversityStaffMember> viewAllStaffs() {
		String jpql = "SELECT e FROM UniversityStaffMember e";
		TypedQuery<UniversityStaffMember> tqry = em.createQuery(jpql, UniversityStaffMember.class);
		List<UniversityStaffMember> ls = tqry.getResultList();
		return ls;
	}

	@Override
	public Course addCourse(Course course) {
		int staffId = course.getUniversityStaffMember().getStaffId();
		UniversityStaffMember usm = em.find(UniversityStaffMember.class, staffId);
		boolean bool = (usm.getStaffId() + "") != null;
		if (bool) {
			txn.begin();
			em.persist(course);
			txn.commit();
			return course;
		} else
			return null;
	}

	@Override
	public Course removeCourse(int courseId) {
		Course course = em.find(Course.class, courseId);
		txn.begin();
		em.remove(course);
		txn.commit();
		return course;
	}

	@Override
	public Course updateCourse(Course course) {
		Course courses = em.find(Course.class, course.getCourseId());
		courses.setCourseName(course.getCourseName());
		courses.setCourseDuration(course.getCourseDuration());
		courses.setCourseStartDate(course.getCourseStartDate());
		courses.setCourseEndDate(course.getCourseEndDate());
		courses.setCourseFees(course.getCourseFees());
		txn.begin();
		em.persist(courses);
		txn.commit();
		return courses;
	}

}
