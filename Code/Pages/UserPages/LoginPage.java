package Pages.UserPages;

import Exceptions.WrongInputException;
import Logic.UserLogicActions;
import Pages.HomePages.ApplicantHomepage;
import Pages.HomePages.ManagerHomepage;
import Pages.HomePages.OfficerHomepage;
import Util.ClearCMD;
import java.util.HashMap;
import java.util.Scanner;

/**
 * The type Login page.
 */
public class LoginPage {
    /**
     * Start.
     */
    public static void start() {
        Scanner scanner = new Scanner(System.in);
        HashMap<String,String> user;

        String password;

        String userID = getNRIC(scanner);

        System.out.println("Password:");
        password = scanner.nextLine();

        try {
            user = UserLogicActions.getInstance().login(userID,password);
            userID = user.get("ID");
            ClearCMD.clear();

            String role = user.get("Role");

            System.out.println("Welcome, "+user.get("Name")+" ("+role+")");

            switch(role) {
                case "Applicant"-> ApplicantHomepage.start(userID);
                case "Manager"-> ManagerHomepage.start(userID);
                case "Officer"-> OfficerHomepage.start(userID);
                default -> System.out.println("Could not find user role");
            }

            start();
        } catch (WrongInputException e) {
            System.out.println("Invalid Input!");
            start();
        }
    }

    /**
     * Get nric string.
     *
     * @param scanner the scanner
     * @return the string
     */
    public static String getNRIC(Scanner scanner){
        System.out.println("User ID:");
        String userid = scanner.nextLine();
        if(
                userid.length() == (7+2) && (userid.charAt(0) == 'T' || userid.charAt(0) == 'S') &&
                Character.isLetter(userid.charAt(userid.length()-1))
        )
            return userid;
        else {
            System.out.println("Wrong NRIC Format");
            return getNRIC(scanner);
        }
    }
}
