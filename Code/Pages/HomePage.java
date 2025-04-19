package Pages;
import Data.Models.Applicant;
import Data.Models.User;

import Pages.Components.HomepageView;
import Util.ClearCMD;

import Logic.UserLogicActions;
import Exceptions.ModelNotFoundException;

import java.util.HashMap;
import java.util.Scanner;

public class HomePage {
    public static void start(String userID){
        Scanner scanner = new Scanner(System.in);
        int input;

        HashMap<String, String> user = new HashMap<>();
        try {
            user = UserLogicActions.getInstance().get(userID);
        }catch(ModelNotFoundException e){
            System.out.println("User Not Found!");
            ClearCMD.clear();
            LogoutPage.logout();
        }

        switch(user.get("Role")){
            case "Applicant":
                System.out.println(HomepageView.applicantHomepage());
                input = Integer.parseInt(scanner.nextLine());
                switch(input){
                    case 1:
                        //Logout
                        LogoutPage.logout(user.get("Role"),user.get("Name"));
                        return;
                    case 2:
                        //Projects
                        ProjectsPage.start(userID);
                        break;
                    case 3:
                        //Enquiries
                        EnquiriesPage.start(userID);
                        break;
                    case 4:
                        //Applications
                        ApplicationsPage.start(userID);
                        break;
                    default:
                        System.out.println("Wrong Input, Please try again.");
                }

                break;
            case "Officer":
                System.out.println(HomepageView.officerHomepage());
                input = Integer.parseInt(scanner.nextLine());
                switch(input) {
                    case 1:
                        //Logout
                        LogoutPage.logout(user.get("Role"), user.get("Name"));
                        return;
                    case 2:
                        //Projects
                        ProjectsPage.start(userID);
                        break;
                    case 3:
                        //Enquiries
                        EnquiriesPage.start(userID);
                        break;
                    case 4:
                        //Applications
                        ApplicationsPage.start(userID);
                        break;
                    case 5:
                        RegistrationPage.start(userID);
                        break;
                }

                break;
            case "Manager":
                System.out.println("Manager");//TODO
                break;
        }

        start(userID);
    }
}


