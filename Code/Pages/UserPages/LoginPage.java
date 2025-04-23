package Pages.UserPages;

import Exceptions.WrongInputException;
import Logic.UserLogicActions;
import Pages.HomePages.ApplicantHomepage;
import Pages.HomePages.ManagerHomepage;
import Pages.HomePages.OfficerHomepage;
import Util.ClearCMD;
import java.util.HashMap;
import java.util.Scanner;

public class LoginPage {
    public static void start() {
        Scanner scanner = new Scanner(System.in);
        HashMap<String,String> user;

        String userid;
        String password;

        System.out.println("User ID:");
        userid = scanner.nextLine();

        System.out.println("Password:");
        password = scanner.nextLine();

        try {
            user = UserLogicActions.getInstance().login(userid,password);
            String userID = user.get("ID");
            ClearCMD.clear();

            String role = user.get("Role");

            System.out.println("Welcome, "+user.get("Name")+" ("+role+")");

            switch(role) {
                case "Applicant"-> ApplicantHomepage.start(userID);
                case "Manager"-> ManagerHomepage.start(userID);
                case "Officer"-> OfficerHomepage.start(userID);
            }

            start();
        } catch (WrongInputException e) {
            System.out.println("Invalid Input!");//Move this to abstraction?
            start();
        }
    }
}
