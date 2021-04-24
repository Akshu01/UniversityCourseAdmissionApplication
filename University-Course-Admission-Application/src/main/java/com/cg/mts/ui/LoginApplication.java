package com.cg.mts.ui;

import java.util.Scanner;

import com.cg.mts.exception.LoginFailedException;
import com.cg.mts.service.ILoginService;
import com.cg.mts.service.LoginServiceImpl;

public class LoginApplication {

	public static final ILoginService loginService = new LoginServiceImpl();
	private static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) throws LoginFailedException {
		LoginMenu[] menus = LoginMenu.values();
		LoginMenu selectedMenu = null;

		while (selectedMenu != LoginMenu.QUIT) {
			System.out.println("Login Operation");
			for (LoginMenu menu : menus) {
				System.out.println(menu.ordinal() + "\t" + menu);
			}
			System.out.print("Enter choice : ");
			int ch = scan.nextInt();

			if (ch >= 0 && ch <= menus.length) {
				selectedMenu = menus[ch];

				switch (selectedMenu) {
				case LOGIN_AS_APPLICANT:
					doLoginAsApplicant();
					break;
				case LOGIN_AS_UNIVERSITYSTAFF:
					doLoginAsUniversityStaffMember();
					break;
				case LOGIN_AS_ADMISSION_COMMITEE_MEMBER:
					doLoginAsAddmissionCommiteeMember();
					break;
				default:
					break;
				}
			} else {
				selectedMenu = null;

			}

		}
		scan.close();
		System.out.println("Login Application Terminated");

	}

	private static void doLoginAsAddmissionCommiteeMember() {
		
	}

	private static void doLoginAsUniversityStaffMember() throws LoginFailedException {
		System.out.println("Enter name: ");
		scan.nextLine();
		String name = scan.nextLine();
		System.out.println("Enter password: ");
		String password = scan.nextLine();
		try { 
			boolean login = loginService.loginAsUniversityStaffMember(name, password);
			if(login == true)
			System.out.println("Login Successful");
		else
			throw new LoginFailedException("Login Unsuccessful, details not verified");
		}catch (LoginFailedException excep) {
			System.out.println(excep.getMessage());
		}
	}

	private static void doLoginAsApplicant() {
		
	}
}
