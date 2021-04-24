package com.cg.mts.ui;

import java.util.List;
import java.util.Scanner;

import com.cg.mts.entities.AdmissionCommiteeMember;
import com.cg.mts.exception.AdmissionCommiteeMemberNotFoundException;
import com.cg.mts.service.AdmissionCommiteeMemberServiceImpl;
import com.cg.mts.service.IAdmissionCommiteeMemberService;

public class AdmissionCommiteeMemberApplication {

	public static final IAdmissionCommiteeMemberService AdmissionCommiteeMemberService = new AdmissionCommiteeMemberServiceImpl();
	private static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		AdmissionCommiteeMemberMenu[] menus = AdmissionCommiteeMemberMenu.values();
		AdmissionCommiteeMemberMenu selectedMenu = null;

		while (selectedMenu != AdmissionCommiteeMemberMenu.QUIT) {
			System.out.println("Choice\tOperation");
			for (AdmissionCommiteeMemberMenu menu : menus) {
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
		AdmissionCommiteeMember AdmissionCommiteeMember = new AdmissionCommiteeMember();
		System.out.print("Enter admin Id :");
		AdmissionCommiteeMember.setadminId(scan.next());
		System.out.print("Enter admin Name :");
		AdmissionCommiteeMember.setadminName(scan.next());
		System.out.print("Enter admin contact :");
		AdmissionCommiteeMember.setadminContact(scan.next());


		try {
			AdmissionCommiteeMemberService.add(AdmissionCommiteeMember);
			System.out.println("admin added");
		} catch (AdmissionCommiteeMemberNotFoundException excep) {
			System.out.println(excep.getMessage());
		}
	}

	private static void doList() {
		try {
			List<AdmissionCommiteeMember> AdmissionCommiteeMember = AdmissionCommiteeMemberService.getAll();
			if (AdmissionCommiteeMember.isEmpty()) {
				System.out.println("No admins found");
			} else {
				for (AdmissionCommiteeMember AdmissionCommiteeMember1 : AdmissionCommiteeMember) {
					System.out.println(AdmissionCommiteeMember1);
				}
			}
		} catch (AdmissionCommiteeMemberNotFoundException excep) {
			System.out.println(excep.getMessage());
		}
	}

	private static void doFind() {
		System.out.println("enter admin id :");
		String adminId = scan.next();

		try {
			AdmissionCommiteeMember AdmissionCommiteeMember = AdmissionCommiteeMemberService.getById(adminId);

			if (AdmissionCommiteeMember == null) {
				System.out.println("No admin with #" + adminId);
			} else {
				System.out.println(AdmissionCommiteeMember);
			}
		} catch (AdmissionCommiteeMemberNotFoundException excep) {
			System.out.println(excep.getMessage());
		}
	}

	private static void doDelete() {
		try {
			System.out.println("Enter admin id :");
			String adminId = scan.next();
			AdmissionCommiteeMember AdmissionCommiteeMember = AdmissionCommiteeMemberService.getById(adminId);
			if (AdmissionCommiteeMember == null) {
				System.out.println("No admin with #" + adminId);
			} else {
				AdmissionCommiteeMemberService.deleteById(adminId);
				System.out.println("Applicant Deleted");
			}
		} catch (AdmissionCommiteeMemberNotFoundException excep) {
			System.out.println(excep.getMessage());
		}

	}
}