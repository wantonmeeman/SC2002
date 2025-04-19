package Pages;

import java.util.HashMap;
import java.util.Scanner;

import Data.Models.Applicant;
import Data.Models.Manager;
import Data.Models.Officer;
import Data.Models.User;
import Exceptions.WrongInputException;
import Logic.UserLogicActions;
import Pages.Components.HomepageView;
import Util.ClearCMD;

public class LoginPage {

    public static void login() {
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
            ClearCMD.clear();
            System.out.println(HomepageView.getWelcomeMessage(user.get("Role"),user.get("Name")));
            HomePage.start(user.get("ID"));
        } catch (WrongInputException e) {
            System.out.println("Wrong Input!\n");
            login();
        }
    }
}
