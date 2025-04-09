package Pages;

import java.util.Scanner;

import Data.User;
import Exceptions.WrongInputException;
import Logic.UserLogicActions;
import Util.ClearCMD;

public class LoginPage {

    public static void login() {
        //load Information
        StringBuilder sb = new StringBuilder();
        Scanner scanner = new Scanner(System.in);

        String userid;
        String password;

        ClearCMD.clear();

        System.out.print("User ID:\n");

        userid = scanner.nextLine();

        System.out.print("Password:\n");

        password = scanner.nextLine();
        try {
            User user = UserLogicActions.login(userid, password);
            System.out.print("Correct");
        } catch (WrongInputException e) {
            System.out.print("Wrong!");
            login();
        }

        // try {
        //     if (user instanceof Applicant) {

        //     } else if (user instanceof HDBManager) {

        //     } else if (user instanceof HDBOfficer) {

        //     }
        // } catch (lol e) {

        // }
    }
}
