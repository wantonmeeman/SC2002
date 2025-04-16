package Pages;

import Exceptions.ModelNotFoundException;
import Logic.UserLogicActions;
import Logic.ApplicationLogicActions;
import Logic.ProjectLogicActions;
import Pages.LogoutPage;
import Util.ClearCMD;

import java.util.HashMap;
import java.util.Scanner;

public class ApplicationsPage {
		public static void start(String userID) {
			Scanner scanner = new Scanner(System.in);
			int input;
			HashMap<String, String> user = new HashMap<>();
			HashMap<String, String> project = new HashMap<>();
			HashMap<String, String> application = new HashMap<>();
try{
			user = UserLogicActions.getInstance().get(userID);
		}catch(ModelNotFoundException e){
		System.out.print(1);
	}


			if(user.get("ApplicationID") != null) {
				try {
					application = ApplicationLogicActions.getInstance().get(user.get("ApplicationID"));
				}catch(ModelNotFoundException e){
					System.out.print(2);
				}

				try{
				project = ProjectLogicActions.getInstance().get(application.get("ProjectID"));
			}catch(ModelNotFoundException e){
					System.out.print(3);

			}
				String projName = project.get("Name");
				String flatType = String.valueOf(Integer.parseInt(application.get("Type")) + 2);
				String status = application.get("Status");

				System.out.println("========================");
				System.out.println("Application for " + projName + "(" + flatType + " Room)");
				System.out.println("Status: " + status);

				switch(status){
					case "Booked":
						try{
						System.out.println("Booked with Officer: "+UserLogicActions.getInstance().get(application.get("OfficerID")));
						}catch(ModelNotFoundException e){

						}
					case "Pending":
					case "Unsuccessful":
						System.out.println("1. Back");
						System.out.println("2. Withdraw");
						break;

					case "Successful":
						System.out.println("1. Back");
						System.out.println("2. Withdraw");
						System.out.println("3. Make a booking");
						break;

					case "Withdrawn":
						System.out.println("1. Back");
						break;
				}

				input = Integer.parseInt(scanner.nextLine());

				if(input == 1){

				}else if(input == 2){
					//Withdraw
					try {
						ApplicationLogicActions.getInstance().withdraw(application.get("ID"));
					}catch(ModelNotFoundException e){
						System.out.print("Cant");
					}
				}else if(status.equals("Successful") && input == 3){
					try {
					//Make a booking
					ApplicationLogicActions.getInstance().book(application.get("ID"),application.get("OfficerID"));
				}catch(ModelNotFoundException e){

				}
				}
			}else{
				System.out.println("========================");
				System.out.println("No Application Made");
				System.out.println("1. Back");

				input = Integer.parseInt(scanner.nextLine());
				if(input == 1){

				}else{
					start(userID);
				}
			}

		}

	}


