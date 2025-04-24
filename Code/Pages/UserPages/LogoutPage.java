package Pages.UserPages;

import Exceptions.ModelNotFoundException;
import Logic.UserLogicActions;
import Util.Interfaces.PrintToCMD;

/**
 * The type Logout page.
 */
public class LogoutPage{
    /**
     * Start.
     *
     * @param userID the user id
     */
    public static void start(String userID) {
        try {
            PrintToCMD.print(UserLogicActions.getInstance().getLogoutMessage(userID));
        } catch (ModelNotFoundException e) {
            PrintToCMD.print("Could not get logout message. Logging out");
        }
    }

    /**
     * Start.
     */
    public static void start(){
        PrintToCMD.print("User not found. Logging out");
    }
}
