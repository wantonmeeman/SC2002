package Pages;



import Exceptions.ModelNotFoundException;
import Exceptions.ModelAlreadyExistsException;

import Logic.EnquiryLogicActions;
import Logic.ProjectLogicActions;
import Logic.UserLogicActions;
import Logic.ApplicationLogicActions;
import Util.ClearCMD;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class ProjectsPage {
		public static void start(String userID) {
			Scanner scanner = new Scanner(System.in);
			int input;
			ArrayList<HashMap<String, String>> projList = new ArrayList<>();
			HashMap<Integer,String> intStr = new HashMap<Integer, String>();

			try {
				projList = ProjectLogicActions.getInstance().getAllFiltered(userID);
			}catch(ModelNotFoundException e){
				System.out.print(e);
			}

			System.out.println("========================");
			System.out.println("1. Back");

			int x = 2;
			for(HashMap<String,String> hm: projList){
				int twoRoom = Integer.parseInt(hm.get("2RoomUnits"));
				int threeRoom = Integer.parseInt(hm.get("3RoomUnits"));
				if(twoRoom > 0){
					System.out.println(x +". "+
							hm.get("Name")+" - 2 Room"+
							"("+twoRoom+")");
					intStr.put(x++,hm.get("ID")+"2");
				}
				if(threeRoom > 0){
					System.out.println(x +". "+
							hm.get("Name")+" - 3 Room"+
							"("+threeRoom+")");

					intStr.put(x++,hm.get("ID")+"3");
				}
			}

			input = Integer.parseInt(scanner.nextLine());

			ClearCMD.clear();
			if(input != 1){
				detailedProject((intStr.get(input)),userID);
			}
		}

		public static void detailedProject(String projectID, String userID){
			Scanner scanner = new Scanner(System.in);
			int input;

			int flatType = Integer.parseInt(String.valueOf(projectID.charAt(projectID.length() - 1)));
			projectID = projectID.substring(0, projectID.length() - 1);



			try{
			System.out.println("========================");
			System.out.print(formatProject(ProjectLogicActions.getInstance().get(projectID),flatType-2));
			System.out.println("1. Back");
			System.out.println("2. Apply");

			input = Integer.parseInt(scanner.nextLine());

			if(input == 1){

			}else if(input == 2){
				//Apply

				HashMap<String,String> hm = new HashMap<String,String>();

				hm.put("UserID",userID);
				hm.put("ProjectID",projectID);
				hm.put("Type",String.valueOf(flatType-2));
				try {
					ApplicationLogicActions.getInstance().apply(hm);
				}catch(ModelAlreadyExistsException e){

				}catch(ModelNotFoundException e){

				}
			}

			}catch(ModelNotFoundException e){
				ClearCMD.clear();
				//Go back or smth
			}
		}

	public static String formatProject(HashMap<String,String> project,int type){
		String returnStr = "";

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");//TODO config

		String formattedOpening = formatter.format(new Date(Integer.parseInt(project.get("OpeningDate")) * 1000L));
		String formattedClosing = formatter.format(new Date(Integer.parseInt(project.get("ClosingDate")) * 1000L));
	try{
		returnStr += "Name:\n"+project.get("Name");
		returnStr += "\nNeighbourhood:\n" +project.get("Neighbourhood");
		returnStr += "\nOfficers:\n"+formatOfficers(project.get("OfficerIDs"));
		returnStr += "\nManager:\n"+UserLogicActions.getInstance().get(project.get("ManagerID")).get("Name");
		returnStr += "\nOpening to Ending:" + formattedOpening + "-" + formattedClosing + "\n";
	}catch(ModelNotFoundException e){
		ClearCMD.clear();
		//Go back or smth
	}

		return returnStr;
	}

	public static String formatOfficers(String officerStrings){
		String[] officerIDs = officerStrings.split(",");
		String returnStr = "";

		for(String x:officerIDs) {

			try{
			returnStr += UserLogicActions.getInstance().get(x).get("Name");
			}catch(ModelNotFoundException e){
				ClearCMD.clear();
				//Go back or smth
			}

		}

		return returnStr;
	}
}


