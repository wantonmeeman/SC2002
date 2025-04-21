package Pages;

import Exceptions.ModelNotFoundException;
import Logic.*;
import Pages.Components.*;
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
				String flatID;
				String status;
				String projectID;
				String applicationID;
                try {
					applicationID = user.get("ApplicationID");

					HashMap<String,String> ahm = ApplicationLogicActions.getInstance().get(applicationID);
                    flatID = ahm.get("FlatID");
					status = ahm.get("Status");

					projectID = ProjectLogicActions.getInstance().getProjectByFlatID(flatID).get("ID");

					System.out.println(ProjectView.detailedView(projectID));
					System.out.println(FlatView.detailedView(flatID));
					System.out.println(ApplicationView.detailedView(applicationID));

				} catch (ModelNotFoundException e) {
					throw new RuntimeException(e);
				}
				String withdrawalStatus;
				try {
					withdrawalStatus = WithdrawalLogicActions.getInstance().get(applicationID).get("Status");
					System.out.println(WithdrawalView.detailedView(applicationID));

				} catch (ModelNotFoundException e) {
					withdrawalStatus = null;
				}
				String withdrawStr = "";
				if(withdrawalStatus == null || withdrawalStatus.equals("Unsuccessful")){
					withdrawStr = "2. Withdraw";
				}

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
					case "Withdrawn":
						System.out.println(Back.back());
						break;
				}
				System.out.println(withdrawStr);
				input = Integer.parseInt(scanner.nextLine());
				if(input == 1){

				}else if(input == 2 && !withdrawStr.equals("")){
					WithdrawalLogicActions.getInstance().withdraw(applicationID);
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


