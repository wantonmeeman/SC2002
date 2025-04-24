package Pages;

import Data.Models.Project;
import Data.Models.User;
import Exceptions.ModelNotFoundException;
import Exceptions.UnauthorizedActionException;
import Logic.*;
import Pages.Components.*;
import Util.ClearCMD;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

/**
 * The type Registration page.
 */
public class RegistrationPage implements ReplyEnquiry {
	/**
	 * Start.
	 *
	 * @param userID the user id
	 */
	public static void start(String userID) {
		Scanner scanner = new Scanner(System.in);
		int input;
		ArrayList<HashMap<String, String>> registration = RegistrationLogicActions.getInstance().getByOfficerID(userID);

		System.out.println(Seperator.seperate());
		System.out.println(Back.back());
		System.out.println("2. Register for new Project");
		int x = 0;
		if (!registration.isEmpty()) {
			x = 3;
			for(HashMap<String,String> hm: registration){
				try {
					String projectID = hm.get("ProjectID");
					String status = hm.get("Status");

					System.out.println((x++) + ". "+ RegistrationView.simpleView(projectID,status));
				}catch(ModelNotFoundException e){
					System.out.println("Registration not found");
				}
			}
		}

		try {
			input = Integer.parseInt(scanner.nextLine());
		}catch(NumberFormatException e){
			input = -1;//pass to default handler
		}
		ClearCMD.clear();
		if (input == 1) {
			//start(userID);
		}else if(input == 2){
			register(userID);
			start(userID);
		}else if(
				input > 0 && input < x){
			if(registration.get(input-3).get("Status").equals("Successful")){
				detailedRegistration(registration.get(input - 3).get("ProjectID"),userID);
			}else{
				System.out.println("Not Allowed to Access");
			}
			start(userID);
		}else{
			System.out.println("Invalid Input");
		}
	}

	/**
	 * Register.
	 *
	 * @param userID the user id
	 */
	public static void register(String userID){
		ArrayList<HashMap<String, String>> projList = ProjectLogicActions.getInstance().getAll();
		int x = 2;
		Scanner scanner = new Scanner(System.in);
		int input;

		System.out.println(Seperator.seperate());
		System.out.println(Back.back());
		for(HashMap<String,String> hm: projList){
			System.out.println((x++) +". "+hm.get("Name"));
		}
		try {
			input = Integer.parseInt(scanner.nextLine());
		}catch(NumberFormatException e){
			input = -1;//pass to default handler
		}
		ClearCMD.clear();

		if (input == 1) {
			//start(userID);
		}else if(
				input > 0 && input < x){
			HashMap<String,String> newHM = new HashMap<String,String>();

			newHM.put("OfficerID",userID);
			newHM.put("ProjectID",projList.get(input-2).get("ID"));

			try {
				RegistrationLogicActions.getInstance().register(newHM);
			}catch(Exception e){
				System.out.println("Could not register");
			}
		}else{
			System.out.println("Invalid Input");
			register(userID);
		}
	}

	/**
	 * Detailed registration.
	 *
	 * @param projectID the project id
	 * @param userID    the user id
	 */
	public static void detailedRegistration(String projectID,String userID){
		Scanner scanner = new Scanner(System.in);
		int input;

        try {
			System.out.println(Seperator.seperate());

            System.out.println(ProjectView.detailedView(projectID));

			HashMap<String,String> phm = ProjectLogicActions.getInstance().get(projectID);
			System.out.println(Seperator.seperate());
			System.out.println(FlatView.detailedView(phm.get("ThreeRoomFlatID")));
			System.out.println(FlatView.detailedView(phm.get("TwoRoomFlatID")));

			System.out.println(Back.back());
			System.out.println("2. Enquiries");
			System.out.println("3. Applicants");

			try {
				input = Integer.parseInt(scanner.nextLine());
			}catch(NumberFormatException e){
				input = -1;//pass to default handler
			}
			ClearCMD.clear();

			if (input == 1) {
				return;
			} else if (input == 2) {
				viewEnquiries(projectID);
			} else if (input == 3) {
				viewApplicants(projectID, userID);
			}else{
				System.out.println("Invalid Input");
			}
		detailedRegistration(projectID, userID);
		} catch (ModelNotFoundException e) {
			System.out.println("Could not find object");
		}
	}

	/**
	 * View enquiry.
	 *
	 * @param enquiryID the enquiry id
	 */
	public static void viewEnquiry(String enquiryID){
		try {
		Scanner scanner = new Scanner(System.in);
		int input;

			System.out.println(Seperator.seperate());
			System.out.println(EnquiryView.detailedView(enquiryID));

			System.out.println(Back.back());
			System.out.println("2. Reply");

			try {
				input = Integer.parseInt(scanner.nextLine());
			}catch(NumberFormatException e){
				input = -1;//pass to default handler
			}
			ClearCMD.clear();

			if(input == 1){

			}else if(input == 2){
				ReplyEnquiry.replyEnquiry(enquiryID);
				viewEnquiry(enquiryID);
			}else{
				System.out.println("Invalid Input");
				viewEnquiry(enquiryID);
			}

		} catch (ModelNotFoundException e) {
			System.out.println("Could not find registration");
        }
    }

//	public static void replyEnquiry(String enquiryID){
//		try {
//		Scanner scanner = new Scanner(System.in);
//		System.out.println(Seperator.seperate());
//		System.out.println(EnquiryView.detailedView(enquiryID));
//        System.out.println("Reply:");
//
//		String reply = scanner.nextLine();
//		EnquiryLogicActions.getInstance().reply(enquiryID,reply);
//		} catch (ModelNotFoundException e) {
//			throw new RuntimeException(e);
//		}
//	}

