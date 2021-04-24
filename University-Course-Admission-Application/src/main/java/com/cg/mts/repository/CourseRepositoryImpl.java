package com.cg.mts.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.cg.mts.entities.Course;
import com.cg.mts.exception.CourseNotFoundException;
import com.cg.mts.util.JPAUtil;

public class CourseRepositoryImpl implements ICourseRepository {

	EntityManagerFactory factory = JPAUtil.getEntityManagerFactory();
	EntityManager em = factory.createEntityManager();
	EntityTransaction txn = em.getTransaction();

	@Override
	public Course addCourse(Course course) {
		txn.begin();
		em.persist(course);
		txn.commit();
		return course;
	}

	@Override
	public Course removeCourse(int courseid) throws CourseNotFoundException {
		Course c = em.find(Course.class, courseid);
		txn.begin();
		em.remove(c);
		txn.commit();
		return c;
	}

	@Override
	public Course updateCourse(Course course) throws CourseNotFoundException {
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

	@Override
	public Course viewCourse(int courseid) throws CourseNotFoundException {
		Course c = em.find(Course.class, courseid);
		return c;
	}

	@Override
	public List<Course> viewCourseList() {
		String jpql = "SELECT e FROM Course e";
		TypedQuery<Course> tqry = em.createQuery(jpql, Course.class);
		List<Course> ls = tqry.getResultList();
		return ls;
	}

}
