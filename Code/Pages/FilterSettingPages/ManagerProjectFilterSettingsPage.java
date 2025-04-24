package Pages.FilterSettingPages;

import Exceptions.ModelNotFoundException;
import Pages.Components.*;
import Util.ClearCMD;

import java.util.Scanner;

/**
 * The type Manager project filter settings page.
 */
public class ManagerProjectFilterSettingsPage implements ProjectFilterSettings {
    /**
     * Start.
     *
     * @param userID the user id
     */
    public static void start(String userID){
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
            System.out.println("Could not find object");
        }

        try {
            input = Integer.parseInt(scanner.nextLine());
        }catch(NumberFormatException e){
            input = -1;//pass to default handler
        }
        ClearCMD.clear();

        try {
            if(input == 1){

            }else {
                if (input == 2) {
                    ProjectFilterSettings.sort(userID);
                    //Ascending toggle
                } else if (input == 3) {
                    //Manual name search filtering
                    ProjectFilterSettings.name(userID);
                } else if (input == 4) {
                    //Neighbourhood filtering
                    ProjectFilterSettings.neighbourhood(userID);
                } else if (input == 5) {
                    ProjectFilterSettings.manager(userID);
                }else{
                    System.out.println("Invalid Input");
                }
                start(userID);
            }
        } catch (ModelNotFoundException e) {
            System.out.println("Could not find object");
        }
    }
}
