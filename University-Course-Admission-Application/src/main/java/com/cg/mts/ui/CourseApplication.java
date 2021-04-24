package com.cg.mts.ui;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.cg.mts.entities.Course;
import com.cg.mts.entities.UniversityStaffMember;
import com.cg.mts.exception.CourseNotFoundException;
import com.cg.mts.exception.UniversityStaffMemberNotFoundException;
import com.cg.mts.service.CourseServiceImpl;
import com.cg.mts.service.ICourseService;
import com.cg.mts.service.IUniversityStaffService;
import com.cg.mts.service.UniversityStaffServiceImpl;

public class CourseApplication {

	public static final ICourseService courseService = new CourseServiceImpl();
	public static final IUniversityStaffService staffService = new UniversityStaffServiceImpl();
	private static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) throws CourseNotFoundException {
		CourseMenu[] menus = CourseMenu.values();
		CourseMenu selectedMenu = null;

		while (selectedMenu != CourseMenu.QUIT) {
			System.out.println("UNIVERSITY OFFERED COURSES MAY-2021");
			System.out.println("Course Operation");
			for (CourseMenu menu : menus) {
				System.out.println(menu.ordinal() + "\t" + menu);
			}
			System.out.print("Enter choice : ");
			int ch = scan.nextInt();

			if (ch >= 0 && ch <= menus.length) {
				selectedMenu = menus[ch];

				switch (selectedMenu) {
				case ADD_COURSE:
					doAddCourse();
					break;
				case VIEW_COURSE:
					doFindCourse();
					break;
				case VIEW_ALL_COURSE:
					doListAllCourses();
					break;
				case UPDATE_COURSE:
					doUpdateCourse();
					break;
				case REMOVE_COURSE:
					doRemoveCourse();
					break;
				default:
					break;
				}
			} else {
				selectedMenu = null;

			}

		}
		scan.close();
		System.out.println("Course Application Terminated");

	}

	private static void doAddCourse() {
		Course course = new Course();
		Course checkCourse = new Course();
		UniversityStaffMember usm = new UniversityStaffMember();
		try {
			System.out.print("Enter course Id :");
			int courseId = scan.nextInt();
			course.setCourseId(courseId);
			scan.nextLine();

			checkCourse = courseService.viewCourse(courseId);
			if (checkCourse != null) {
				throw new CourseNotFoundException("Course Already Exists");
			} else {
				System.out.print("Enter Course Name :");
				String courseName = scan.nextLine();
				course.setCourseName(courseName);
				System.out.print("Enter Course Duration :");
				course.setCourseDuration(scan.nextLine());
				System.out.print("Enter Course Start Date :");
				course.setCourseStartDate(LocalDate.parse(scan.nextLine()));
				System.out.print("Enter Course End Date :");
				course.setCourseEndDate(LocalDate.parse(scan.nextLine()));
				System.out.print("Enter Course Fees :");
				course.setCourseFees(scan.nextLine());
				System.out.println("Enter the staff id :");
				int staffId = scan.nextInt();

				usm = staffService.viewStaff(staffId);
				try {
					if (usm != null) {
						course.setUniversityStaffMember(usm);

						courseService.addCourse(course);
						System.out.println("Course added ");
					} else {
						throw new UniversityStaffMemberNotFoundException("No staff member with provided Id");
					}
				} catch (UniversityStaffMemberNotFoundException excep) {
					System.out.println(excep.getMessage());
				}
			}
		} catch (CourseNotFoundException error) {
			System.out.println(error.getMessage());
		}
	}

	private static void doFindCourse() {
		System.out.println("Enter Course Id to be searched :");
		int courseId = scan.nextInt();

		try {
			Course course = courseService.viewCourse(courseId);

			if (course == null) {
				throw new CourseNotFoundException("No course with Id #" + courseId + " present");
			} else {
				System.out.println(course);
			}
			System.out.println("Want to see staff details related to this course? Y/N");
			String choice = scan.next();
			if (choice.contentEquals("Y")) {
				System.out.println(course.getUniversityStaffMember());
			}

		} catch (CourseNotFoundException excep) {
			System.out.println(excep.getMessage());
		}
	}

	private static void doListAllCourses() {
		List<Course> courses = courseService.viewAllCourses();
		try {
			if (courses.isEmpty()) {
				throw new CourseNotFoundException("No courses found");
			} else {
				for (Course course : courses) {
					System.out.println(course);
				}
			}
		} catch (CourseNotFoundException excep) {
			System.out.println(excep.getMessage());
		}
	}

	private static void doUpdateCourse() {
		System.out.print("Enter Course Id whose details are to be updated :");
		int courseId = scan.nextInt();
		try {
			Course course = courseService.viewCourse(courseId);

			if (course == null) {
				throw new CourseNotFoundException(
						"No course updation with #" + courseId + " is possible as it doesn't exist.");
			} else {
				System.out.print("Enter Updated Course Name :");
				course.setCourseName(scan.next());
				scan.nextLine();
				System.out.print("Enter Updated Course Duration :");
				course.setCourseDuration(scan.nextLine());
				System.out.print("Enter Updated Course Start Date :");
				course.setCourseStartDate(LocalDate.parse(scan.next()));
				System.out.print("Enter Updated Course End Date :");
				course.setCourseEndDate(LocalDate.parse(scan.next()));
				System.out.print("Enter Updated Course Fees :");
				course.setCourseFees(scan.next());

				courseService.updateCourse(course);
				System.out.println("Course updated");
			}
		} catch (CourseNotFoundException excep) {
			System.out.println(excep.getMessage());
		}
	}

	private static void doRemoveCourse() {
		try {
			System.out.println("Enter course id :");
			int courseId = scan.nextInt();
			Course course = courseService.viewCourse(courseId);
			if (course == null) {
				throw new CourseNotFoundException("No course with #" + courseId + "to be removed.");
			} else {
				courseService.removeCourse(courseId);
				System.out.println("Course Deleted");
			}
		} catch (CourseNotFoundException excep) {
			System.out.println(excep.getMessage());
		}

	}

}