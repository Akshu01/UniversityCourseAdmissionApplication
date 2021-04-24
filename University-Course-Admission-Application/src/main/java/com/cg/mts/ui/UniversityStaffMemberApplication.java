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

public class UniversityStaffMemberApplication {
	public static final IUniversityStaffService staffService = new UniversityStaffServiceImpl();
	public static final ICourseService courseService = new CourseServiceImpl();
	static final Scanner scan = new Scanner(System.in);

	public static void main(String[] args) throws CourseNotFoundException {
		UniversityStaffMenu[] menus = UniversityStaffMenu.values();
		UniversityStaffMenu selectedMenu = null;

		while (selectedMenu != UniversityStaffMenu.QUIT) {
			System.out.println("University Staff Member Operation");
			for (UniversityStaffMenu menu : menus) {
				System.out.println(menu.ordinal() + "\t" + menu);
			}
			System.out.print("Enter choice : ");
			int ch = scan.nextInt();

			if (ch >= 0 && ch <= menus.length) {
				selectedMenu = menus[ch];

				switch (selectedMenu) {
				case ADD_STAFF:
					doAdd();
					break;
				case UPDATE_STAFF:
					doUpdate();
					break;
				case VIEW_STAFF:
					doFind();
					break;
				case REMOVE_STAFF:
					doRemove();
					break;
				case VIEW_ALL_STAFF:
					doList();
					break;
				case ADD_COURSE:
					doAddCourse();
					break;
				case REMOVE_COURSE:
					doRemoveCourse();
					break;
				case UPDATE_COURSE:
					doUpdateCourse();
					break;
				default:
					break;
				}
			} else {
				selectedMenu = null;
			}

		}
		scan.close();
		System.out.println("University Staff Member Application Terminated");

	}

	private static void doAdd() {
		UniversityStaffMember member = new UniversityStaffMember();
		UniversityStaffMember checker = new UniversityStaffMember();
		try {
			System.out.print("Enter Staff Id :");
			int staffId = scan.nextInt();
			member.setStaffId(staffId);
			scan.nextLine();

			checker = staffService.viewStaff(staffId);
			if (checker != null) {
				throw new UniversityStaffMemberNotFoundException("Staff member already exists with provided staff Id");
			} else {
				System.out.print("Enter Password :");
				String password = scan.nextLine();
				member.setPassword(password);
				System.out.print("Enter Staff Role :");
				member.setRole(scan.nextLine());

				staffService.addStaff(member);
				System.out.println("Staff Member Added");
			}
		} catch (UniversityStaffMemberNotFoundException excep) {
			System.out.println("Unable to add staff as " + excep.getMessage());
		}
	}

	private static void doUpdate() {
		System.out.print("Enter Staff Id whose details are to be updated :");
		int staffId = scan.nextInt();

		try {
			UniversityStaffMember member = staffService.viewStaff(staffId);
			if (member == null) {
				throw new UniversityStaffMemberNotFoundException(
						"No staff details updation with #" + staffId + " is possible as it doesn't exist.");
			} else {
				System.out.print("Enter Updated Password :");
				member.setPassword(scan.next());
				System.out.print("Enter Updated Role :");
				member.setRole(scan.next());

				staffService.updateStaff(member);
				System.out.println("Staff Member details updated");
			}
		} catch (UniversityStaffMemberNotFoundException excep) {
			System.out.println(excep.getMessage());
		}
	}

	private static void doFind() {
		System.out.println("Enter Staff Id :");
		int staffId = scan.nextInt();

		try {
			UniversityStaffMember member = staffService.viewStaff(staffId);
			if (member == null) {
				throw new UniversityStaffMemberNotFoundException(
						"No staff member with staff id #" + staffId + " present");
			} else {
				System.out.println(member);
			}
		} catch (UniversityStaffMemberNotFoundException excep) {
			System.out.println(excep.getMessage());
		}
	}

	private static void doRemove() {
		try {
			System.out.println("Enter Staff Id :");
			int staffId = scan.nextInt();
			UniversityStaffMember member = staffService.viewStaff(staffId);
			if (member == null) {
				throw new UniversityStaffMemberNotFoundException("No staff member with staff id #" + staffId);
			} else {
				staffService.removeStaff(staffId);
				System.out.println("Staff Member Removed");
			}
		} catch (UniversityStaffMemberNotFoundException excep) {
			System.out.println(excep.getMessage());
		}
	}

	private static void doList() {
		List<UniversityStaffMember> list = staffService.viewAllStaffs();
		try {
			if (list.isEmpty()) {
				throw new UniversityStaffMemberNotFoundException("No members found");
			} else {
				for (UniversityStaffMember member : list) {
					System.out.println(member);
				}
			}
		} catch (UniversityStaffMemberNotFoundException excep) {
			System.out.println(excep.getMessage());
		}
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
						throw new UniversityStaffMemberNotFoundException("No staff member with provided Id, course insertion failed");
					}
				} catch (UniversityStaffMemberNotFoundException excep) {
					System.out.println(excep.getMessage());
				}
			}
		} catch (CourseNotFoundException error) {
			System.out.println(error.getMessage());
		}
	}

	private static void doUpdateCourse() throws CourseNotFoundException {
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

				staffService.updateCourse(course);
				System.out.println("Course updated");
			}
		} catch (CourseNotFoundException excep) {
			System.out.println(excep.getMessage());
		}

	}

	private static void doRemoveCourse() throws CourseNotFoundException {
		try {
			System.out.println("Enter course id :");
			int courseId = scan.nextInt();
			Course course = courseService.viewCourse(courseId);
			if (course == null) {
				throw new CourseNotFoundException("No course with #" + courseId + " present to be removed");
			} else {
				staffService.removeCourse(courseId);
				System.out.println("Course Deleted");
			}
		} catch (CourseNotFoundException excep) {
			System.out.println(excep.getMessage());
		}

	}

}
