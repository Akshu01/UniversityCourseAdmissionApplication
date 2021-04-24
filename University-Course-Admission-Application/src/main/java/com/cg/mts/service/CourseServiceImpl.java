package com.cg.mts.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.cg.mts.entities.Course;
import com.cg.mts.exception.CourseNotFoundException;
import com.cg.mts.repository.CourseRepositoryImpl;
import com.cg.mts.repository.ICourseRepository;

public class CourseServiceImpl implements ICourseService {

	private ICourseRepository service;

	public CourseServiceImpl() {
		service = new CourseRepositoryImpl();
	}

	public boolean isValidCourseId(int courseId) {
		return courseId > 0;
	}

	public boolean isValidCourseName(String courseName) {
		return courseName != null && (courseName.length() <= 30) && (!("".equals(courseName)));
	}

	public boolean isValidCourseDuration(String duration) {
		return duration != null && (duration.length() <= 20) && (!("".equals(duration)));
	}

	public boolean isValidCourseStartDate(LocalDate startDate) {
		LocalDate dateValidator = LocalDate.parse("2021-04-30");
		return startDate != null && (startDate.isAfter(dateValidator));
	}

	public boolean isValidCourseEndDate(LocalDate endDate) {
		LocalDate dateValidator = LocalDate.parse("2021-05-01");
		return endDate != null && (endDate.isAfter(dateValidator));
	}

	public boolean isValidFees(String fees) {
		return fees != null && (!(fees).contentEquals("0")) && (!("".equals(fees)));
	}

	public boolean isValidCourse(Course course) throws CourseNotFoundException {
		List<String> error = new ArrayList<>();
		boolean isvalid = true;

		if (course != null) {
			if (!isValidCourseId(course.getCourseId())) {
				isvalid = false;
				error.add("Course Id must be positive number");
			}
			if (!isValidCourseName(course.getCourseName())) {
				isvalid = false;
				error.add("Course Name cannot be blank and must be less then 30 characters");
			}
			if (!isValidCourseDuration(course.getCourseDuration())) {
				isvalid = false;
				error.add("Course Duration cannot be null!! Please specify appropriate Course Duration");
			}
			if (!isValidFees(course.getCourseFees())) {
				isvalid = false;
				error.add("Course Fees cannot be Rupee 0, course is paid.");
			}
			if (!isValidCourseStartDate(course.getCourseStartDate())) {
				isvalid = false;
				error.add("Start Date can't be empty and also Courses Offered starts from May 1,2021");
			}
			if (!isValidCourseEndDate(course.getCourseEndDate())) {
				isvalid = false;
				error.add(
						"End Date can't be empty and also Course can't end before May 1,2021, correct the course end date!!");
			}
			if (!error.isEmpty()) {
				throw new CourseNotFoundException("Invalid Course Input : " + error);
			}
		} else {
			isvalid = false;
			throw new CourseNotFoundException("Course details incorrectly supplied");
		}

		return isvalid;
	}

	@Override
	public Course addCourse(Course course) throws CourseNotFoundException {
		if (isValidCourse(course)) {
			service.addCourse(course);
		}
		return course;
	}

	@Override
	public Course removeCourse(int courseId) throws CourseNotFoundException {
		Course courses = service.removeCourse(courseId);
		return courses;
	}

	@Override
	public Course updateCourse(Course course) throws CourseNotFoundException {
		if (isValidCourse(course)) {
			service.updateCourse(course);
		}
		return course;
	}

	@Override
	public Course viewCourse(int courseid) throws CourseNotFoundException {
		Course courses = service.viewCourse(courseid);
		return courses;
	}

	@Override
	public List<Course> viewAllCourses() {
		List<Course> courses = service.viewCourseList();
		return courses;
	}

}