	/**
	 * View enquiries.
	 *
	 * @param projectID the project id
	 */
	public static void viewEnquiries(String projectID){
		try {
			Scanner scanner = new Scanner(System.in);
			int input;

			HashMap<String,String> phm = ProjectLogicActions.getInstance().get(projectID);
			System.out.println(Seperator.seperate());
			System.out.println(phm.get("Name")+"'s Enquiries: ");

			ArrayList<HashMap<String,String>> enqArr = EnquiryLogicActions.getInstance().getEnquiriesByProjectID(projectID);

			int x = 2;

			System.out.println(Back.back());

			for(HashMap<String,String> hm: enqArr){
				System.out.println((x++) + ". "+EnquiryView.simpleView(hm.get("ID"))+" - "+(hm.get("Reply") == null ? "Not Replied": "Reply Sent"));
			}

			input = Integer.parseInt(scanner.nextLine());

			switch(input){
				case 1:
					return;
				default:
					viewEnquiry(enqArr.get(input-2).get("ID"));
					viewEnquiries(projectID);
					break;
			}
		} catch (ModelNotFoundException e) {
			System.out.println("Could not find object");
		}
	}

	/**
	 * View applicant.
	 *
	 * @param applicantID the applicant id
	 * @param officerID   the officer id
	 */
	public static void viewApplicant(String applicantID, String officerID){
		try{
			Scanner scanner = new Scanner(System.in);
			int input;
			HashMap<String,String> uhm = UserLogicActions.getInstance().get(applicantID);
			System.out.println(UserView.detailedView(applicantID));

			HashMap<String,String> ahm = ApplicationLogicActions.getInstance().get(uhm.get("ApplicationID"));
			System.out.println(ApplicationView.detailedView(uhm.get("ApplicationID")));
			System.out.println(FlatView.detailedView(ahm.get("FlatID")));

			System.out.println(Back.back());

			switch(ahm.get("Status")){
				case "Successful":
					System.out.println("2. Book Flat");

					break;
				case "Booked":
					System.out.println("2. Print receipt");
					break;
			}

			try {
				input = Integer.parseInt(scanner.nextLine());
			}catch(NumberFormatException e){
				input = -1;//pass to default handler
			}
			ClearCMD.clear();

			if(input == 1){

			}else if(input == 2){
				String status = ahm.get("Status");
				if(status.equals("Successful")) {
					try {
						ApplicationLogicActions.getInstance().book(uhm.get("ApplicationID"), officerID);
					}catch(UnauthorizedActionException e){
						System.out.println("Cannot book flat");
					}
				}else if(status.equals("Booked")){
					printReceipt(uhm.get("ApplicationID"));
				}

				viewApplicant(applicantID,officerID);
			}else{
				System.out.println("Invalid Input");
				viewApplicant(applicantID,officerID);
			}


		}catch(Exception e){
			System.out.println("Could not find object");
		}
	}

	/**
	 * Print receipt.
	 *
	 * @param applicationID the application id
	 */
	public static void printReceipt(String applicationID){
        HashMap<String,String> ahm = null;
        try {
            ahm = ApplicationLogicActions.getInstance().get(applicationID);
			HashMap<String,String> phm = ProjectLogicActions.getInstance().getProjectByFlatID(ahm.get("FlatID"));

			System.out.println(Seperator.seperate());
			System.out.println(UserView.detailedView(ahm.get("UserID")));
			System.out.println(ProjectView.detailedView(phm.get("ID")));
			System.out.println(FlatView.detailedView(ahm.get("FlatID")));
			System.out.println(ApplicationView.detailedView(applicationID));
			System.out.println(Seperator.seperate());

		} catch (ModelNotFoundException e) {
			System.out.println("Could not find object");
		}
	}

	/**
	 * View applicants.
	 *
	 * @param projectID the project id
	 * @param userID    the user id
	 */
	public static void viewApplicants(String projectID, String userID){
		try {
			Scanner scanner = new Scanner(System.in);
			int input;

			HashMap<String,String> phm = ProjectLogicActions.getInstance().get(projectID);
			System.out.println(phm.get("Name")+"'s Applicants: ");

			ArrayList<HashMap<String,String>> appArr = ApplicationLogicActions.getInstance().getFilteredApplicationsByProjectID(projectID,null);

			int x = 2;

			System.out.println(Back.back());

			for(HashMap<String,String> hm: appArr){
				HashMap<String,String> uhm = UserLogicActions.getInstance().get(hm.get("UserID"));
				String Name = uhm.get("Name");
				String ID = uhm.get("ID");
				System.out.println((x++)+". "+ Name +" - "+ID+" - "+hm.get("Status"));
			}

			try {
				input = Integer.parseInt(scanner.nextLine());
			}catch(NumberFormatException e){
				input = -1;//pass to default handler
			}
			ClearCMD.clear();
			if (input == 1) {
				return;
			} else if(
					input > 0 && input < x) {
				viewApplicant(appArr.get(input - 2).get("UserID"), userID);
				// viewApplicants(projectID);, then print
			} else{
				System.out.println("Invalid Input");
			}

			viewApplicants(projectID,userID);

		} catch (ModelNotFoundException e) {
			System.out.println("Could not find Applicant");
		}
	}

}
