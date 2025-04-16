package Pages;

import Logic.UserLogicActions;
import Data.Models.User;

public class LogoutPage {

    public static void logout(String userID, String name) {
        System.out.println(getLogoutMessage(userID,name));
    }

    public static String getLogoutMessage(String role,String name){
        String returnStr = "Logging out "+name;

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
    public static void logout() {
        System.out.println("Something went wrong! Logging out");
    }
}
