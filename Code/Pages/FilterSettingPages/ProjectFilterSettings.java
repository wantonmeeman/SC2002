package Pages.FilterSettingPages;

import Exceptions.ModelNotFoundException;
import Logic.NeighbourhoodLogicActions;
import Logic.SearchSettingLogicActions;
import Logic.UserLogicActions;
import Pages.Components.NeighbourhoodView;
import Pages.Components.Seperator;
import Pages.Components.UserView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public interface ProjectFilterSettings {
    static void neighbourhood(String userID){
        Scanner scanner = new Scanner(System.in);
        int input;

        try {
            System.out.println(Seperator.seperate());
            ArrayList<HashMap<String,String>> nal = NeighbourhoodLogicActions.getInstance().getAll();

            int x = 2;

            System.out.println("1. None");
            for(HashMap<String,String> nhm: nal){
                System.out.println((x++)+". "+ NeighbourhoodView.simpleView(nhm.get("ID")));
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

    static void manager(String userID){
        Scanner scanner = new Scanner(System.in);
        int input;

        try {
            System.out.println(Seperator.seperate());
            ArrayList<HashMap<String,String>> mal = UserLogicActions.getInstance().getAllManager();

            int x = 2;

            System.out.println("1. None");
            for(HashMap<String,String> mhm: mal){
                System.out.println((x++)+". "+ UserView.simpleView(mhm.get("ID")));
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

    static void name(String userID){
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

    static void sort(String userID) throws ModelNotFoundException {
        SearchSettingLogicActions.getInstance().toggleSort(userID);
    }
}
