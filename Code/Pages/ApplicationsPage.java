package Pages;

import Exceptions.ModelNotFoundException;
import Logic.UserLogicActions;
import Logic.ApplicationLogicActions;
import Logic.ProjectLogicActions;

import Pages.Components.ApplicationView;
import Pages.Components.Back;
import Pages.Components.ProjectView;
import Pages.Components.Seperator;
import Pages.LogoutPage;
import Util.ClearCMD;

import java.util.HashMap;
import java.util.Scanner;

public class ApplicationsPage {
		public static void start(String userID) {
			Scanner scanner = new Scanner(System.in);
			int input;
			HashMap<String, String> user = new HashMap<>();
try{
			user = UserLogicActions.getInstance().get(userID);
		}catch(ModelNotFoundException e){
		System.out.println(e);
	}


			if(user.get("ApplicationID") != null) {

				System.out.println(Seperator.seperate());
                try {
					String applicationID = user.get("ApplicationID");
                    String projectID = ApplicationLogicActions.getInstance().get(applicationID).get("ProjectID");
					String status = ApplicationLogicActions.getInstance().get(applicationID).get("Status");

					System.out.println(ProjectView.detailedView(projectID));
					System.out.println(ApplicationView.detailedView(applicationID));

				switch(status){
					case "Booked":
						try{
						System.out.println("Booked with Officer: "+UserLogicActions.getInstance().get(
								ApplicationLogicActions.getInstance().get(applicationID).get("OfficerID")
						).get("Name"));
						}catch(ModelNotFoundException e){

						}

					case "Pending":
					case "Unsuccessful":
					case "Successful":
						System.out.println(Back.back());
						System.out.println("2. Withdraw");
					break;
					case "Withdrawn":

						System.out.println(Back.back());
						break;
				}

				input = Integer.parseInt(scanner.nextLine());

				if(input == 1){

				}else if(input == 2) {
					//Withdraw
					try {
						ApplicationLogicActions.getInstance().withdraw(applicationID);
					} catch (ModelNotFoundException e) {
						System.out.println("Cant");
					}
				}

				} catch (ModelNotFoundException e) {
					throw new RuntimeException(e);
				}
			}else{

				System.out.println(Seperator.seperate());
				System.out.println("No Application Made");
				System.out.println(Back.back());

				input = Integer.parseInt(scanner.nextLine());
				if(input == 1){

				}else{
					start(userID);
				}
			}

		}

	}


