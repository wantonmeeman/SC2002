package Pages.UserPages;

import Exceptions.ModelNotFoundException;
import Logic.UserLogicActions;
import Util.Interfaces.PrintToCMD;

public class LogoutPage{
    public static void start(String userID) {
        try {
            PrintToCMD.print(UserLogicActions.getInstance().getLogoutMessage(userID));
        } catch (ModelNotFoundException e) {
            PrintToCMD.print("Could not get logout message. Logging out");
        }
    }

    public static void start(){
        PrintToCMD.print("User not found. Logging out");
    }
}
