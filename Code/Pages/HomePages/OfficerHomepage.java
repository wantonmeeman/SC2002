package Pages.HomePages;

import Pages.*;
import Pages.ApplicationPages.ApplicationsPage;
import Pages.Components.HomepageView;
import Pages.EnquiryPages.ApplicantEnquiryPage;
import Pages.ProjectPages.ApplicantProjectPages;
import Pages.RegistrationPage;
import Pages.UserPages.LogoutPage;
import Util.ClearCMD;

import java.util.Scanner;

public class OfficerHomepage {
    public static void start(String userID) {
        Scanner scanner = new Scanner(System.in);
        int input;

        System.out.println(HomepageView.officerHomepage());
        try {
            input = Integer.parseInt(scanner.nextLine());
            ClearCMD.clear();
        switch(input) {
            case 1:
                //Logout
                LogoutPage.start(userID);
                return;
            case 2:
                //Projects
                ApplicantProjectPages.start(userID);
                break;
            case 3:
                //Enquiries
                ApplicantEnquiryPage.start(userID);
                break;
            case 4:
                //Applications
                ApplicationsPage.start(userID);
                break;
            case 5:
                RegistrationPage.start(userID);
                break;
            default:
                System.out.println("Wrong Input, Please try again.");
        }
        }catch(NumberFormatException e){
            System.out.println("Wrong Input, Please try again.");
        }
        start(userID);
    }
}

