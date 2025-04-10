package Pages;

import java.util.Scanner;

import Data.Models.Applicant;
import Data.Models.HDBManager;
import Data.Models.HDBOfficer;
import Data.Models.User;
import Exceptions.WrongInputException;
import Logic.UserLogicActions;
import Util.ClearCMD;

public class LoginPage {

    public static void login() {
        StringBuilder sb = new StringBuilder();
        Scanner scanner = new Scanner(System.in);
        User user;
        String userid;
        String password;

        System.out.print("User ID:\n");

        userid = scanner.nextLine();

        System.out.print("Password:\n");

        password = scanner.nextLine();

        try {
            user = UserLogicActions.login(userid,password);

                if (user instanceof HDBOfficer) {
                    System.out.print("Officer");
                }else if (user instanceof HDBManager) {
                    System.out.print("Manager");
                } else if (user instanceof Applicant) {
                    System.out.print("App");
                }

        } catch (WrongInputException e) {
            System.out.print("Wrong Input!\n");
            login();
        }


    }
}
