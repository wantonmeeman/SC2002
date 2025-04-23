package Pages.HomePages;

import Pages.Components.HomepageView;
import Pages.EnquiryPages.ManagerEnquiryPage;
import Pages.ProjectPages.ManagerProjectPages;
import Pages.UserPages.LogoutPage;
import Util.ClearCMD;

import java.util.Scanner;

public class ManagerHomepage {
    public static void start(String userID) {
        Scanner scanner = new Scanner(System.in);
        int input;

        System.out.println(HomepageView.managerHomepage());
        input = Integer.parseInt(scanner.nextLine());
        ClearCMD.clear();

        switch(input) {
            case 1:
                //Logout
                LogoutPage.start(userID);
                return;
            case 2:
                //Projects
                ManagerProjectPages.start(userID);
                break;
            case 3:
                //Enquiries
                ManagerEnquiryPage.start(userID);
                break;
            default:
                System.out.println("Wrong Input, Please try again.");
        }
        start(userID);
    }
}

