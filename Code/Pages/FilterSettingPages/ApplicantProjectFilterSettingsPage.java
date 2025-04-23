package Pages.FilterSettingPages;

import Exceptions.ModelNotFoundException;
import Logic.SearchSettingLogicActions;
import Pages.Components.Back;
import Pages.Components.SearchSettingView;
import Pages.Components.Seperator;

import java.util.Scanner;

public class ApplicantProjectFilterSettingsPage implements ProjectFilterSettings {
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
                    ProjectFilterSettings.name(userID);
                } else if (input == 4) {
                    //Neighbourhood filtering
                    ProjectFilterSettings.neighbourhood(userID);
                } else if (input == 5) {
                    SearchSettingLogicActions.getInstance().toggleTwoRoomFilter(userID);
                    //2 Room filtering
                } else if (input == 6) {
                    SearchSettingLogicActions.getInstance().toggleThreeRoomFilter(userID);
                    //3 Room filtering
                } else if (input == 7) {
                    ProjectFilterSettings.manager(userID);
                }
                start(userID);
            }
        } catch (ModelNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
