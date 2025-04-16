package Pages;

import Exceptions.ModelNotFoundException;
import Logic.UserLogicActions;
import Logic.EnquiryLogicActions;
import Pages.LogoutPage;
import Util.ClearCMD;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class EnquiriesPage {
		public static void start(String userID){
			Scanner scanner = new Scanner(System.in);
			int input;
			ArrayList<HashMap<String, String>> enquiries = new ArrayList<>();

			enquiries = EnquiryLogicActions.getUserEnquiries(userID);


//			HashMap<String, String> user;
//
//			try {
//				user = UserLogicActions.getUser(userID);
//			}catch(ModelNotFoundException e){
//				System.out.println("User Not Found!");
//				ClearCMD.clear();
//				LogoutPage.logout();
//			}

			System.out.println("============Enquiries============");
			System.out.println("1. Back");
			System.out.print(formatEnquiries(enquiries));
			System.out.println("=================================");

			input = Integer.parseInt(scanner.nextLine());
			if(input == 1){

			}else{
				ClearCMD.clear();
				detailedEnquiry(enquiries.get(input-2).get("ID"));
			}
		}

		public static void detailedEnquiry(String enquiryID){
			Scanner scanner = new Scanner(System.in);
			int input;
			HashMap<String, String> enquiry = new HashMap<>();
			try {
				enquiry = EnquiryLogicActions.getEnquiry(enquiryID);

				System.out.println("=================================");
				System.out.println(formatEnquiry(enquiry));
				System.out.println("1. Back");
				System.out.println("2. Edit");
				System.out.println("3. Delete");

				input = Integer.parseInt(scanner.nextLine());

				if(input == 1){
					ClearCMD.clear();
					return;
				}else if(input == 2){
					//edit
					editEnquiry(enquiryID);
				}else if(input == 3){
					//delete
					deleteEnquiry(enquiryID);
				}
			}catch(ModelNotFoundException e){
				ClearCMD.clear();
				return;
			}
			detailedEnquiry(enquiryID);
		}

		public static void deleteEnquiry(String enquiryID) {
			try {
				EnquiryLogicActions.deleteEnquiry(enquiryID);
			} catch (ModelNotFoundException e) {

			}
			ClearCMD.clear();
			System.out.println("Changes Saved!");
		}

		public static void editEnquiry(String enquiryID){
			HashMap<String,String> enquiry = new HashMap<>();
			try {
				enquiry = EnquiryLogicActions.getEnquiry(enquiryID);

				String simulatedInput = enquiry.get("Message");
				Scanner scanner = new Scanner(System.in);

				System.out.println("=================================");
				System.out.println("Old Message:");
				System.out.println(simulatedInput);
				System.out.println("New Message:");
				String NewMsg = scanner.nextLine();

				EnquiryLogicActions.editEnquiry(enquiryID,NewMsg);
			}catch(ModelNotFoundException e){

			}
			ClearCMD.clear();
			System.out.println("Changes Saved!");

		}

		public static String formatEnquiry(HashMap<String,String> enquiry){
			String returnStr = "";
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date dateObject = new Date(Integer.parseInt(enquiry.get("Timestamp")));
			String formattedDateTime = formatter.format(dateObject);

			returnStr += "\n" + "Time and Date: " +formattedDateTime;
			returnStr += "\n" + "Project ID: " + enquiry.get("ProjectID");
			returnStr += "\n" + "Message:\n" + wrapText(enquiry.get("Message"),50);
			returnStr += "\n" + "Reply:\n" + wrapText(enquiry.get("Reply"),50);

			return returnStr;
		}

	public static String wrapText(String input, int maxLineLength) {//AI, rewrite this
//		StringBuilder result = new StringBuilder();
//		int index = 0;
//
//		while (index < input.length()) {
//			int endIndex = Math.min(index + maxLineLength, input.length());
//			result.append(input, index, endIndex).append("\n");
//			index = endIndex;
//		}
//
//		return result.toString();
		return input;
	}

		public static String formatEnquiries(ArrayList<HashMap<String,String>> enquiries){
			String returnStr = "";
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			int iterator = 2;

			for(HashMap<String,String> enquiry:enquiries){
				Date dateObject = new Date(Integer.parseInt(enquiry.get("Timestamp")));
				String formattedDateTime = formatter.format(dateObject);

				String Message = enquiry.get("Message");
				Message = Message.length() > 50 ? Message.substring(0, 50 - 3) + "..." : Message;

				returnStr += iterator++ +". "+ formattedDateTime + " - " + enquiry.get("ProjectID") + " - " + Message + "\n";
			}
			return returnStr;
		}

	}


