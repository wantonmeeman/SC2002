package Pages;

import Exceptions.ModelNotFoundException;
import Logic.ApplicationLogicActions;
import Logic.ProjectLogicActions;
import Logic.UserLogicActions;
import Logic.RegistrationLogicActions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class RegistrationPage {

	public static void start(String userID) {
		Scanner scanner = new Scanner(System.in);
		int input;
		HashMap<String, String> user = new HashMap<>();
		ArrayList<HashMap<String, String>> registration = new ArrayList<>();

		registration = RegistrationLogicActions.getInstance().getByOfficerID(userID);

		if (!registration.isEmpty()) {
			System.out.println("========================");

			for(HashMap<String,String> hm: registration){
				try {
					HashMap<String, String> project = ProjectLogicActions.getInstance().get(hm.get("ProjectID"));
					HashMap<String, String> officer = UserLogicActions.getInstance().get(project.get("ManagerID"));

					System.out.println("Project Name: " + project.get("Name"));
					System.out.println("Manager Name: " + officer.get("Name"));
					System.out.println("Status: " + hm.get("Status") + "\n");
				}catch(ModelNotFoundException e){

				}
			}
		} else {
			System.out.println("========================");
			System.out.println("No Registration Made");
		}

		System.out.println("1. Back");
		System.out.println("2. Register for new Project");

		input = Integer.parseInt(scanner.nextLine());
		if (input == 1) {
			//start(userID);
		}else{
			register(userID);
			start(userID);
		}
	}

	public static void register(String userID){
		ArrayList<HashMap<String, String>> projList = new ArrayList<>();
		try {
			projList = ProjectLogicActions.getInstance().getAllFiltered(userID);
		}catch(ModelNotFoundException e){

		}
		int x = 2;
		Scanner scanner = new Scanner(System.in);
		int input;

		System.out.println("========================");
		System.out.println("1. Back");
		for(HashMap<String,String> hm: projList){
			System.out.println((x++) +". "+hm.get("Name"));
		}
		input = Integer.parseInt(scanner.nextLine());
		if (input == 1) {
			//start(userID);
		}else{
			if(RegistrationLogicActions.getInstance().registerEligibility(userID, projList.get(input-2).get("ID"))){
				HashMap<String,String> newHM = new HashMap<String,String>();

				newHM.put("OfficerID",userID);
				newHM.put("ProjectID",projList.get(input-2).get("ID"));
				try {
					RegistrationLogicActions.getInstance().register(newHM);
				}catch(Exception e){

				}
			}
		}
	}
}
