package Pages.HomePages;

import Pages.ApplicationPages.ApplicationsPage;
import Pages.Components.HomepageView;
import Pages.EnquiryPages.ApplicantOfficerEnquiryPage;
import Pages.ProjectPages.ApplicantProjectPages;
import Pages.RegistrationPage;
import Pages.UserPages.LogoutPage;
import Pages.UserPages.UserPage;
import Util.ClearCMD;

import java.util.Scanner;

/**
 * The type Officer homepage.
 */
public class OfficerHomepage {
    /**
     * Start.
     *
     * @param userID the user id
     */
    public static void start(String userID) {
        Scanner scanner = new Scanner(System.in);
        int input;

        System.out.println(HomepageView.officerHomepage());
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
                ApplicantProjectPages.start(userID);
                break;
            case 3:
                //Enquiries
                ApplicantOfficerEnquiryPage.start(userID);
                break;
            case 4:
                //Applications
                ApplicationsPage.start(userID);
                break;
            case 5:
                RegistrationPage.start(userID);
                break;
            case 6:
                UserPage.start(userID);
                break;
            default:
                System.out.println("Invalid Input");
        }
        start(userID);
    }
}

