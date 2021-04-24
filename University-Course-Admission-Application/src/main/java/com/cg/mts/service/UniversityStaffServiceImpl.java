package com.cg.mts.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.cg.mts.entities.Course;
import com.cg.mts.entities.UniversityStaffMember;
import com.cg.mts.exception.CourseNotFoundException;
import com.cg.mts.exception.UniversityStaffMemberNotFoundException;
import com.cg.mts.repository.IUniversityStaffRepository;
import com.cg.mts.repository.UniversityStaffRepositoryImpl;

public class UniversityStaffServiceImpl implements IUniversityStaffService {

	IUniversityStaffRepository service;

	public UniversityStaffServiceImpl() {
		service = new UniversityStaffRepositoryImpl();
	}

	public boolean isValidStaffId(int staffId) {
		return staffId > 0;
	}

	public boolean isValidPassword(String password) {
		return password != null && (password.length() <= 20) && (!("".equals(password)));
	}

	public boolean isValidRole(String role) {
		return role != null && (role.length() <= 30) && (!("".equals(role)));
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
			if (!error.isEmpty()) {
				throw new CourseNotFoundException("Invalid Course Input : " + error);
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

	public boolean isValidUniversityStaff(UniversityStaffMember staffMember)
			throws UniversityStaffMemberNotFoundException {
		List<String> error = new ArrayList<>();
		boolean isvalid = true;

		if (staffMember != null) {
			if (!isValidStaffId(staffMember.getStaffId())) {
				isvalid = false;
				error.add("Staff Id must be positive number");
			}
			if (!isValidPassword(staffMember.getPassword())) {
				isvalid = false;
				error.add("Password cannot be blank and must be less then 20 characters");
			}
			if (!isValidRole(staffMember.getRole())) {
				isvalid = false;
				error.add("Role cannot be null/empty");
			}
			if (!error.isEmpty()) {
				throw new UniversityStaffMemberNotFoundException("Invalid Staff Details Input : " + error);
			}
		} else {
			isvalid = false;
			throw new UniversityStaffMemberNotFoundException("Staff Member details incorrectly supplied");
		}
		return isvalid;
	}

	@Override
	public UniversityStaffMember addStaff(UniversityStaffMember user) throws UniversityStaffMemberNotFoundException {
		if (isValidUniversityStaff(user)) {
			service.addStaff(user);
		}
		return user;
	}

	@Override
	public UniversityStaffMember updateStaff(UniversityStaffMember user) throws UniversityStaffMemberNotFoundException {
		if (isValidUniversityStaff(user)) {
			service.updateStaff(user);
		}
		return user;
	}

	@Override
	public UniversityStaffMember viewStaff(int staffid) {
		UniversityStaffMember uStaffMember = service.viewStaff(staffid);
		return uStaffMember;
	}

	@Override
	public void removeStaff(int staffid) {
		service.removeStaff(staffid);
	}

	@Override
	public List<UniversityStaffMember> viewAllStaffs() {
		List<UniversityStaffMember> members = service.viewAllStaffs();
		return members;
	}

	@Override
	public Course addCourse(Course course) throws CourseNotFoundException {
		if (isValidCourse(course)) {
			service.addCourse(course);
		}
		return course;
	}

	@Override
	public Course removeCourse(int courseId) {
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
}