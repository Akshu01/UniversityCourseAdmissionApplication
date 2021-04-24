package com.cg.mts.repository;

import java.util.List;

import com.cg.mts.entities.Course;
import com.cg.mts.entities.UniversityStaffMember;

public interface IUniversityStaffRepository {
	public  UniversityStaffMember addStaff(UniversityStaffMember user);
	public UniversityStaffMember updateStaff(UniversityStaffMember user);
	public UniversityStaffMember viewStaff(int staffid);
	public void removeStaff(int staffid);
	public List<UniversityStaffMember> viewAllStaffs();
	public Course addCourse(Course course);
	public Course removeCourse(int courseId);
	public Course updateCourse(Course course);
}
