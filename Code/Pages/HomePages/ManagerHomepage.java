package Pages.HomePages;

import Pages.Components.HomepageView;
import Pages.EnquiryPages.ManagerEnquiryPage;
import Pages.ProjectPages.ManagerProjectPages;
import Pages.UserPages.LogoutPage;
import Pages.UserPages.UserPage;
import Util.ClearCMD;

import java.util.Scanner;

public class ManagerHomepage {
    public static void start(String userID) {
        Scanner scanner = new Scanner(System.in);
        int input;

        System.out.println(HomepageView.managerHomepage());
        try {
            input = Integer.parseInt(scanner.nextLine());
        }catch(NumberFormatException e){
            input = -1;//pass to default handler
        }
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
            case 4:
                UserPage.start(userID);
                break;
            default:
                System.out.println("Invalid Input");
        }
        start(userID);
    }
}

