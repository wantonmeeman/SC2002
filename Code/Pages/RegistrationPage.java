package Pages;

import Data.Models.Project;
import Data.Models.User;
import Exceptions.ModelNotFoundException;
import Logic.*;
import Pages.Components.*;
import Util.ClearCMD;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class RegistrationPage {

	public static void start(String userID) {
		Scanner scanner = new Scanner(System.in);
		int input;
		ArrayList<HashMap<String, String>> registration = new ArrayList<>();

		registration = RegistrationLogicActions.getInstance().getByOfficerID(userID);


		System.out.println(Seperator.seperate());
		System.out.println(Back.back());
		System.out.println("2. Register for new Project");

		if (!registration.isEmpty()) {
			int x = 3;
			for(HashMap<String,String> hm: registration){
				try {
					String projectID = hm.get("ProjectID");
					String status = hm.get("Status");

					System.out.println((x++) + ". "+ RegistrationView.simpleView(projectID,status) + "\n");
				}catch(ModelNotFoundException e){
					//TODO
					System.out.println(e);
				}
			}
		}

		input = Integer.parseInt(scanner.nextLine());
		ClearCMD.clear();
		if (input == 1) {
			//start(userID);
		}else if(input == 2){
			register(userID);
			start(userID);
		}else{
			//if(registration.get(input-3).get("Status").equals("Successful")){
				detailedRegistration(registration.get(input - 3).get("ProjectID"),userID);
//			}else{
//				System.out.println("Not Allowed to Access");
//			}
			start(userID);
		}
	}

	public static void register(String userID){
		ArrayList<HashMap<String, String>> projList = new ArrayList<>();
		try {
			projList = ProjectLogicActions.getInstance().getAllFiltered(userID);
		}catch(ModelNotFoundException e){

		}
		int x = 2;
		Scanner scanner = new Scanner(System.in);
		int input;


		System.out.println(Seperator.seperate());
		System.out.println(Back.back());
		for(HashMap<String,String> hm: projList){
			System.out.println((x++) +". "+hm.get("Name"));
		}
		input = Integer.parseInt(scanner.nextLine());
		if (input == 1) {
			//start(userID);
		}else{
			HashMap<String,String> newHM = new HashMap<String,String>();

			newHM.put("OfficerID",userID);
			newHM.put("ProjectID",projList.get(input-2).get("ID"));

			try {
				RegistrationLogicActions.getInstance().register(newHM);
			}catch(Exception e){
				System.out.println(e);
			}
		}
	}

	public static void detailedRegistration(String projectID,String userID){
		Scanner scanner = new Scanner(System.in);
		int input;

        try {
            System.out.println(ProjectView.detailedView(projectID));
			System.out.println(Back.back());
			System.out.println("2. Enquiries");
			System.out.println("3. Applicants");

		input = Integer.parseInt(scanner.nextLine());

		switch(input){
			case 1:
				return;
			case 2:
				viewEnquiries(projectID);
				break;
			case 3:
				viewApplicants(projectID,userID);
				break;
		}
		detailedRegistration(projectID, userID);
		} catch (ModelNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public static void viewEnquiry(String enquiryID){
		try {
		Scanner scanner = new Scanner(System.in);
		int input;

			System.out.println(Seperator.seperate());
			System.out.println(EnquiryView.detailedView(enquiryID));

			System.out.println(Back.back());
			System.out.println("2. Reply");//TODO do we need to remove this

			input = Integer.parseInt(scanner.nextLine());

			if(input == 1){
				return;
			}else if(input == 2){
				replyEnquiry(enquiryID);
			}
			viewEnquiry(enquiryID);

		} catch (ModelNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

	public static void replyEnquiry(String enquiryID){
		try {
		Scanner scanner = new Scanner(System.in);
		System.out.println(Seperator.seperate());
		System.out.println(EnquiryView.detailedView(enquiryID));
        System.out.println("Reply:");

		String reply = scanner.nextLine();
		EnquiryLogicActions.getInstance().reply(enquiryID,reply);
		} catch (ModelNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

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
			viewEnquiries(projectID);
		} catch (ModelNotFoundException e) {
			throw new RuntimeException(e);//TODO
		}
	}

	public static void viewApplicant(String applicantID, String officerID){
		try{
			Scanner scanner = new Scanner(System.in);
			int input;
			HashMap<String,String> uhm = UserLogicActions.getInstance().get(applicantID);
			System.out.println(UserView.detailedView(applicantID));

			HashMap<String,String> ahm = ApplicationLogicActions.getInstance().get(uhm.get("ApplicationID"));
			System.out.println(ApplicationView.detailedView(uhm.get("ApplicationID")));
			System.out.println(ProjectView.detailedView(ahm.get("ProjectID")));

			System.out.println(Back.back());

			switch(ahm.get("Status")){
				case "Successful":
					System.out.println("2. Book Flat");

					break;
				case "Booked":
					System.out.println("2. Print receipt");
					break;
			}

			input = Integer.parseInt(scanner.nextLine());

			switch(input){
				case 1:
					return;
				case 2:
					ApplicationLogicActions.getInstance().book(uhm.get("ApplicationID"),officerID);
					break;

			}

			viewApplicant(applicantID,officerID);

		}catch(Exception e){
			System.out.println(e);
		}
	}

	public static void viewApplicants(String projectID, String userID){
		try {
			Scanner scanner = new Scanner(System.in);
			int input;

			HashMap<String,String> phm = ProjectLogicActions.getInstance().get(projectID);
			System.out.println(phm.get("Name")+"'s Applicants: ");

			ArrayList<HashMap<String,String>> appArr = ApplicationLogicActions.getInstance().getApplicationsByProjectID(projectID);

			int x = 2;

			System.out.println(Back.back());

			for(HashMap<String,String> hm: appArr){
				String Name = UserLogicActions.getInstance().get(hm.get("UserID")).get("Name");

				System.out.println((x++)+". "+ Name +" - "+hm.get("Status"));
			}

			input = Integer.parseInt(scanner.nextLine());

			switch(input){
				case 1:

				return;
				default:
					viewApplicant(appArr.get(input-2).get("UserID"), userID);
					//viewApplicants(projectID);, then print
				break;
			}

			viewApplicants(projectID,userID);

		} catch (ModelNotFoundException e) {
			throw new RuntimeException(e);//TODO
		}
	}

}
