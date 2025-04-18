package Pages;
import Data.Models.Applicant;
import Data.Models.User;

import Util.ClearCMD;

import Logic.UserLogicActions;
import Pages.LogoutPage;
import Exceptions.ModelNotFoundException;

import java.util.HashMap;
import java.util.Scanner;

public class OfficerPage {
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

        System.out.println("========================");
        System.out.println("1. Logout");
        System.out.println("2. Projects");
        System.out.println("3. Enquiries");
        System.out.println("4. Applications");
        System.out.println("5. Registration");
        System.out.println("========================");

        input = Integer.parseInt(scanner.nextLine());
        ClearCMD.clear();

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
            case 5:
                RegistrationPage.start(userID);
                break;
            default:
                System.out.print("Wrong Input, Please try again.");
        }
        start(userID);

    }
//
//
//
//    public void viewRegistrationToBeOfficerStatus(Officer officer) {
//        System.out.println("Officer " + officer.getName() + " is checking their registration status");
//        // Implementation would fetch registration status
//    }
//
//    public void viewHandlingProject(Officer officer) {
//        System.out.println("Officer " + officer.getName() + " is viewing their assigned projects");
//        // Implementation would fetch assigned projects
//    }
//
//    public void replyEnquiries(Officer officer) {
//        System.out.println("Officer " + officer.getName() + " is replying to enquiries");
//        // Implementation would handle enquiry responses
//    }
//
//    public void viewPendingApplications(Officer officer) {
//        System.out.println("Officer " + officer.getName() + " is viewing pending applications");
//        // Implementation would fetch pending applications
//    }
//
//    public void approveOrRejectApplications(Officer officer) {
//        System.out.println("Officer " + officer.getName() + " is reviewing applications");
//        // Implementation would handle application approval/rejection
//    }
//
//    public void updateProjectStatus(Officer officer) {
//        System.out.println("Officer " + officer.getName() + " is updating project status");
//        // Implementation would handle project status updates
//    }
}
