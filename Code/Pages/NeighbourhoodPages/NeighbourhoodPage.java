package Pages.NeighbourhoodPages;


import Data.Repository.NeighbourhoodRepository;
import Exceptions.ModelNotFoundException;

import Logic.*;
import Pages.Components.*;
import Util.ClearCMD;

import java.util.ArrayList;
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
			ClearCMD.clear();

			switch (input) {
				case 1 -> {
					// handle input == 1 here
				}
				case 2 -> {
					createNeighbourhood();
					start();
				}
				default -> detailedNeighbourhood(nal.get(input - 3).get("ID"));
			}
		}

	private static void detailedNeighbourhood(String neighbourhoodID){
		Scanner scanner = new Scanner(System.in);
		int input;

		System.out.println(Seperator.seperate());
		System.out.println(NeighbourhoodView.simpleView(neighbourhoodID));
		System.out.println(Back.back());
		System.out.println("2. Edit");
		System.out.println("3. Delete");

		input = Integer.parseInt(scanner.nextLine());
		ClearCMD.clear();

		switch (input) {
			case 1 -> {
				// handle input == 1 here
			}
			case 2 -> {
				editNeighbourhood(neighbourhoodID);
				detailedNeighbourhood(neighbourhoodID);
			}
			case 3 -> {
				deleteNeighbourhood(neighbourhoodID);
				detailedNeighbourhood(neighbourhoodID);
			}
		}
	}

	private static void createNeighbourhood(){
			Scanner scanner = new Scanner(System.in);
			System.out.println(Seperator.seperate());
			System.out.println("Neighbourhood Name:");

			HashMap<String,String> nhm = new HashMap<String,String>();
			nhm.put("Name",scanner.nextLine());
			NeighbourhoodLogicActions.getInstance().create(nhm);
			ClearCMD.clear();
		}

	private static void editNeighbourhood(String neighbourhoodID){
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

	private static void deleteNeighbourhood(String neighbourhoodID){
            try {
                NeighbourhoodRepository.getInstance().delete(neighbourhoodID);
            } catch (ModelNotFoundException e) {
                throw new RuntimeException(e);
            }
		System.out.println("Changes Saved!");
        }
}


