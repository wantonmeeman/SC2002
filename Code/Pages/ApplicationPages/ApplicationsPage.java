package Pages.ApplicationPages;

import Exceptions.ModelNotFoundException;
import Logic.*;
import Pages.Components.*;
import Util.ClearCMD;

import java.util.HashMap;
import java.util.Scanner;

/**
 * The type Applications page.
 */
public class ApplicationsPage {
    /**
     * Start.
     *
     * @param userID the user id
     */
    public static void start(String userID) {
        Scanner scanner = new Scanner(System.in);
        int input;
        HashMap<String, String> user = new HashMap<>();

        try {
            user = UserLogicActions.getInstance().get(userID);
        } catch (ModelNotFoundException e) {
            ClearCMD.clear();
            System.out.println("User not found");
            return;
        }

        if (user.get("ApplicationID") != null) {

            System.out.println(Seperator.seperate());
            String flatID;
            String status;
            String projectID;
            String applicationID;
            try {
                applicationID = user.get("ApplicationID");

                HashMap<String, String> ahm = ApplicationLogicActions.getInstance().get(applicationID);
                flatID = ahm.get("FlatID");
                status = ahm.get("Status");

                projectID = ProjectLogicActions.getInstance().getProjectByFlatID(flatID).get("ID");

                System.out.println(ProjectView.detailedView(projectID));
                System.out.println(FlatView.detailedView(flatID));
                System.out.println(ApplicationView.detailedView(applicationID));

            } catch (ModelNotFoundException e) {
               System.out.println("Could not find Application");
               return;
            }
            String withdrawalStatus;
            try {
                withdrawalStatus = WithdrawalLogicActions.getInstance().get(applicationID).get("Status");
                System.out.println(WithdrawalView.detailedView(applicationID));

            } catch (ModelNotFoundException e) {
                withdrawalStatus = null;
            }
            String withdrawStr = "";
            if (withdrawalStatus == null || withdrawalStatus.equals("Unsuccessful")) {
                withdrawStr = "2. Withdraw";
            }

            switch (status) {
                case "Booked":
                    try {
                        System.out.println("Booked with Officer: " + UserLogicActions.getInstance().get(
                                ApplicationLogicActions.getInstance().get(applicationID).get("OfficerID")
                        ).get("Name"));
                    } catch (ModelNotFoundException e) {

                    }

                case "Pending":
                case "Unsuccessful":
                case "Successful":
                case "Withdrawn":
                    System.out.println(Back.back());
                    break;
            }
            System.out.println(withdrawStr);
            try {
                input = Integer.parseInt(scanner.nextLine());
            }catch(NumberFormatException e){
                input = -1;//pass to default handler
            }
            ClearCMD.clear();
            if (input == 1) {
                // handle input == 1 here
            } else if (input == 2) {
                if (!withdrawStr.isEmpty()) {
                    WithdrawalLogicActions.getInstance().withdraw(applicationID);
                    start(userID);
                }
            }else{
                System.out.println("Invalid Input");
                start(userID);
            }

        } else {

            System.out.println(Seperator.seperate());
            System.out.println("No Application Made");
            System.out.println(Back.back());

            try {
                input = Integer.parseInt(scanner.nextLine());
            }catch(NumberFormatException e){
                input = -1;//pass to default handler
            }
            ClearCMD.clear();
            if(input != 1){
                System.out.print("Invalid Input");
                start(userID);
            }
        }
    }

}


