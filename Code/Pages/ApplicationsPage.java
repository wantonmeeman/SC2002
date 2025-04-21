package Pages;

import Exceptions.ModelNotFoundException;
import Logic.ApplicationLogicActions;
import Logic.FlatLogicActions;
import Logic.ProjectLogicActions;
import Pages.Components.*;
import Pages.LogoutPage;
import Logic.UserLogicActions;
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

					HashMap<String,String> ahm = ApplicationLogicActions.getInstance().get(applicationID);
                    String flatID = ahm.get("FlatID");
					String status = ahm.get("Status");

					String projectID = ProjectLogicActions.getInstance().getProjectByFlatID(flatID).get("ID");

					System.out.println(ProjectView.detailedView(projectID));
					System.out.println(FlatView.detailedView(flatID));
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
					ClearCMD.clear();
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


