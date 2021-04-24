package com.cg.mts.tests;

import java.time.LocalDate;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.cg.mts.entities.Course;
import com.cg.mts.exception.CourseNotFoundException;
import com.cg.mts.service.CourseServiceImpl;
import com.cg.mts.service.ICourseService;
import com.cg.mts.util.JPAUtil;

public class CourseManagementTests {

	public static final ICourseService courseService = new CourseServiceImpl();

	EntityManagerFactory factory = JPAUtil.getEntityManagerFactory();
	EntityManager em = factory.createEntityManager();
	EntityTransaction txn = em.getTransaction();

	/**
	 * case when Course doesn't exist, verifying no course data is fetched and null is returned
	 * 
	 * @throws CourseNotFoundException
	 */
	@Test
	public void testFindByCourseId_1() throws CourseNotFoundException {
		int courseId = 101;

		Course result = courseService.viewCourse(courseId);
		Assertions.assertNull(result);

	}

	/**
	 * case when course exist, verifying null is not returned as an output
	 * 
	 * @throws CourseNotFoundException
	 */
	@Test
	public void testFindByCourseId_3() throws CourseNotFoundException {
		int courseId = 1;

		Course result = courseService.viewCourse(courseId);

		Assertions.assertNotNull(result);

	}

	/**
	 * case when Course exists, verifying course data is validated correctly or not for provided inputs 
	 * precondition : UniversityStaff table exists in database
	 * 
	 * @throws CourseNotFoundException
	 */
	@Test
	public void testMatchByStaffDetails_4() throws CourseNotFoundException {
		Course course = new Course();
		course.setCourseId(1);
		course.setCourseName("Java CORE");
		course.setCourseDuration("5 Months");
		course.setCourseStartDate(LocalDate.parse("2021-05-01"));
		course.setCourseEndDate(LocalDate.parse("2021-08-10"));
		course.setCourseFees("3500");

		int cId = course.getCourseId();
		Course result = courseService.viewCourse(cId);

		Assertions.assertNotSame(result, course);
	}

	/**
	 * case when Course exists, verifying course data is validated correctly for provided inputs
	 * precondition : UniversityStaff table exists in database
	 * 
	 * @throws CourseNotFoundException
	 */
	@Test
	public void testMatchByStaffDetails_5() throws CourseNotFoundException {
		int id = 1;
		String courseName = "Java CORE";
		String courseDuration = "3 Months";
		LocalDate courseStartDate = LocalDate.parse("2021-05-01");
		LocalDate courseEndDate = LocalDate.parse("2021-08-10");
		String courseFees = "3500";
		Course course = new Course();
		course.setCourseId(id);
		course.setCourseName(courseName);
		course.setCourseDuration(courseDuration);
		course.setCourseStartDate(courseStartDate);
		course.setCourseEndDate(courseEndDate);
		course.setCourseFees(courseFees);

		int cId = course.getCourseId();
		Course result = courseService.viewCourse(cId);

		Assertions.assertEquals(courseName, result.getCourseName());
		Assertions.assertEquals(courseDuration, result.getCourseDuration());
		Assertions.assertEquals(courseStartDate, result.getCourseStartDate());
		Assertions.assertEquals(courseEndDate, result.getCourseEndDate());
		Assertions.assertEquals(courseFees, result.getCourseFees());
	}

}