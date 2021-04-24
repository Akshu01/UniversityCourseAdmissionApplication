package com.cg.mts.ui;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.cg.mts.entities.Admission;
import com.cg.mts.exception.AdmissionNotGrantedException;
import com.cg.mts.service.AdmissionServiceImpl;
import com.cg.mts.service.IAdmissionService;

public class AdmissionApplication {
	
	public static final IAdmissionService admissionService = new AdmissionServiceImpl();
	private static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		AdmissionMenu[] menus = AdmissionMenu.values();
		AdmissionMenu selectedMenu = null;
		
		while(selectedMenu != AdmissionMenu.Quit) {
			System.out.println("Admission Operations available are: ");
			for(AdmissionMenu menu : menus) {
				System.out.println(menu.ordinal() + "\t" +menu);
			}
			System.out.println("Enter choice: ");
			int ch = scan.nextInt();
			
			if(ch>=0 && ch<=menus.length) {
				selectedMenu = menus[ch];
				
				switch(selectedMenu) {
				case Add_Admission:
					doAddAdmission();
					break;
				case Update_Admission:
					doUpdateAdmission();
					break;
				case Cancel_Admission:
					doCancelAdmission();
					break;
				case Show_All_Admissions_By_CourseId:
					doShowAllAdmissionsByCourseId();
					break;
				case Show_Admission_By_ApplicantId:
					doShowAdmissionByApplicantId();
					break;
				case Show_All_Admissions_By_Date:
					doShowAllAdmissionsByDate();
					break;
				case Quit:
					break;
				}
			} else {
				selectedMenu=null;
				System.out.println("Invalid Choice");
			}
		}
		scan.close();
		System.out.println("Application Terminated");
		
	}
	private static  void doAddAdmission() {
		Admission a = new Admission();
		System.out.println("Enter details of new admission to be added:");
		System.out.println("Enter Admission ID:");
		a.getCourse().setCourseId(scan.nextInt());
		System.out.println("Enter Admission Date:");
		a.setAdmissionDate(LocalDate.parse(scan.next()));
		
		try {
			IAdmissionService admissionServiceImpl = new AdmissionServiceImpl();
			Admission b = admissionServiceImpl.addAdmission(a);
			System.out.println("New admission added");
			System.out.println(b);
			
		}
		catch(AdmissionNotGrantedException e) {
			AdmissionNotGrantedException excep = new AdmissionNotGrantedException("Admission Not Granted");
		}
		}
	
	private static void doUpdateAdmission() {
		Admission a = new Admission();
		System.out.println("Enter details of new admission to be added:");
		System.out.println("Enter Admission ID:");
		a.getCourse().setCourseId(scan.nextInt());
		System.out.println("Enter Admission Date:");
		a.setAdmissionDate(LocalDate.parse(scan.next()));
		
		try {
			IAdmissionService admissionServiceImpl = new AdmissionServiceImpl();
			a = admissionServiceImpl.updateAdmission(a);
			System.out.println("Details updated");
			System.out.println(a);
			
			}
		catch(AdmissionNotGrantedException e) {
			AdmissionNotGrantedException excep = new AdmissionNotGrantedException("Cannot update details.");
		}
		
    }
	
	private static void doCancelAdmission() {
		System.out.println("Enter admission id of admisssion to be cancelled: ");
		int id = scan.nextInt();
		try {
			IAdmissionService admissionServiceImpl = new AdmissionServiceImpl();
			admissionServiceImpl.cancelAdmission(id);
			System.out.println("Admission cancelled successfully");
			}
		catch(AdmissionNotGrantedException e) {
			AdmissionNotGrantedException excep = new AdmissionNotGrantedException("Admission Not Cancelled");
		}
		
		
	}
	
	private static void doShowAllAdmissionsByCourseId() {
		System.out.println("Enter course id: ");
		int id = scan.nextInt();
		IAdmissionService admissionServiceImpl = new AdmissionServiceImpl();
		List<Admission> admissions =admissionServiceImpl.showAllAdmissionsByCourseId(id);
		if(admissions.isEmpty()) {
			System.out.println("No such admission found");
		} else {
			admissions.stream().forEach(System.out::println);
		}
				
	}
	
	private static void doShowAdmissionByApplicantId() {
		System.out.println("Enter applicant id: ");
		int id = scan.nextInt();
		IAdmissionService admissionServiceImpl = new AdmissionServiceImpl();
		Admission admission =admissionServiceImpl.showAdmissionByApplicant(id);
		if(admission == null) {
			System.out.println("No such admission found");
		} else {
			System.out.println(admission);		}
		
		}
	
		private static void doShowAllAdmissionsByDate() {
			System.out.println("Enter Date: ");
			LocalDate d = LocalDate.parse(scan.next());
			IAdmissionService admissionServiceImpl = new AdmissionServiceImpl();
			List<Admission> admissions =admissionServiceImpl.showAllAdmissionsByDate(d);
			if(admissions.isEmpty()) {
				System.out.println("No such admission found");
			} else {
				admissions.stream().forEach(System.out::println);
			
		}
	}

}
