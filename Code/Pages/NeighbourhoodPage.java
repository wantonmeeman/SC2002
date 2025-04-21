package Pages;


import Data.Repository.NeighbourhoodRepository;
import Exceptions.ModelAlreadyExistsException;
import Exceptions.ModelNotFoundException;
import Exceptions.RepositoryNotFoundException;
import Exceptions.UnauthorizedActionException;
import Logic.*;
import Pages.Components.*;
import Util.ClearCMD;
import Util.Config;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class NeighbourhoodPage {
		public static void start() {
			Scanner scanner = new Scanner(System.in);
			int input;

			ArrayList<HashMap<String,String>> nal = NeighbourhoodLogicActions.getInstance().getAll();
			System.out.println(Seperator.seperate());
			System.out.println(Back.back());
			System.out.println("2. Create new Neighbourhood");
			int x = 3;
			for(HashMap<String,String> nhm:nal){
				System.out.println((x++) + ". "+ NeighbourhoodView.simpleView(nhm.get("ID")));
            }
			input = Integer.parseInt(scanner.nextLine());

			if(input == 1){

			}else if(input == 2){
				createNeighbourhood();
				start();
			}else{
				detailedNeighbourhood(nal.get(input-3).get("ID"));
			}
		}

		public static void createNeighbourhood(){
			Scanner scanner = new Scanner(System.in);
			System.out.println(Seperator.seperate());
			System.out.println("Neighbourhood Name:");

			HashMap<String,String> nhm = new HashMap<String,String>();
			nhm.put("Name",scanner.nextLine());
			NeighbourhoodLogicActions.getInstance().create(nhm);
		}

		public static void detailedNeighbourhood(String neighbourhoodID){
			Scanner scanner = new Scanner(System.in);
			int input;

			System.out.println(Seperator.seperate());
			System.out.println(NeighbourhoodView.simpleView(neighbourhoodID));
			System.out.println(Back.back());
			System.out.println("2. Edit");
			System.out.println("3. Delete");

			input = Integer.parseInt(scanner.nextLine());
			if(input == 1){

			}else if(input == 2){
				editNeighbourhood(neighbourhoodID);
				detailedNeighbourhood(neighbourhoodID);
			}else if(input == 3){
				deleteNeighbourhood(neighbourhoodID);
				detailedNeighbourhood(neighbourhoodID);
			}
        }

	public static void editNeighbourhood(String neighbourhoodID){
		try {
			HashMap<String,String> nhm = NeighbourhoodLogicActions.getInstance().get(neighbourhoodID);

			String name = nhm.get("Name");
			Scanner scanner = new Scanner(System.in);

			System.out.println(Seperator.seperate());
			System.out.println("Old Neighbourhood:");
			System.out.println(name);
			System.out.println("New Neighbourhood:");
			String NewMsg = scanner.nextLine();

			NeighbourhoodLogicActions.getInstance().editName(neighbourhoodID,NewMsg);
		}catch(ModelNotFoundException e){
			//TODO
			System.out.println(e);
		}
		ClearCMD.clear();
		System.out.println("Changes Saved!");

	}
		public static void deleteNeighbourhood(String neighbourhoodID){
            try {
                NeighbourhoodRepository.getInstance().delete(neighbourhoodID);
            } catch (ModelNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
}


