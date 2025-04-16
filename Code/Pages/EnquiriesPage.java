package Pages;

import Exceptions.ModelNotFoundException;
import Logic.ProjectLogicActions;
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

			enquiries = EnquiryLogicActions.getInstance().getUserEnquiries(userID);


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
			System.out.println("2. Create new Enquiry");
			System.out.print(formatEnquiries(enquiries));
			System.out.println("=================================");

			input = Integer.parseInt(scanner.nextLine());
			ClearCMD.clear();
			if(input == 1){

			}else if(input == 2){
				createEnquiry(userID);
				start(userID);
			}else{
				detailedEnquiry(enquiries.get(input-3).get("ID"));
				start(userID);
			}
		}

		public static String chooseProject(String userID){
			Scanner scanner = new Scanner(System.in);
			int input;
			System.out.println("=================================");

			System.out.println("Choose which project you wish to enquire about:");
			ArrayList<HashMap<String,String>> projList = ProjectLogicActions.getInstance().getAll();

			int x = 1;
			for(HashMap<String,String> hm: projList){
				System.out.println((x++) +". "+hm.get("Name"));
			}

			input = Integer.parseInt(scanner.nextLine());
			return projList.get(input-1).get("ID");
		}

		public static String writeMessage(){
			Scanner scanner = new Scanner(System.in);
			System.out.println("=================================");

			System.out.println("Write your enquiry:");
			String message = scanner.nextLine();

			return message;
		}

		public static void createEnquiry(String userID){
			HashMap<String,String> hm = new HashMap<String,String>();

			hm.put("ProjectID",chooseProject(userID));
			hm.put("Message",writeMessage());
			hm.put("UserID", userID);

			EnquiryLogicActions.getInstance().create(hm);
			ClearCMD.clear();
		}

		public static void detailedEnquiry(String enquiryID){
			Scanner scanner = new Scanner(System.in);
			int input;
			HashMap<String, String> enquiry = new HashMap<>();
			try {
				enquiry = EnquiryLogicActions.getInstance().get(enquiryID);

				System.out.println("=================================");
				System.out.println(formatEnquiry(enquiry));
				System.out.println("1. Back");
				System.out.println("2. Edit");
				System.out.println("3. Delete");

				input = Integer.parseInt(scanner.nextLine());

				ClearCMD.clear();

				if(input == 1){
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
				EnquiryLogicActions.getInstance().delete(enquiryID);
			} catch (ModelNotFoundException e) {

			}
			ClearCMD.clear();
			System.out.println("Changes Saved!");
		}

		public static void editEnquiry(String enquiryID){
			HashMap<String,String> enquiry = new HashMap<>();
			try {
				enquiry = EnquiryLogicActions.getInstance().get(enquiryID);

				String simulatedInput = enquiry.get("Message");
				Scanner scanner = new Scanner(System.in);

				System.out.println("=================================");
				System.out.println("Old Message:");
				System.out.println(simulatedInput);
				System.out.println("New Message:");
				String NewMsg = scanner.nextLine();

				EnquiryLogicActions.getInstance().editMessage(enquiryID,NewMsg);
			}catch(ModelNotFoundException e){
				//TODO
			}
			ClearCMD.clear();
			System.out.println("Changes Saved!");

		}

		public static String formatEnquiry(HashMap<String,String> enquiry){
			String returnStr = "";
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//TODO config
			Date dateObject = new Date(Integer.parseInt(enquiry.get("Timestamp")) * 1000L);
			String formattedDateTime = formatter.format(dateObject);

			returnStr += "Time and Date: " +formattedDateTime;
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
			int iterator = 3;

			for(HashMap<String,String> enquiry:enquiries){
				Date dateObject = new Date(Integer.parseInt(enquiry.get("Timestamp"))*1000L);
				String formattedDateTime = formatter.format(dateObject);

				String Message = enquiry.get("Message");
				Message = Message.length() > 50 ? Message.substring(0, 50 - 3) + "..." : Message;

				returnStr += iterator++ +". "+ formattedDateTime + " - " + enquiry.get("ProjectID") + " - " + Message + "\n";
			}
			return returnStr;
		}

	}


