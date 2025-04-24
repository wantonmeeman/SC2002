package Pages.ProjectPages;

import Exceptions.ModelAlreadyExistsException;
import Exceptions.ModelNotFoundException;
import Exceptions.RepositoryNotFoundException;
import Exceptions.UnauthorizedActionException;
import Logic.ApplicationLogicActions;
import Logic.FlatLogicActions;
import Logic.ProjectLogicActions;
import Logic.WithdrawalLogicActions;
import Pages.Components.*;
import Pages.FilterSettingPages.ApplicantProjectFilterSettingsPage;
import Util.ClearCMD;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * The type Applicant project pages.
 */
public class ApplicantProjectPages {
    /**
     * Start.
     *
     * @param userID the user id
     */
    public static void start(String userID) {
        Scanner scanner = new Scanner(System.in);
        int input;
        ArrayList<HashMap<String, String>> projList = new ArrayList<>();
        HashMap<Integer,String> inputToIDMap = new HashMap<>();

        try {
            projList = ProjectLogicActions.getInstance().getAllFiltered(userID);
        }catch(ModelNotFoundException e){
            System.out.println("User not found");
            return;
        }

        System.out.println(Seperator.seperate());
        System.out.println(Back.back());
        System.out.println("2. Filter Settings");

        int x = 3;
        for(HashMap<String,String> hm: projList){
            String threeRoomFlatID = hm.get("ThreeRoomFlatID");
            String twoRoomFlatID = hm.get("TwoRoomFlatID");
            String projectID = hm.get("ID");

            try {
                String projectSimpleView = ProjectView.simpleView(projectID);
                if(threeRoomFlatID != null){
                    System.out.println(x+". "+projectSimpleView+" | "+ FlatView.simpleView(threeRoomFlatID));
                    inputToIDMap.put((x++),threeRoomFlatID);
                }

                if(twoRoomFlatID != null){
                    System.out.println(x+". "+projectSimpleView+" | "+FlatView.simpleView(twoRoomFlatID));
                    inputToIDMap.put((x++),twoRoomFlatID);
                }

            } catch (ModelNotFoundException e) {
                System.out.println("Project not found");
            }
        }

        try {
            input = Integer.parseInt(scanner.nextLine());
        }catch(NumberFormatException e){
            input = -1;//pass to default handler
        }

        ClearCMD.clear();
        if(input == 1) {

        }else if(input == 2){
            ApplicantProjectFilterSettingsPage.start(userID);
            start(userID);
        }else if(
                input > 0 && input < x){
            detailedFlat(inputToIDMap.get(input),userID);
        }else{
            System.out.println("Invalid Input");
            start(userID);
            }
    }

    /**
     * Detailed flat.
     *
     * @param flatID the flat id
     * @param userID the user id
     */
    public static void detailedFlat(String flatID,String userID){
        String projectID;
        Scanner scanner = new Scanner(System.in);
        int input;

        try {
            projectID = ProjectLogicActions.getInstance().getProjectByFlatID(flatID).get("ID");
        } catch (ModelNotFoundException e) {
            System.out.println("Could not find object");
            return;
        }

        System.out.println(Seperator.seperate());

        try {
            System.out.println(ProjectView.detailedView(projectID));
            System.out.println(FlatView.detailedView(flatID));
        }catch (ModelNotFoundException e){
            System.out.print("Could not find Project or Flat");
        }

        System.out.println(Back.back());
        System.out.println("2. Apply");

        try {
            input = Integer.parseInt(scanner.nextLine());
            ClearCMD.clear();
        }catch(NumberFormatException e){
            input = -1;//pass to default handler
        }

        if(input != 1){
            try {
                ApplicationLogicActions.getInstance().apply(userID,flatID);
            } catch (UnauthorizedActionException | ModelAlreadyExistsException | ModelNotFoundException |
                     RepositoryNotFoundException e) {
                System.out.println("Could not apply");
            }
        }
    }
}
