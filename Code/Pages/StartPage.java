package Pages;

import Exceptions.WrongInputException;
import Logic.UserLogicActions;
import Util.ClearCMD;

public class StartPage {
    public static void start(){
        //load Information
        ClearCMD.clear();
        LoginPage.login();

    }
}
