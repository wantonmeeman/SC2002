package Pages;

import Data.Models.Model;
import Exceptions.ModelNotFoundException;
import Logic.ProjectLogicActions;
import Logic.EnquiryLogicActions;
import Pages.Components.Back;
import Pages.Components.EnquiryView;
import Pages.Components.Seperator;
import Pages.LogoutPage;
import Util.ClearCMD;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

import static Pages.RegistrationPage.replyEnquiry;

public class EnquiriesPage {
		public static void adminStart(String userID){
			Scanner scanner = new Scanner(System.in);
			int input;
			ArrayList<HashMap<String, String>> enquiries = new ArrayList<>();

			enquiries = EnquiryLogicActions.getInstance().getAll();
			System.out.println(Seperator.seperate());
			System.out.println(Back.back());

			int x = 2;
			for(HashMap<String,String> ehm: enquiries){
				try {
					System.out.println((x++)+". "+EnquiryView.simpleView(ehm.get("ID")));
				}catch(ModelNotFoundException e){
					System.out.println(e);
				}
			}

			input = Integer.parseInt(scanner.nextLine());
			ClearCMD.clear();
			if(input == 1){

			}else{
				viewEnquiry(enquiries.get(input-2).get("ID"),userID);
				adminStart(userID);
			}
		}

		public static void viewEnquiry(String enquiryID,String userID){
			try {
				Scanner scanner = new Scanner(System.in);
				int input;

				System.out.println(Seperator.seperate());
				System.out.println(EnquiryView.detailedView(enquiryID));

				System.out.println(Back.back());

				String managerID = ProjectLogicActions.getInstance().get(
						EnquiryLogicActions.getInstance().get(enquiryID).get("ProjectID")
				).get("ManagerID");

				if(managerID.equals(userID)){
					System.out.println("2. Reply");//TODO do we need to remove this
				}

				input = Integer.parseInt(scanner.nextLine());

				if(input == 1){
					return;
				}else if(input == 2 && managerID.equals(userID)){
					replyEnquiry(enquiryID);
				}
				viewEnquiry(enquiryID,userID);

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

		public static void start(String userID){
			Scanner scanner = new Scanner(System.in);
			int input;
			ArrayList<HashMap<String, String>> enquiries = new ArrayList<>();

			enquiries = EnquiryLogicActions.getInstance().getEnquiriesByUserID(userID);

			System.out.println(Seperator.seperate());
			System.out.println(Back.back());
			System.out.println("2. Create new Enquiry");

			int x = 3;
			for(HashMap<String,String> ehm: enquiries){
				try {
					System.out.println((x++)+". "+EnquiryView.simpleView(ehm.get("ID")));
				}catch(ModelNotFoundException e){
					System.out.println(e);
				}
			}

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

			System.out.println(Seperator.seperate());

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

			System.out.println(Seperator.seperate());

			System.out.println("Write your enquiry:");

            return scanner.nextLine();
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
			try {

				System.out.println(Seperator.seperate());
				System.out.println(EnquiryView.detailedView(enquiryID));

				System.out.println(Back.back());
				System.out.println("2. Edit");
				System.out.println("3. Delete");

				input = Integer.parseInt(scanner.nextLine());

				ClearCMD.clear();

				if(input == 1){
					return;
				}else if(input == 2){
					editEnquiry(enquiryID);
				}else if(input == 3){
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

				String message = enquiry.get("Message");
				Scanner scanner = new Scanner(System.in);


				System.out.println(Seperator.seperate());
				System.out.println("Old Message:");
				System.out.println(message);
				System.out.println("New Message:");
				String NewMsg = scanner.nextLine();

				EnquiryLogicActions.getInstance().editMessage(enquiryID,NewMsg);
			}catch(ModelNotFoundException e){
				//TODO
				System.out.println(e);
			}
			ClearCMD.clear();
			System.out.println("Changes Saved!");

		}

	}


