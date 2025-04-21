package Pages;

import Data.Models.User;
import Exceptions.ModelNotFoundException;
import Logic.NeighbourhoodLogicActions;
import Logic.SearchSettingLogicActions;
import Logic.UserLogicActions;
import Pages.Components.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class SearchSettingsPage {
    public static HashMap<String,String> applicantSearch(HashMap<String,String> ashm){
        Scanner scanner = new Scanner(System.in);
        int input;

        try {
            System.out.println(Seperator.seperate());
            System.out.println(ApplicantSearchView.detailedView(ashm));//TODO change this
            System.out.println(Back.back());
            System.out.println("2. Marital Status Toggle");
            System.out.println("3. Flat Type Toggle");
        } catch (ModelNotFoundException e) {
            throw new RuntimeException(e);
        }
        input = Integer.parseInt(scanner.nextLine());

        try {
            if(input == 1){

            }else if(input == 2){
            String maritalStatus = ashm.get("MaritalStatus");
            if (maritalStatus == null) {
                ashm.put("MaritalStatus", "S"); // or whatever you want to default to
            } else {
                switch (maritalStatus) {
                    case "M":
                        ashm.put("MaritalStatus", null);
                        break;

                    case "S":
                        ashm.put("MaritalStatus", "M");
                        break;
                }
            }
            applicantSearch(ashm);
            }else if(input == 3){
                String flatType = ashm.get("FlatType");
                if (flatType == null) {
                    ashm.put("FlatType", "2Room"); // default value if null
                } else {
                    switch (flatType) {
                        case "3Room":
                            ashm.put("FlatType", null);
                            break;

                        case "2Room":
                            ashm.put("FlatType", "3Room");
                            break;
                    }
                }
                applicantSearch(ashm);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return ashm;
    }

    public static void neighbourhood(String userID){
        Scanner scanner = new Scanner(System.in);
        int input;

        try {
            System.out.println(Seperator.seperate());
            ArrayList<HashMap<String,String>> nal = NeighbourhoodLogicActions.getInstance().getAll();

            int x = 2;

            System.out.println("1. None");
            for(HashMap<String,String> nhm: nal){
                System.out.println((x++)+". "+NeighbourhoodView.simpleView(nhm.get("ID")));
            }

            input = Integer.parseInt(scanner.nextLine());

            if(input == 1){
                SearchSettingLogicActions.getInstance().editNeighbourhood(userID, null);
            }else {
                SearchSettingLogicActions.getInstance().editNeighbourhood(userID,nal.get(input-2).get("ID"));
            }

        } catch (ModelNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void manager(String userID){
        Scanner scanner = new Scanner(System.in);
        int input;

        try {
            System.out.println(Seperator.seperate());
            ArrayList<HashMap<String,String>> mal = UserLogicActions.getInstance().getAllManager();

            int x = 2;

            System.out.println("1. None");
            for(HashMap<String,String> mhm: mal){
                System.out.println((x++)+". "+UserView.simpleView(mhm.get("ID")));
            }

            input = Integer.parseInt(scanner.nextLine());
            if(input == 1){
                SearchSettingLogicActions.getInstance().editManager(userID, null);
            }else {
                SearchSettingLogicActions.getInstance().editManager(userID, mal.get(input - 2).get("ID"));
            }
        } catch (ModelNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void name(String userID){
        Scanner scanner = new Scanner(System.in);

        try {
            String input;
            System.out.println(Seperator.seperate());
            System.out.println("Name Search: ");

            input = scanner.nextLine();

            SearchSettingLogicActions.getInstance().editName(userID,input);
        } catch (ModelNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void adminStart(String userID){
        Scanner scanner = new Scanner(System.in);
        int input;

        try {
            System.out.println(Seperator.seperate());
            System.out.println(SearchSettingView.detailedView(userID));
            System.out.println(Back.back());
            System.out.println("2. Toggle Ascending");
            System.out.println("3. Name Filter");
            System.out.println("4. Neighbourhood Filter");
            System.out.println("5. Manager Filter");
        } catch (ModelNotFoundException e) {
            throw new RuntimeException(e);
        }

        input = Integer.parseInt(scanner.nextLine());

        try {
            if(input == 1){

            }else {
                if (input == 2) {
                    SearchSettingLogicActions.getInstance().toggleSort(userID);
                    //Ascending toggle
                } else if (input == 3) {
                    //Manual name search filtering
                    name(userID);
                } else if (input == 4) {
                    //Neighbourhood filtering
                    neighbourhood(userID);
                } else if (input == 5) {
                    manager(userID);
                }
                adminStart(userID);
            }
        } catch (ModelNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void start(String userID) {
        Scanner scanner = new Scanner(System.in);
        int input;

        try {
            System.out.println(Seperator.seperate());
            System.out.println(SearchSettingView.detailedView(userID));
            System.out.println(Back.back());
            System.out.println("2. Toggle Ascending");
            System.out.println("3. Name Filter");
            System.out.println("4. Neighbourhood Filter");
            System.out.println("5. Toggle 2 Room Filter");
            System.out.println("6. Toggle 3 Room Filter");
            System.out.println("7. Manager Filter");
        } catch (ModelNotFoundException e) {
            throw new RuntimeException(e);
        }

        input = Integer.parseInt(scanner.nextLine());

        try {
        if(input == 1){

        }else {
            if (input == 2) {
                SearchSettingLogicActions.getInstance().toggleSort(userID);
                //Ascending toggle
            } else if (input == 3) {
                //Manual name search filtering
                name(userID);
            } else if (input == 4) {
                //Neighbourhood filtering
                neighbourhood(userID);
            } else if (input == 5) {
                SearchSettingLogicActions.getInstance().toggleTwoRoomFilter(userID);
                //2 Room filtering
            } else if (input == 6) {
                SearchSettingLogicActions.getInstance().toggleThreeRoomFilter(userID);
                //3 Room filtering
            } else if (input == 7) {
                manager(userID);
            }
            start(userID);
        }
        } catch (ModelNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
