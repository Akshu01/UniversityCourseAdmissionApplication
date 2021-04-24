package com.cg.mts.service;

import java.util.List;

import com.cg.mts.entities.Course;
import com.cg.mts.entities.UniversityStaffMember;
import com.cg.mts.exception.CourseNotFoundException;
import com.cg.mts.exception.UniversityStaffMemberNotFoundException;

public interface IUniversityStaffService {
	public  UniversityStaffMember addStaff(UniversityStaffMember user) throws UniversityStaffMemberNotFoundException;
	public UniversityStaffMember updateStaff(UniversityStaffMember user) throws UniversityStaffMemberNotFoundException;
	public UniversityStaffMember viewStaff(int staffid);
	public void removeStaff(int staffid);
	public List<UniversityStaffMember> viewAllStaffs();
	public Course addCourse(Course course) throws CourseNotFoundException;
	public Course removeCourse(int courseId) throws CourseNotFoundException;
	public Course updateCourse(Course course) throws CourseNotFoundException;
	
}
