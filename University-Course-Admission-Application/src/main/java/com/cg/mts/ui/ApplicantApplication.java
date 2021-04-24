package com.cg.mts.ui;

import java.util.List;
import java.util.Scanner;

import com.cg.mts.entities.Applicant;
import com.cg.mts.exception.ApplicantNotFoundException;
import com.cg.mts.service.ApplicantServicehbImpl;
import com.cg.mts.service.IApplicantService;

public class ApplicantApplication {

	public static final IApplicantService applicantService = new ApplicantServicehbImpl();
	private static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		Menu[] menus = Menu.values();
		Menu selectedMenu = null;

		while (selectedMenu != Menu.QUIT) {
			System.out.println("Choice\tOperation");
			for (Menu menu : menus) {
				System.out.println(menu.ordinal() + "\t" + menu);
			}
			System.out.print("Enter choice : ");
			int ch = scan.nextInt();

			if (ch >= 0 && ch <= menus.length) {
				selectedMenu = menus[ch];

				switch (selectedMenu) {
				case ADD:
					doAdd();
					break;
				case FIND:
					doFind();
					break;
				case LIST:
					doList();
					break;
				case DELETE:
					doDelete();
					break;
				default:
					break;
				}
			} else {
				selectedMenu = null;

			}

		}

	}

	private static void doAdd() {
		Applicant Applicant = new Applicant();
		System.out.print("Enter applicant Id :");
		Applicant.setapplicantId(scan.next());
		System.out.print("Enter Applicant Name :");
		Applicant.setapplicantName(scan.next());
		System.out.print("Enter Mobile Number :");
		Applicant.setmobileNumber(scan.next());
		System.out.print("Enter Applicant Degree :");
		Applicant.setapplicantDegree(scan.next());
		System.out.print("Enter Applicant Graducation Percent :");
		Applicant.setapplicantGraduationPercent(scan.nextFloat());


		try {
			applicantService.add(Applicant);
			System.out.println("applicant added");
		} catch (ApplicantNotFoundException excep) {
			System.out.println(excep.getMessage());
		}
	}

	private static void doList() {
		try {
			List<Applicant> Applicant = applicantService.getAll();
			if (Applicant.isEmpty()) {
				System.out.println("No applciants found");
			} else {
				for (Applicant applicant : Applicant) {
					System.out.println(applicant);
				}
			}
		} catch (ApplicantNotFoundException excep) {
			System.out.println(excep.getMessage());
		}
	}

	private static void doFind() {
		System.out.println("enter applcaint id :");
		String applicantId = scan.next();

		try {
			Applicant contact = applicantService.getById(applicantId);

			if (contact == null) {
				System.out.println("No applicant with #" + applicantId);
			} else {
				System.out.println(contact);
			}
		} catch (ApplicantNotFoundException excep) {
			System.out.println(excep.getMessage());
		}
	}

	private static void doDelete() {
		try {
			System.out.println("Enter applicant id :");
			String ApplicantId = scan.next();
			Applicant contact = applicantService.getById(ApplicantId);
			if (contact == null) {
				System.out.println("No applicant with #" + ApplicantId);
			} else {
				applicantService.deleteById(ApplicantId);
				System.out.println("Applicant Deleted");
			}
		} catch (ApplicantNotFoundException excep) {
			System.out.println(excep.getMessage());
		}

	}
}