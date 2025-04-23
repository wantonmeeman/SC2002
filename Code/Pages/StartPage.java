package Pages;

import Pages.UserPages.LoginPage;
import Util.ClearCMD;

public class StartPage {
    public static void start(){
        //load Information
        ClearCMD.clear();
        LoginPage.start();
        start();
    }
}
