package Pages;

import Exceptions.WrongInputException;
import Logic.UserLogicActions;
import Util.ClearCMD;
import java.util.HashMap;
import java.util.Scanner;

public class LoginPage {

    public static void login() {
        Scanner scanner = new Scanner(System.in);
        HashMap<String,String> user;
        String userid;
        String password;

        System.out.print("User ID:\n");

        userid = scanner.nextLine();

        System.out.print("Password:\n");

        password = scanner.nextLine();

        try {
            user = UserLogicActions.getInstance().login(userid,password);
            ClearCMD.clear();
            System.out.print(getWelcomeMessage(user.get("Role"),user.get("Name")));

            switch(user.get("Role")) {
                case "Officer":
                    OfficerPage.start(user.get("ID"));
                    break;
                case "Manager":
                    //ManagerPage.start();
                    break;
                case "Applicant":
                    ApplicantPage.start(user.get("ID"));
                    break;
            }

        } catch (WrongInputException e) {
            System.out.print("Wrong Input!\n");
            login();
        }


    }

    public static String getWelcomeMessage(String role,String name){
        String returnStr = "Welcome "+name;

        switch(role) {
            case "Officer":
                returnStr += " (Officer)";
                break;
            case "Manager":
                returnStr += " (Manager)";
                break;
            case "Applicant":
                returnStr += " (Applicant)";
                break;
        }

        return returnStr+"\n";
    }
}
