package Pages;
import Data.Models.Applicant;
import Data.Models.User;

import Util.ClearCMD;

import Logic.UserLogicActions;
import Pages.LogoutPage;
import Exceptions.ModelNotFoundException;

import java.util.HashMap;
import java.util.Scanner;

public class ApplicantPage {
		public static void start(String userID){
			Scanner scanner = new Scanner(System.in);
			int input;
			HashMap<String, String> user = new HashMap<>();
			try {
				user = UserLogicActions.getUser(userID);
			}catch(ModelNotFoundException e){
				System.out.println("User Not Found!");
				ClearCMD.clear();
				LogoutPage.logout();
			}

			System.out.println("========================");
			System.out.println("1. Logout");
			System.out.println("2. Projects");
			System.out.println("3. Enquiries");
			System.out.println("4. Applications");
			System.out.println("========================");

			input = Integer.parseInt(scanner.nextLine());
			ClearCMD.clear();

			switch(input){
				case 1:
					//Logout
					LogoutPage.logout(user.get("Role"),user.get("Name"));
					return;
				case 2:
					//Projects
					break;
				case 3:
					//Enquiries
					EnquiriesPage.start(userID);
					break;
				case 4:
					//Applications
					break;
				default:
					System.out.print("Wrong Input, Please try again.");
			}
			start(userID);

		}

//	    public void applyProject(Applicant applicant) {
//	        System.out.println("Applicant " + applicant.getName() + " (ID: "+
//	                         ") is applying for a project with application ID: " + applicationID);
//	        // Implementation for project application logic
//	    }
//
//	    public void requestAppWithdraw(Applicant applicant) {
//	        System.out.println("Applicant " + applicant.getName() + " is requesting to withdraw application " + applicationID);
//	        // Implementation for withdrawal request
//	    }
//
//	    public void createEnquiries(Applicant applicant) {
//	        System.out.println("Applicant " + applicant.getName() + " is creating an enquiry");
//	        // Implementation for enquiry creation
//	    }
//
//	    public void viewEnquiries(Applicant applicant) {
//	        System.out.println("Applicant " + applicant.getName() + " is viewing enquiries");
//	        // Implementation to fetch and display enquiries
//	    }
//
//	    public void deleteEnquiries(Applicant applicant) {
//	        System.out.println("Applicant " + applicant.getName() + " is deleting an enquiry");
//	        // Implementation for enquiry deletion
//	    }
//
//	    // Getter for applicationID
//	    public String generateApplicationID() {
//	        return applicationID;
//	    }

	}


