package Pages;


import Logic.*;
import Pages.Components.Back;
import Pages.Components.ProjectView;
import Pages.Components.Seperator;
import Util.ClearCMD;

import Exceptions.*;

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
				System.out.println(e.toString());
			}


			System.out.println(Seperator.seperate());
			System.out.println(Back.back());

			int x = 2;
			for(HashMap<String,String> hm: projList){
				String twoRoomID = hm.get("TwoRoomFlatID");
				String threeRoomID = hm.get("ThreeRoomFlatID");

				HashMap<String,String> twoRoomHm,threeRoomHm;

				try {
					if(twoRoomID != null) {
						twoRoomHm = FlatLogicActions.getInstance().get(twoRoomID);

						int twoRoomUnits = Integer.parseInt(twoRoomHm.get("TotalUnits"));

						if (twoRoomUnits > 0) {
							System.out.println(x + ". " +
									hm.get("Name") + " - 2 Room - $" + twoRoomHm.get("Price") +
									" - " + twoRoomUnits + " Units left");
							intStr.put(x++, hm.get("ID") + "2");
						}
					}
				} catch(ModelNotFoundException e){
					System.out.println(e);
				}

				try{
					if(threeRoomID != null) {
						threeRoomHm = FlatLogicActions.getInstance().get(threeRoomID);

						int threeRoomUnits = Integer.parseInt(threeRoomHm.get("TotalUnits"));

						if (threeRoomUnits > 0) {
							System.out.println(x + ". " +
									hm.get("Name") + " - 3 Room - $" + threeRoomHm.get("Price") +
									" - " + threeRoomUnits + " Units left");

							intStr.put(x++, hm.get("ID") + "3");
						}
					}
				}catch(ModelNotFoundException e){
					System.out.println(e);
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

			System.out.println(Seperator.seperate());
			System.out.println(ProjectView.detailedView(projectID));
			System.out.println(Back.back());
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
					System.out.println("123");//todo
				}catch(ModelNotFoundException e){
					System.out.println("456");//todo
				}catch(RepositoryNotFoundException e){
					System.out.println("789");//TODO
				}catch(UnauthorizedActionException e){
					System.out.println("You cannot apply for this flat!");
				}
			}

			}catch(ModelNotFoundException e){
				ClearCMD.clear();
				//Go back or smth
			}
		}

}


