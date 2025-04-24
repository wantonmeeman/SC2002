package Pages;

import Pages.UserPages.LoginPage;
import Util.ClearCMD;

/**
 * The type Start page.
 */
public class StartPage {
    /**
     * Start.
     */
    public static void start(){
        //load Information
        ClearCMD.clear();
        LoginPage.start();
        start();
    }
}
